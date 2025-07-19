package cn.gok.hotel.entity;

import lombok.Data;

@Data
public class RoomNumber {
    private Integer roomNumberId;
    private String roomNumberName;
    private Integer roomTypeId;
    /**
     * 房间状态：0=空闲，1=已入住，2=清洁中，3=已预定
     */
    private Integer status;
    /**
     * 清洁中开始时间（时间戳，毫秒），仅status=2时有效
     */
    private Long cleaningStartTime;
    /**
     * 清洁前的状态，用于清洁结束后恢复原状态
     */
    private Integer previousStatus;
    private String roomTypeName;
} 