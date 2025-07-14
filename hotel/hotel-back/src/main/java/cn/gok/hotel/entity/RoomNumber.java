package cn.gok.hotel.entity;

import lombok.Data;

@Data
public class RoomNumber {
    private Integer roomNumberId;
    private String roomNumberName;
    private Integer roomTypeId;
    private Integer status;
} 