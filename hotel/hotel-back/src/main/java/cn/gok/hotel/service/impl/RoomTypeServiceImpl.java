package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomType;
import cn.gok.hotel.mapper.RoomTypeMapper;
import cn.gok.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> findTop6() {
        return roomTypeMapper.findTop6();
    }
}