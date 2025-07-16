package cn.gok.hotel.controller;

import cn.gok.hotel.entity.RoomType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import cn.gok.hotel.service.RoomTypeService;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomTypeService roomTypeService; // 你需要有这个service

    @GetMapping("/findlist6")
    public List<Map<String, Object>> findList6() {
        List<RoomType> list = roomTypeService.findTop6(); // 你需要实现这个方法
        // 只返回房间名和价格
        return list.stream().map(room -> {
            Map<String, Object> map = new HashMap<>();
            map.put("roomName", room.getRoomTypeName());
            map.put("price", room.getPrice()); // 假设有price字段
            return map;
        }).collect(Collectors.toList());
    }
}