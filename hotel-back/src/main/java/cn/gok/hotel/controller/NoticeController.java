package cn.gok.hotel.controller;
import cn.gok.hotel.entity.Notice;
import cn.gok.hotel.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public Map<String, Object> getMyNotices(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        List<Notice> notices = noticeService.getNoticesByMemberId(memberId);
        result.put("success", true);
        result.put("data", notices);
        return result;
    }
} 