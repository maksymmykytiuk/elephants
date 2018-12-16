package com.maksymmykytiuk.elephants.service.role;

import com.maksymmykytiuk.elephants.dao.RoleDao;
import com.maksymmykytiuk.elephants.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicesImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public void editRole(Role role) {
        roleDao.saveAndFlush(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findByRoleIgnoreCase(name);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDao.getOne(id);
    }

    @Override
    public List<Role> getListRoles() {
        return roleDao.findAll();
    }
}