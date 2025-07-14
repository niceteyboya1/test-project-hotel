package cn.gok.hotel.entity;

import lombok.Data;

@Data
public class RoomOrder {
    private String orderId;
    private Integer memberId;
    private String memberName;
    private String contactPhone;
    private Integer orderStatus;
    private String checkinTime;
    private Integer roomTypeId;
    private Integer roomNumberId;
    private String roomNumberName;
    private String guestIdInfo;
} 