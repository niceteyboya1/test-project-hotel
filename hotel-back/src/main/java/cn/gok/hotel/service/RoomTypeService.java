package cn.gok.hotel.service;

import cn.gok.hotel.entity.RoomType;
import java.util.List;

public interface RoomTypeService {
    List<RoomType> findTop6();
    List<RoomType> findAll();
    RoomType findById(Integer id);
}