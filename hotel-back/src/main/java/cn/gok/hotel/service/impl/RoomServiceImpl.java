package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.Room;
import cn.gok.hotel.mapper.RoomMapper;
import cn.gok.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> findList6() {
        return roomMapper.findList6();
    }
}