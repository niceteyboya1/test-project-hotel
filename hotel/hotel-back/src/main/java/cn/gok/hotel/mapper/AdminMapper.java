package cn.gok.hotel.mapper;

import cn.gok.hotel.entity.Admin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AdminMapper {
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password")String password);
    List<Admin> findAll();
    Admin findById(Integer adminId);
    int insertAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(Integer adminId);
} 