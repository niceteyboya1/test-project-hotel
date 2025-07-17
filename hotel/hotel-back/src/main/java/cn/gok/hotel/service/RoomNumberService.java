package cn.gok.hotel.service;

import cn.gok.hotel.entity.RoomNumber;

import java.util.List;

public interface RoomNumberService {
    RoomNumber getRandomFreeRoom(Integer roomTypeId);
    void updateRoomStatus(Integer roomNumberId, Integer status);
    List<RoomNumber> findAll();
    RoomNumber findById(Integer roomNumberId);
    void updateRoom(RoomNumber room);
    int countRooms();
    int countFreeRooms();
    RoomNumber getRandomFreeRoomEx(Integer roomTypeId); // 新增，排除清洁中
}
