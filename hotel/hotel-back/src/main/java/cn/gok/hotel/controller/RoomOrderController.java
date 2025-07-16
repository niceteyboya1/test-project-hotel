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
        RoomNumber room = roomNumberService.getRandomFreeRoom(roomTypeId);
        if (room == null) {
            result.put("success", false);
            result.put("message", "该房型暂无空闲房间");
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
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            order.setEnterTime(sdf.parse(checkin));
            order.setLeaveTime(sdf.parse(checkout));
        } catch (Exception e) {
            // 日期解析失败可忽略或记录日志
        }
        // 其它字段赋值...
        roomOrderService.createOrder(order);
        roomNumberService.updateRoomStatus(room.getRoomNumberId(), 1);
        result.put("success", true);
        result.put("roomNumberName", room.getRoomNumberName());
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
