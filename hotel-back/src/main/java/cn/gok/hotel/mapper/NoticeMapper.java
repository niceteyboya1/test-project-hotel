package cn.gok.hotel.mapper;
import cn.gok.hotel.entity.Notice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface NoticeMapper {
    void insertNotice(Notice notice);
    List<Notice> selectByMemberId(@Param("memberId") Integer memberId);
} 