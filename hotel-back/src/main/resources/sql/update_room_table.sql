-- 更新房间号表结构
USE test;

-- 添加cleaning_start_time字段
ALTER TABLE room_number ADD COLUMN cleaning_start_time BIGINT NULL COMMENT '清洁开始时间（时间戳）' AFTER status;

-- 添加previous_status字段
ALTER TABLE room_number ADD COLUMN previous_status INT NULL COMMENT '清洁前的状态' AFTER cleaning_start_time;

-- 更新现有数据的status字段注释
ALTER TABLE room_number MODIFY COLUMN status INT DEFAULT 0 COMMENT '0:空闲 1:已入住 2:清洁中 3:已预定'; 