package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.RoomNumber;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomNumberMapper {
    List<RoomNumber> findFreeRoomsByType(Integer roomTypeId);
    void updateRoomStatus(@Param("roomNumberId") Integer roomNumberId, @Param("status") Integer status);
    List<RoomNumber> selectAll();
    int countRooms();
    int countFreeRooms();
    int countFreeRoomsByType(Integer roomTypeId);
    RoomNumber selectById(Integer roomNumberId);
    void updateRoom(RoomNumber room);
}
