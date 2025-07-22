package com.nghcuong.todo_list.utils;

import com.nghcuong.todo_list.config.JwtProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UserUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        UserUtils.applicationContext = context;
    }

    public static Long getCurrentUserId() {
        try {
            JwtProvider jwtProvider = applicationContext.getBean(JwtProvider.class);
            return jwtProvider.getCurrentUserId();
        } catch (Exception e) {
            System.err.println("Error getting current user ID: " + e.getMessage());
            return null;
        }
    }
}
