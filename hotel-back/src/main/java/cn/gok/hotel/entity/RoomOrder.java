package cn.gok.hotel.entity;

import java.time.LocalDate;

public class RoomOrder {
    private Integer orderId;
    private Integer memberId;
    private String memberName;
    private String contactPhone;
    private Integer orderStatus;
    private String checkinTime; // 对应数据库的checkin_time
    private LocalDate enterTime;
    private LocalDate leaveTime;
    private Integer roomTypeId;
    private Integer roomNumberId;
    private String roomNumberName;
    private String guestIdInfo;

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public Integer getOrderStatus() { return orderStatus; }
    public void setOrderStatus(Integer orderStatus) { this.orderStatus = orderStatus; }
    public String getCheckinTime() { return checkinTime; }
    public void setCheckinTime(String checkinTime) { this.checkinTime = checkinTime; }
    public LocalDate getEnterTime() { return enterTime; }
    public void setEnterTime(LocalDate enterTime) { this.enterTime = enterTime; }
    public LocalDate getLeaveTime() { return leaveTime; }
    public void setLeaveTime(LocalDate leaveTime) { this.leaveTime = leaveTime; }
    public Integer getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Integer roomTypeId) { this.roomTypeId = roomTypeId; }
    public Integer getRoomNumberId() { return roomNumberId; }
    public void setRoomNumberId(Integer roomNumberId) { this.roomNumberId = roomNumberId; }
    public String getRoomNumberName() { return roomNumberName; }
    public void setRoomNumberName(String roomNumberName) { this.roomNumberName = roomNumberName; }
    public String getGuestIdInfo() { return guestIdInfo; }
    public void setGuestIdInfo(String guestIdInfo) { this.guestIdInfo = guestIdInfo; }
} 