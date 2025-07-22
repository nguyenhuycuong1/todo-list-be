package com.nghcuong.todo_list.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String email;
    String password;
    @JsonProperty("full_name")
    String fullName;
    String phone;
    String username;
}
