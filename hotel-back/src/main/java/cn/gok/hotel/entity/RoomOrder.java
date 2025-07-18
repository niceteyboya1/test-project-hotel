package cn.gok.hotel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RoomOrder {
    private Integer orderId;
    private Integer memberId;
    private String memberName;
    private String contactPhone;
    private Integer orderStatus;
    private String checkinTime; // 对应数据库的checkin_time
    private Date enterTime;
    private Date leaveTime;
    private Integer roomTypeId;
    private Integer roomNumberId;
    private String roomNumberName;
    private String guestIdInfo;
} 