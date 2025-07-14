package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    Member findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
} 