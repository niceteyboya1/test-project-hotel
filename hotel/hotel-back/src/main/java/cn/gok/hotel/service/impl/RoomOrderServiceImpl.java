package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomOrder;
import cn.gok.hotel.mapper.RoomOrderMapper;
import cn.gok.hotel.service.RoomOrderService;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomOrderServiceImpl implements RoomOrderService {
    @Autowired
    private RoomOrderMapper roomOrderMapper;
    @Autowired
    private RoomNumberService roomNumberService;

    @Override
    public void createOrder(RoomOrder order) {
        roomOrderMapper.insertOrder(order);
    }
    @Override
public List<RoomOrder> listOrdersByMemberId(Integer memberId) {
    return roomOrderMapper.selectByMemberId(memberId);
}

@Override
public boolean deleteOrder(Integer orderId, Integer memberId) {
    RoomOrder order = roomOrderMapper.selectById(orderId);
    if (order != null && order.getMemberId().equals(memberId)) {
        int rows = roomOrderMapper.deleteById(orderId);
        if (rows > 0 && order.getRoomNumberId() != null) {
            // 退单后将房间状态设为0
            roomNumberService.updateRoomStatus(order.getRoomNumberId(), 0);
        }
        return rows > 0;
    }
    return false;
}
}
