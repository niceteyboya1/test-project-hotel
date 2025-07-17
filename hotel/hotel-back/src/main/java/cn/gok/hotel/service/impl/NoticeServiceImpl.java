package cn.gok.hotel.service.impl;
import cn.gok.hotel.entity.Notice;
import cn.gok.hotel.mapper.NoticeMapper;
import cn.gok.hotel.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
    }

    @Override
    public List<Notice> getNoticesByMemberId(Integer memberId) {
        return noticeMapper.selectByMemberId(memberId);
    }
} 