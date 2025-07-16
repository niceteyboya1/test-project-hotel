package cn.gok.hotel.controller;

import cn.gok.hotel.entity.RoomType;
import cn.gok.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/list")
    public List<RoomType> listAll() {
        return roomTypeService.findAll();
    }
}