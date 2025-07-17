package cn.gok.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Member {
    private Integer memberId;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String idCardNo;
    private BigDecimal balance;
    private java.util.Date createdAt;
} 