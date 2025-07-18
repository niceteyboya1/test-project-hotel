package cn.gok.hotel.service;

import cn.gok.hotel.entity.RoomOrder;
import java.util.List;


public interface RoomOrderService {
    void createOrder(RoomOrder order);
    List<RoomOrder> listOrdersByMemberId(Integer memberId);
    boolean deleteOrder(Integer orderId, Integer memberId);
    List<RoomOrder> findAll();
    RoomOrder findById(Integer orderId);
    boolean hasOverlapOrder(Integer memberId, java.util.Date enterTime, java.util.Date leaveTime);
    int countOrders();
    List<RoomOrder> getTodayOrders(String today);
}
