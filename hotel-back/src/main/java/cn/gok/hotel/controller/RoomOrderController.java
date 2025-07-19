package cn.gok.hotel.controller;

import cn.gok.hotel.entity.RoomNumber;
import cn.gok.hotel.entity.RoomOrder;
import cn.gok.hotel.service.RoomNumberService;
import cn.gok.hotel.service.RoomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/order")
public class RoomOrderController {
    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private RoomNumberService roomNumberService;

    @PostMapping("/create")
    public Map<String, Object> createOrder(@RequestParam Integer roomTypeId,
                                           @RequestParam String idCard,
                                           @RequestParam String checkin,
                                           @RequestParam String checkout,
                                           HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer memberId = (Integer) session.getAttribute("memberId");
        String memberName = (String) session.getAttribute("username");
        String contactPhone = (String) session.getAttribute("phone");
        if (memberId == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        LocalDate enterDate = null;
        LocalDate leaveDate = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            enterDate = LocalDate.parse(checkin, dtf);
            leaveDate = LocalDate.parse(checkout, dtf);
        } catch (Exception e) {
            // 日期解析失败可忽略或记录日志
        }
        if (enterDate == null || leaveDate == null) {
            result.put("success", false);
            result.put("message", "日期格式错误");
            return result;
        }
        // 新增校验：同一用户同一时间段只能预订一个房间
        if (roomOrderService.hasOverlapOrder(memberId, enterDate, leaveDate)) {
            result.put("success", false);
            result.put("message", "同一时间段内只能预订一个房间");
            return result;
        }
        // 获取可用房间（排除清洁中）
        RoomNumber room = roomNumberService.getRandomFreeRoomEx(roomTypeId);
        if (room == null) {
            result.put("success", false);
            result.put("message", "该房型暂无可预订房间（可能都在清洁中或已入住）");
            return result;
        }
        RoomOrder order = new RoomOrder();
        order.setMemberId(memberId);
        order.setMemberName(memberName);
        order.setContactPhone(contactPhone);
        order.setRoomTypeId(roomTypeId);
        order.setRoomNumberId(room.getRoomNumberId());
        order.setRoomNumberName(room.getRoomNumberName());
        order.setCheckinTime(LocalDateTime.now().toString());
        order.setEnterTime(enterDate);
        order.setLeaveTime(leaveDate);
        // 其它字段赋值...
        try {
            roomOrderService.createOrder(order);
            roomNumberService.updateRoomStatus(room.getRoomNumberId(), 3); // 设置为已预定状态
            result.put("success", true);
            result.put("roomNumberName", room.getRoomNumberName());
        } catch (RuntimeException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public Object listOrders(HttpSession session) {
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return new Object[0];
        }
        
        return roomOrderService.listOrdersByMemberId(memberId);
    }
    @PostMapping("/delete")
    public Map<String, Object> deleteOrder(@RequestParam Integer orderId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        boolean success = roomOrderService.deleteOrder(orderId, memberId);
        result.put("success", success);
        if (!success) {
            result.put("message", "退单失败");
        }
        
        return result;
    }
}
