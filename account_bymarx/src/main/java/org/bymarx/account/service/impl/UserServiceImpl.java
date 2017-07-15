package org.bymarx.account.service.impl;

import org.bymarx.account.dao.wordpress.bymarx.UserBymarxMapper;
import org.bymarx.account.dao.wordpress.xinminnews.UserNewsMapper;
import org.bymarx.account.dao.wordpress.xinminxuehui.UserXhMapper;
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

    @Resource(name = "userBymarxMapper")
    private UserBymarxMapper userBymarxMapper;

    @Resource(name = "userNewsMapper")
    private UserNewsMapper userNewsMapper;

    @Resource(name = "userXhMapper")
    private UserXhMapper userXhMapper;

    @Override
    public boolean isLogin(String username, String pwd) {

        User user = userBymarxMapper.selectByUserLogin(username);
        User user1 = userXhMapper.selectByUserLogin(username);
        User user2 = userNewsMapper.selectByUserLogin(username);
        String password = user.getUserPass();
        String password1 = user1.getUserPass();
        String password2 = user2.getUserPass();
        boolean isLogined;
        try {
            isLogined = WordpressPasswordHasher.checkPassword(pwd, password);
        } catch (Exception e) {
            return false;
        }
        return isLogined;
    }
}