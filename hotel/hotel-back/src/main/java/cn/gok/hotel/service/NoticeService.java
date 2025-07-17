package cn.gok.hotel.service;
import cn.gok.hotel.entity.Notice;
import java.util.List;

public interface NoticeService {
    void addNotice(Notice notice);
    List<Notice> getNoticesByMemberId(Integer memberId);
} 