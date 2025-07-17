package cn.gok.hotel.service;

import cn.gok.hotel.entity.Admin;
import java.util.List;

public interface AdminService {
    Admin login(String username, String password);
    List<Admin> findAll();
    Admin findById(Integer adminId);
    boolean addAdmin(Admin admin);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(Integer adminId);
} 