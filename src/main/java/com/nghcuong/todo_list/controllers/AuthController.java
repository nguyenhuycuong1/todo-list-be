package com.nghcuong.todo_list.controllers;

import com.nghcuong.todo_list.config.JwtProvider;
import com.nghcuong.todo_list.config.UserDetailsImpl;
import com.nghcuong.todo_list.dto.request.LoginRequest;
import com.nghcuong.todo_list.dto.request.RegisterRequest;
import com.nghcuong.todo_list.dto.request.TokenRefreshRequest;
import com.nghcuong.todo_list.dto.response.ApiResponse;
import com.nghcuong.todo_list.dto.response.AuthResponse;
import com.nghcuong.todo_list.dto.response.Result;
import com.nghcuong.todo_list.dto.response.TokenRefreshResponse;
import com.nghcuong.todo_list.entity.RefreshToken;
import com.nghcuong.todo_list.entity.User;
import com.nghcuong.todo_list.exception.AppException;
import com.nghcuong.todo_list.exception.ErrorCode;
import com.nghcuong.todo_list.services.RefreshTokenService;
import com.nghcuong.todo_list.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            return new ApiResponse<>(new AuthResponse(
                    jwt,
                    refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail()))
                    .success();

    }

    @PostMapping("/register")
    public ApiResponse<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userService.existsByUsername(registerRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_RESOURCE, "Error: Username is already taken!");
        }
        // Kiểm tra xem email đã tồn tại chưa
        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new AppException(ErrorCode.DUPLICATE_RESOURCE, "Error: Email is already in use!");
        }

        // Tạo tài khoản người dùng mới
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());

        User savedUser = userService.save(user);

        // Tự động tạo token sau khi đăng ký
        String jwt = jwtProvider.generateTokenFromUsername(savedUser.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser.getId());

        AuthResponse authResponse = new AuthResponse(
                jwt,
                refreshToken.getToken(),
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail());

        return new ApiResponse<>(authResponse).success();

    }

    @PostMapping("/refresh-token")
    public ApiResponse<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateTokenFromUsername(user.getUsername());
                    return new ApiResponse<>(new TokenRefreshResponse(token, requestRefreshToken))
                            .success();
                })
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN, "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout() {
            Long currentUserId = jwtProvider.getCurrentUserId();
            if (currentUserId != null) {
                refreshTokenService.deleteByUserId(currentUserId);
            }

            SecurityContextHolder.getContext().setAuthentication(null);

            return new ApiResponse<>()
                    .success();
    }
}
