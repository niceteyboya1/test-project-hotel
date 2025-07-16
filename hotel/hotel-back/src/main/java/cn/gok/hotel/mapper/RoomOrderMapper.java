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
}
