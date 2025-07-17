-- 创建数据库
CREATE DATABASE IF NOT EXISTS test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE test;

-- 创建会员表
CREATE TABLE IF NOT EXISTS member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    id_card_no VARCHAR(20),
    balance DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建管理员表
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    role INT DEFAULT 2 COMMENT '1:超级管理员 2:普通管理员',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login_time TIMESTAMP NULL
);

-- 创建房型表
CREATE TABLE IF NOT EXISTS room_type (
    room_type_id INT PRIMARY KEY AUTO_INCREMENT,
    room_type_name VARCHAR(50) NOT NULL,
    room_type_desc TEXT,
    price DECIMAL(10,2) NOT NULL,
    capacity INT DEFAULT 2,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建房间号表
CREATE TABLE IF NOT EXISTS room_number (
    room_number_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number_name VARCHAR(20) NOT NULL UNIQUE,
    room_type_id INT NOT NULL,
    room_status INT DEFAULT 0 COMMENT '0:空闲 1:已入住',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id)
);

-- 创建订单表
CREATE TABLE IF NOT EXISTS room_order (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    member_name VARCHAR(50) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    order_status INT DEFAULT 1 COMMENT '1:已预订 2:已入住 3:已完成 4:已取消',
    checkin_time VARCHAR(50) NOT NULL,
    enter_time DATE NOT NULL,
    leave_time DATE NOT NULL,
    room_type_id INT NOT NULL,
    room_number_id INT NOT NULL,
    room_number_name VARCHAR(20) NOT NULL,
    guest_id_info TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id),
    FOREIGN KEY (room_number_id) REFERENCES room_number(room_number_id)
);

-- 创建酒店信息表
CREATE TABLE IF NOT EXISTS hotel_info (
    hotel_id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_name VARCHAR(100) NOT NULL,
    hotel_desc TEXT,
    longitude DOUBLE,
    latitude DOUBLE,
    business_license_no VARCHAR(50),
    legal_person VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    has_breakfast BOOLEAN DEFAULT FALSE,
    has_parking BOOLEAN DEFAULT FALSE
);

-- 插入默认管理员账号
INSERT INTO admin (username, password, real_name, phone, email, role) VALUES
('admin', '123456', '系统管理员', '13801380000', 'admin@gkhd.com', 1);

-- 插入示例房型数据
INSERT INTO room_type (room_type_name, room_type_desc, price, capacity) VALUES
('标准单人间', '舒适的单人房间，配备基本设施', 188.00, 1),
('标准双人间', '温馨的双人房间，适合情侣或朋友', 268.00, 2),
('豪华套房', '宽敞的套房，配备高级设施', 588.00, 4);

-- 插入示例房间数据
INSERT INTO room_number (room_number_name, room_type_id, room_status) VALUES
('101', 1, 0),
('102', 1, 0),
('103', 1, 0),
('104', 1, 0),
('201', 2, 0),
('202', 2, 0),
('203', 2, 0),
('204', 2, 0),
('301', 3, 0),
('302', 3, 0);

-- 插入酒店信息
INSERT INTO hotel_info (hotel_name, hotel_desc, longitude, latitude, business_license_no, legal_person, has_breakfast, has_parking) VALUES
('金凯大酒店', '位于市中心的豪华酒店，提供优质的服务和舒适的住宿环境', 116.397128, 39.916527, 'L123456789', '张三', TRUE, TRUE); 

-- 创建通知表
CREATE TABLE IF NOT EXISTS notice (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    notice_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);