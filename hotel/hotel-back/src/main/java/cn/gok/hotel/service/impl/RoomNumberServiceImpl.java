package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomNumber;
import cn.gok.hotel.mapper.RoomNumberMapper;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoomNumberServiceImpl implements RoomNumberService {
    @Autowired
    private RoomNumberMapper roomNumberMapper;

    @Override
    public RoomNumber getRandomFreeRoom(Integer roomTypeId) {
        List<RoomNumber> freeRooms = roomNumberMapper.findFreeRoomsByType(roomTypeId);
        if (freeRooms == null || freeRooms.isEmpty()) return null;
        return freeRooms.get(new Random().nextInt(freeRooms.size()));
    }

    @Override
    public void updateRoomStatus(Integer roomNumberId, Integer status) {
        roomNumberMapper.updateRoomStatus(roomNumberId, status);
    }
    }

