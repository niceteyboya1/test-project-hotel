package cn.gok.hotel.entity;

import lombok.Data;

@Data
public class RoomNumber {
    private Integer roomNumberId;
    private String roomNumberName;
    private Integer roomTypeId;
    /**
     * 房间状态：0=空闲，1=已入住，2=清洁中
     */
    private Integer status;
    /**
     * 清洁中开始时间（时间戳，毫秒），仅status=2时有效
     */
    private Long cleaningStartTime;
    private String roomTypeName;
    public String getRoomTypeName() {
        return roomTypeName;
    }
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
} 