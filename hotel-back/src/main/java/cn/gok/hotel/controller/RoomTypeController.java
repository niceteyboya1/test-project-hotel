package cn.gok.hotel.controller;

import cn.gok.hotel.entity.RoomType;
import cn.gok.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import cn.gok.hotel.service.RoomNumberService;

@RestController
@RequestMapping("/api/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomNumberService roomNumberService;

    @GetMapping("/list")
    public List<RoomType> listAll() {
        return roomTypeService.findAll();
    }
    @GetMapping("/roomtype/detail/{id}")
    @ResponseBody
    public Map<String, Object> getRoomTypeDetail(@PathVariable Integer id) {
        RoomType roomType = roomTypeService.findById(id);
        int freeCount = roomNumberService.countFreeRoomsByType(id);
        Map<String, Object> result = new HashMap<>();
        result.put("areaDesc", roomType.getAreaDesc());
        result.put("freeCount", freeCount);
        return result;
    }
}
