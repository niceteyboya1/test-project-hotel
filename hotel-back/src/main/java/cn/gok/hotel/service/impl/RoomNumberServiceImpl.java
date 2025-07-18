package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomNumber;
import cn.gok.hotel.mapper.RoomNumberMapper;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;

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

    /**
     * 获取指定房型的空闲房间（排除清洁中）
     */
    public RoomNumber getRandomFreeRoomEx(Integer roomTypeId) {
        List<RoomNumber> freeRooms = roomNumberMapper.findFreeRoomsByType(roomTypeId);
        if (freeRooms == null || freeRooms.isEmpty()) return null;
        // 过滤掉清洁中
        freeRooms.removeIf(r -> r.getStatus() != null && r.getStatus() == 2);
        if (freeRooms.isEmpty()) return null;
        return freeRooms.get(new Random().nextInt(freeRooms.size()));
    }

    @Override
    public void updateRoomStatus(Integer roomNumberId, Integer status) {
        roomNumberMapper.updateRoomStatus(roomNumberId, status);
    }

    @Override
    public List<RoomNumber> findAll() {
        return roomNumberMapper.selectAll();
    }

    @Override
    public int countRooms() {
        return roomNumberMapper.countRooms();
    }
    @Override
    public int countFreeRooms() {
        return roomNumberMapper.countFreeRooms();
    }

    @Override
    public int countFreeRoomsByType(Integer roomTypeId) {
        return roomNumberMapper.countFreeRoomsByType(roomTypeId);
    }

    @Override
    public RoomNumber findById(Integer roomNumberId) {
        // 需要在RoomNumberMapper中实现selectById
        return roomNumberMapper.selectById(roomNumberId);
    }

    @Override
    public void updateRoom(RoomNumber room) {
        // 需要在RoomNumberMapper中实现updateRoom
        roomNumberMapper.updateRoom(room);
    }

    /**
     * 定时任务：将清洁中超过1小时的房间自动设为空闲
     */
    @Scheduled(fixedRate = 5 * 60 * 1000) // 每5分钟执行一次
    public void autoSetCleaningToFree() {
        List<RoomNumber> allRooms = roomNumberMapper.selectAll();
        long now = System.currentTimeMillis();
        for (RoomNumber room : allRooms) {
            if (room.getStatus() != null && room.getStatus() == 2 && room.getCleaningStartTime() != null) {
                if (now - room.getCleaningStartTime() >= 60 * 60 * 1000) { // 超过1小时
                    room.setStatus(0); // 设为空闲
                    room.setCleaningStartTime(null);
                    roomNumberMapper.updateRoom(room);
                }
            }
        }
    }
    }

