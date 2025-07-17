package cn.gok.hotel.entity;
import lombok.Data;
import java.util.Date;

@Data
public class Notice {
    private Integer id;
    private Integer memberId;
    private String title;
    private String content;
    private Date noticeTime;
    private Boolean isRead;
} 