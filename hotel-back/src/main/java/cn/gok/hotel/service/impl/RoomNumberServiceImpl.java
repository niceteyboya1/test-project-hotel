package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.RoomNumber;
import cn.gok.hotel.entity.RoomOrder;
import cn.gok.hotel.mapper.RoomNumberMapper;
import cn.gok.hotel.mapper.RoomOrderMapper;
import cn.gok.hotel.service.RoomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class RoomNumberServiceImpl implements RoomNumberService {
    @Autowired
    private RoomNumberMapper roomNumberMapper;
    
    @Autowired
    private RoomOrderMapper roomOrderMapper;

    @Override
    public RoomNumber getRandomFreeRoom(Integer roomTypeId) {
        List<RoomNumber> freeRooms = roomNumberMapper.findFreeRoomsByType(roomTypeId);
        if (freeRooms == null || freeRooms.isEmpty()) return null;
        return freeRooms.get(new Random().nextInt(freeRooms.size()));
    }

    /**
     * 获取指定房型的空闲房间（排除清洁中）
     */
    public RoomNumber getRandomFreeRoomEx(Integer roomTypeId) {
        List<RoomNumber> freeRooms = roomNumberMapper.findFreeRoomsByType(roomTypeId);
        if (freeRooms == null || freeRooms.isEmpty()) return null;
        // 过滤掉清洁中
        freeRooms.removeIf(r -> r.getStatus() != null && r.getStatus() == 2);
        if (freeRooms.isEmpty()) return null;
        return freeRooms.get(new Random().nextInt(freeRooms.size()));
    }

    @Override
    public void updateRoomStatus(Integer roomNumberId, Integer status) {
        roomNumberMapper.updateRoomStatus(roomNumberId, status);
    }

    @Override
    public List<RoomNumber> findAll() {
        return roomNumberMapper.selectAll();
    }

    @Override
    public int countRooms() {
        return roomNumberMapper.countRooms();
    }
    @Override
    public int countFreeRooms() {
        return roomNumberMapper.countFreeRooms();
    }

    @Override
    public int countFreeRoomsByType(Integer roomTypeId) {
        return roomNumberMapper.countFreeRoomsByType(roomTypeId);
    }

    @Override
    public RoomNumber findById(Integer roomNumberId) {
        // 需要在RoomNumberMapper中实现selectById
        return roomNumberMapper.selectById(roomNumberId);
    }

    @Override
    public void updateRoom(RoomNumber room) {
        // 需要在RoomNumberMapper中实现updateRoom
        roomNumberMapper.updateRoom(room);
    }

    /**
     * 定时任务：将清洁中超过1小时的房间根据原状态恢复
     */
    @Scheduled(fixedRate = 5 * 60 * 1000) // 每5分钟执行一次
    public void autoSetCleaningToFree() {
        List<RoomNumber> allRooms = roomNumberMapper.selectAll();
        long now = System.currentTimeMillis();
        for (RoomNumber room : allRooms) {
            if (room.getStatus() != null && room.getStatus() == 2 && room.getCleaningStartTime() != null) {
                if (now - room.getCleaningStartTime() >= 60 * 60 * 1000) { // 超过1小时
                    // 根据清洁前的状态恢复
                    Integer previousStatus = room.getPreviousStatus();
                    if (previousStatus != null) {
                        room.setStatus(previousStatus);
                        System.out.println("房间 " + room.getRoomNumberName() + " 清洁结束，恢复为状态: " + previousStatus);
                    } else {
                        room.setStatus(0); // 默认设为空闲
                        System.out.println("房间 " + room.getRoomNumberName() + " 清洁结束，设为空闲");
                    }
                    room.setCleaningStartTime(null);
                    room.setPreviousStatus(null);
                    roomNumberMapper.updateRoom(room);
                }
            }
        }
    }

    /**
     * 定时任务：检查已预定房间的入住时间，到达入住时间后自动改为已入住
     */
    @Scheduled(fixedRate = 1 * 60 * 1000) // 每1分钟执行一次
    public void checkReservedRoomsCheckinTime() {
        try {
            // 获取当前日期
            String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            
            // 查询今天应该入住的订单（状态为1表示已预定）
            List<RoomOrder> todayOrders = roomOrderMapper.findTodayCheckinOrders(currentDate);
            
            if (todayOrders != null && !todayOrders.isEmpty()) {
                System.out.println("检查到 " + todayOrders.size() + " 个今天需要入住的订单");
                
                for (RoomOrder order : todayOrders) {
                    try {
                        // 更新订单状态为已入住（状态2）
                        order.setOrderStatus(2);
                        roomOrderMapper.updateOrder(order);
                        
                        // 更新房间状态为已入住（状态1）
                        RoomNumber room = roomNumberMapper.selectById(order.getRoomNumberId());
                        if (room != null && room.getStatus() != null && room.getStatus() == 3) { // 已预定状态
                            room.setStatus(1); // 改为已入住
                            roomNumberMapper.updateRoom(room);
                            System.out.println("✓ 房间 " + room.getRoomNumberName() + " 到达入住时间，自动改为已入住");
                        } else if (room != null) {
                            System.out.println("⚠ 房间 " + room.getRoomNumberName() + " 当前状态不是已预定，跳过更新");
                        } else {
                            System.out.println("⚠ 未找到订单对应的房间信息，订单ID: " + order.getOrderId());
                        }
                    } catch (Exception e) {
                        System.err.println("处理订单 " + order.getOrderId() + " 时出错: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("今天没有需要入住的订单");
            }
        } catch (Exception e) {
            System.err.println("检查已预定房间入住时间时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

