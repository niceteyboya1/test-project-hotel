package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    Member findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    int insertMember(Member member); // 新增
    List<Member> findAll(); // 查询所有会员
    Member findById(@Param("memberId") Integer memberId);
    int countMembers();
    int updateMemberBalance(@Param("memberId") Integer memberId, @Param("balance") java.math.BigDecimal balance);
} 