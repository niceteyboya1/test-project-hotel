package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.Member;
import cn.gok.hotel.mapper.MemberMapper;
import cn.gok.hotel.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member checkLogin(String username, String password) {
        return memberMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean register(Member member) {
        // 可加用户名唯一性校验
        return memberMapper.insertMember(member) > 0;
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Member findById(Integer memberId) {
        return memberMapper.findById(memberId);
    }

    @Override
    public int countMembers() {
        return memberMapper.countMembers();
    }
}