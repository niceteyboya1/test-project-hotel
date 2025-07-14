package cn.gok.hotel.controller;

import cn.gok.hotel.entity.Member;
import cn.gok.hotel.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    

    @PostMapping("/checklogin")
    @ResponseBody
    public Map<String, Object> checkLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Member member = memberService.checkLogin(username, password);
        Map<String, Object> result = new HashMap<>();
        if (member != null) {
            session.setAttribute("memberId", member.getMemberId());
            session.setAttribute("username", member.getUsername());
            result.put("success", true);
            result.put("memberId", member.getMemberId());
            result.put("username", member.getUsername());
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }
} 