package com.maksymmykytiuk.elephants.service.user;

import com.maksymmykytiuk.elephants.dao.UserDao;
import com.maksymmykytiuk.elephants.model.user.User;
import com.maksymmykytiuk.elephants.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleService roleService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.saveAndFlush(user);
    }

    @Override
    public boolean validEmail(String email) {
        String query = "select case when (count(e) > 0)  then true else false end " +
                "from User e where LOWER(e.email) = LOWER(:email) and e.enabled";

        TypedQuery<Boolean> booleanQuery = em.createQuery(query, Boolean.class);
        return booleanQuery.getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
