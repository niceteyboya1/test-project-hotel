package cn.gok.hotel.entity;

import lombok.Data;

@Data
public class RoomType {
    private Integer roomTypeId;
    private String roomTypeName;
    private String roomSizeType;
    private String areaDesc;
    private String floorDesc;
    private String otherDesc;
    private String image1;
    private String image2;
    private String image3;
} 