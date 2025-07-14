package cn.gok.hotel.service;

import cn.gok.hotel.entity.Member;

public interface MemberService {
    Member checkLogin(String username, String password);
} 