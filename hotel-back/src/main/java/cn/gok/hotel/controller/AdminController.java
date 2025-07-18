package cn.gok.hotel.controller;

import cn.gok.hotel.entity.Admin;
import cn.gok.hotel.service.AdminService;
import cn.gok.hotel.service.MemberService;
import cn.gok.hotel.service.NoticeService;
import cn.gok.hotel.service.RoomOrderService;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private RoomOrderService roomOrderService;
    
    @Autowired
    private RoomNumberService roomNumberService;

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Admin admin = adminService.login(username, password);
        Map<String, Object> result = new HashMap<>();
        if (admin != null) {
            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("adminUsername", admin.getUsername());
            session.setAttribute("adminRole", admin.getRole());
            result.put("success", true);
            result.put("adminId", admin.getAdminId());
            result.put("username", admin.getUsername());
            result.put("role", admin.getRole());
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/stats")
    @ResponseBody
    public Map<String, Object> getStats() {
        Map<String, Object> result = new HashMap<>();
        try {
            int totalMembers = memberService.countMembers();
            int totalOrders = roomOrderService.countOrders();
            int totalRooms = roomNumberService.countRooms();
            int availableRooms = roomNumberService.countFreeRooms();
            result.put("success", true);
            result.put("totalMembers", totalMembers);
            result.put("totalOrders", totalOrders);
            result.put("totalRooms", totalRooms);
            result.put("availableRooms", availableRooms);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取统计数据失败");
        }
        return result;
    }

    @GetMapping("/members")
    @ResponseBody
    public Map<String, Object> getMembers() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<cn.gok.hotel.entity.Member> members = memberService.findAll();
            result.put("success", true);
            result.put("data", members);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取会员列表失败");
        }
        return result;
    }

    @GetMapping("/orders")
    @ResponseBody
    public Map<String, Object> getOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<cn.gok.hotel.entity.RoomOrder> orders = roomOrderService.findAll();
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取订单列表失败");
        }
        return result;
    }

    @GetMapping("/today-orders")
    @ResponseBody
    public Map<String, Object> getTodayOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            String today = java.time.LocalDate.now().toString();
            List<cn.gok.hotel.entity.RoomOrder> orders = roomOrderService.getTodayOrders(today);
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取今日订单失败");
        }
        return result;
    }

    /**
     * 获取所有房间及其状态（0=空闲，1=已入住，2=清洁中）
     * @return { success: true, data: [ {roomNumberId, roomNumberName, roomTypeId, status, cleaningStartTime, ...} ] }
     */
    @GetMapping("/rooms")
    @ResponseBody
    public Map<String, Object> getRooms() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<cn.gok.hotel.entity.RoomNumber> rooms = roomNumberService.findAll();
            result.put("success", true);
            result.put("data", rooms);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取房间列表失败");
        }
        return result;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Admin> listAll() {
        return adminService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addAdmin(@RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam String realName,
                                       @RequestParam String phone,
                                       @RequestParam String email,
                                       @RequestParam Integer role) {
        Map<String, Object> result = new HashMap<>();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setRealName(realName);
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setRole(role);
        
        boolean success = adminService.addAdmin(admin);
        result.put("success", success);
        if (!success) {
            result.put("message", "添加管理员失败");
        }
        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateAdmin(@RequestParam Integer adminId,
                                          @RequestParam String username,
                                          @RequestParam String realName,
                                          @RequestParam String phone,
                                          @RequestParam String email,
                                          @RequestParam Integer role) {
        Map<String, Object> result = new HashMap<>();
        Admin admin = adminService.findById(adminId);
        if (admin != null && "admin".equalsIgnoreCase(admin.getUsername())) {
            if (!"admin".equals(username) || role != 1) {
                result.put("success", false);
                result.put("message", "admin账号受保护，无法修改用户名或降级权限");
                return result;
            }
        }
        Admin update = new Admin();
        update.setAdminId(adminId);
        update.setUsername(username);
        update.setRealName(realName);
        update.setPhone(phone);
        update.setEmail(email);
        update.setRole(role);
        boolean success = adminService.updateAdmin(update);
        result.put("success", success);
        if (!success) {
            result.put("message", "更新管理员失败");
        }
        return result;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteAdmin(@RequestParam Integer adminId) {
        Map<String, Object> result = new HashMap<>();
        Admin admin = adminService.findById(adminId);
        if (admin != null && "admin".equalsIgnoreCase(admin.getUsername())) {
            result.put("success", false);
            result.put("message", "admin账号受保护，无法删除");
            return result;
        }
        boolean success = adminService.deleteAdmin(adminId);
        result.put("success", success);
        if (!success) {
            result.put("message", "删除管理员失败");
        }
        return result;
    }

    @PostMapping("/delete-order")
    @ResponseBody
    public Map<String, Object> deleteOrder(@RequestParam Integer orderId) {
        Map<String, Object> result = new HashMap<>();
        try {
            cn.gok.hotel.entity.RoomOrder order = roomOrderService.findById(orderId);
            if (order == null) {
                result.put("success", false);
                result.put("message", "订单不存在");
                return result;
            }
            boolean success = roomOrderService.deleteOrder(orderId, order.getMemberId());
            if (success && order.getRoomNumberId() != null) {
                roomNumberService.updateRoomStatus(order.getRoomNumberId(), 0);
            }
            result.put("success", success);
            if (!success) {
                result.put("message", "删除订单失败");
            } else {
                // 写入通知到数据库
                cn.gok.hotel.entity.Notice notice = new cn.gok.hotel.entity.Notice();
                notice.setMemberId(order.getMemberId());
                notice.setTitle("订单被管理员删除");
                notice.setContent("您的订单（房间:" + order.getRoomNumberName() + "，入住:" + order.getEnterTime() + "，离店:" + order.getLeaveTime() + "）已被管理员删除，如有疑问请联系客服。");
                notice.setIsRead(false);
                noticeService.addNotice(notice);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除订单失败");
        }
        return result;
    }

    @PostMapping("/set-room-free")
    @ResponseBody
    public Map<String, Object> setRoomFree(@RequestParam Integer roomNumberId) {
        Map<String, Object> result = new HashMap<>();
        try {
            roomNumberService.updateRoomStatus(roomNumberId, 0); // 0 表示空闲
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "设置房间为空闲失败");
        }
        return result;
    }

    /**
     * 修改房间状态，支持设置为清洁中（status=2），并记录清洁开始时间
     * @param roomNumberId 房间号ID
     * @param status 新状态（0=空闲，1=已入住，2=清洁中）
     * @return 操作结果
     */
    @PostMapping("/set-room-status")
    @ResponseBody
    public Map<String, Object> setRoomStatus(@RequestParam Integer roomNumberId, @RequestParam Integer status) {
        Map<String, Object> result = new HashMap<>();
        try {
            cn.gok.hotel.entity.RoomNumber room = roomNumberService.findById(roomNumberId);
            if (room == null) {
                result.put("success", false);
                result.put("message", "房间不存在");
                return result;
            }
            if (status == 2) { // 设置为清洁中
                room.setStatus(2);
                room.setCleaningStartTime(System.currentTimeMillis());
            } else {
                room.setStatus(status);
                room.setCleaningStartTime(null);
            }
            roomNumberService.updateRoom(room);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "设置房间状态失败");
        }
        return result;
    }

    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.invalidate();
        result.put("success", true);
        return result;
    }
} 