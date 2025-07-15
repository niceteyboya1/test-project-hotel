package cn.gok.hotel.entity;

import lombok.Data;
@Data
public class Room {
    private Integer id;
    private String roomName;
    private Double price;
    // 其它字段...
}