package cn.gok.hotel.service;

import cn.gok.hotel.entity.RoomNumber;

public interface RoomNumberService {
    RoomNumber getRandomFreeRoom(Integer roomTypeId);
    void updateRoomStatus(Integer roomNumberId, Integer status);
}
