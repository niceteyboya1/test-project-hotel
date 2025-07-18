package cn.gok.hotel.service.impl;

import cn.gok.hotel.entity.Admin;
import cn.gok.hotel.mapper.AdminMapper;
import cn.gok.hotel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        return adminMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public Admin findById(Integer adminId) {
        return adminMapper.findById(adminId);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin) > 0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin) > 0;
    }

    @Override
    public boolean deleteAdmin(Integer adminId) {
        return adminMapper.deleteAdmin(adminId) > 0;
    }
} 