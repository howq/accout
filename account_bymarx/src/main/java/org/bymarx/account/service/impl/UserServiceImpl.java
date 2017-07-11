package org.bymarx.account.service.impl;

import org.bymarx.account.dao.UserMapper;
import org.bymarx.account.model.User;
import org.bymarx.account.service.UserService;
import org.bymarx.account.util.WordpressPasswordHasher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author howq
 * @create 2017-07-11 下午4:14
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name="userMapper")
    private UserMapper userMapper;

    @Override
    public boolean isLogin(String username, String pwd) {

        User user = userMapper.selectByUserLogin(username);
        String password = user.getUserPass();
        boolean isLogined;
        try {
            isLogined = WordpressPasswordHasher.checkPassword(pwd, password);
        }catch (Exception e){

            return false;
        }
        return isLogined;
    }
}