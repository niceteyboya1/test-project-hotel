package cn.gok.hotel.service;

import cn.gok.hotel.entity.Member;

import java.util.List;

public interface MemberService {
    Member checkLogin(String username, String password);
    boolean register(Member member); // 新增
    List<Member> findAll(); // 查询所有会员
    Member findById(Integer memberId);
    int countMembers();
} 