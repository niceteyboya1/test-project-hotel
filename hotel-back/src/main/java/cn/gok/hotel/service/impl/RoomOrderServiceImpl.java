package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomOrder;
import cn.gok.hotel.mapper.RoomOrderMapper;
import cn.gok.hotel.service.RoomOrderService;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import cn.gok.hotel.mapper.MemberMapper;
import cn.gok.hotel.mapper.RoomTypeMapper;
import cn.gok.hotel.entity.Member;
import cn.gok.hotel.entity.RoomType;
import java.time.LocalDate;

@Service
public class RoomOrderServiceImpl implements RoomOrderService {
    @Autowired
    private RoomOrderMapper roomOrderMapper;
    @Autowired
    private RoomNumberService roomNumberService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RoomTypeMapper roomTypeMapper;

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

    @Override
    public boolean hasOverlapOrder(Integer memberId, LocalDate enterTime, LocalDate leaveTime) {
        int count = roomOrderMapper.countOverlapOrder(memberId, java.sql.Date.valueOf(enterTime), java.sql.Date.valueOf(leaveTime));
        return count > 0;
    }

    @Override
    public List<RoomOrder> findAll() {
        return roomOrderMapper.selectAll();
    }

    @Override
    public RoomOrder findById(Integer orderId) {
        return roomOrderMapper.selectById(orderId);
    }

    @Override
    public int countOrders() {
        return roomOrderMapper.countOrders();
    }

    @Override
    public List<RoomOrder> getTodayOrders(String today) {
        return roomOrderMapper.selectTodayOrders(today);
    }
    @Override
    public void createOrder(RoomOrder order) {
        // 查询会员余额
        Member member = memberMapper.findById(order.getMemberId());
        if (member == null) throw new RuntimeException("会员不存在");
        double balance = member.getBalance() != null ? member.getBalance().doubleValue() : 0.0;
        // 查询房型价格
        RoomType roomType = roomTypeMapper.findById(order.getRoomTypeId());
        double price = roomType != null && roomType.getPrice() != null ? roomType.getPrice() : 0.0;
        if (balance < price) throw new RuntimeException("余额不足");
        // 扣除余额
        member.setBalance(new java.math.BigDecimal(balance - price));
        memberMapper.updateMemberBalance(member.getMemberId(), member.getBalance());
        // 创建订单
        roomOrderMapper.insertOrder(order);
    }
}
