package cn.gok.hotel.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Admin {
    private Integer adminId;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private Integer role; // 1:超级管理员 2:普通管理员
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginTime;
} 