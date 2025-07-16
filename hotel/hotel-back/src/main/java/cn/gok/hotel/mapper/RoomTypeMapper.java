package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.RoomType;
import java.util.List;

public interface RoomTypeMapper {
    List<RoomType> findTop6();
}