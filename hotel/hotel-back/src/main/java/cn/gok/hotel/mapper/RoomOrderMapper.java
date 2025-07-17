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
                        @org.apache.ibatis.annotations.Param("enterTime") java.util.Date enterTime,
                        @org.apache.ibatis.annotations.Param("leaveTime") java.util.Date leaveTime);
    int countOrders();
    List<RoomOrder> selectTodayOrders(@org.apache.ibatis.annotations.Param("today") String today);
}
