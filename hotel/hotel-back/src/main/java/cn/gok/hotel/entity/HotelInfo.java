package cn.gok.hotel.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HotelInfo {
    private Integer hotelId;
    private String hotelName;
    private String hotelDesc;
    private Double longitude;
    private Double latitude;
    private String businessLicenseNo;
    private String legalPerson;
    private LocalDateTime createdAt;
    private Boolean hasBreakfast;
    private Boolean hasParking;
} 