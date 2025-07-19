package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.RoomOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomOrderMapper {
    void insertOrder(RoomOrder order);
    List<RoomOrder> selectByMemberId(Integer memberId);
    RoomOrder selectById(Integer orderId);
    int deleteById(Integer orderId);
    List<RoomOrder> selectAll();
    int countOverlapOrder(@org.apache.ibatis.annotations.Param("memberId") Integer memberId,
                        @org.apache.ibatis.annotations.Param("enterTime") java.sql.Date enterTime,
                        @org.apache.ibatis.annotations.Param("leaveTime") java.sql.Date leaveTime);
    int countOrders();
    List<RoomOrder> selectTodayOrders(@org.apache.ibatis.annotations.Param("today") String today);
    
    /**
     * 查找今天应该入住的订单
     */
    List<RoomOrder> findTodayCheckinOrders(@org.apache.ibatis.annotations.Param("checkinDate") String checkinDate);
    
    /**
     * 更新订单
     */
    void updateOrder(RoomOrder order);
}
