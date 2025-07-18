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
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String realName,
                                        @RequestParam String phone) {
        Map<String, Object> result = new HashMap<>();
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setRealName(realName);
        member.setPhone(phone);
        boolean success = memberService.register(member);
        result.put("success", success);
        if (!success) {
            result.put("message", "注册失败，用户名可能已存在");
        }
        return result;
    }

    @GetMapping("/profile")
    @ResponseBody
    public Map<String, Object> getProfile(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        Member member = memberService.findById(memberId);
        if (member == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }
        result.put("success", true);
        result.put("data", member);
        return result;
    }
    @PostMapping("/recharge")
    @ResponseBody
    public Map<String, Object> recharge(@RequestParam Integer memberId, @RequestParam Double amount) {
    Map<String, Object> result = new HashMap<>();
    if (amount == null || amount <= 0) {
        result.put("success", false);
        result.put("message", "充值金额必须大于0");
        return result;
    }
    Member member = memberService.findById(memberId);
    if (member == null) {
        result.put("success", false);
        result.put("message", "用户不存在");
        return result;
    }
    java.math.BigDecimal newBalance = member.getBalance() == null ? java.math.BigDecimal.ZERO : member.getBalance();
    newBalance = newBalance.add(new java.math.BigDecimal(amount));
    memberService.updateBalance(memberId, newBalance);
    result.put("success", true);
    result.put("newBalance", newBalance);
    return result;
}
} 
