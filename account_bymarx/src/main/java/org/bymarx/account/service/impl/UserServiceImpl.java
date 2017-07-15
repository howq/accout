package org.bymarx.account.service.impl;

import org.bymarx.account.common.AccountConst;
import org.bymarx.account.dao.wordpress.bymarx.UserBymarxMapper;
import org.bymarx.account.dao.wordpress.bymarx.UsermetaBymarxMapper;
import org.bymarx.account.dao.wordpress.xinminnews.UserNewsMapper;
import org.bymarx.account.dao.wordpress.xinminnews.UsermetaNewsMapper;
import org.bymarx.account.dao.wordpress.xinminxuehui.UserXhMapper;
import org.bymarx.account.dao.wordpress.xinminxuehui.UsermetaXhMapper;
import org.bymarx.account.dto.wordpress.UserInfo;
import org.bymarx.account.model.User;
import org.bymarx.account.model.Usermeta;
import org.bymarx.account.model.UsermetaCollection;
import org.bymarx.account.service.UserService;
import org.bymarx.account.util.DateUtil;
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

    @Resource(name = "usermetaBymarxMapper")
    private UsermetaBymarxMapper usermetaBymarxMapper;

    @Resource(name = "usermetaNewsMapper")
    private UsermetaNewsMapper usermetaNewsMapper;

    @Resource(name = "usermetaXhMapper")
    private UsermetaXhMapper usermetaXhMapper;

    @Override
    public boolean isLogin(String username, String pwd) {

        User user = userBymarxMapper.selectByUserLogin(username);

        String password = user.getUserPass();
        boolean isLogined = false;
        try {
            isLogined = WordpressPasswordHasher.checkPassword(pwd, password);
        } catch (Exception e) {
            return false;
        }
        return isLogined;
    }

    @Override
    public void addUser(UserInfo userInfo, byte domain) {
        User user = new User();
        try {
            user.setUserPass(WordpressPasswordHasher.HashPassword(userInfo.getPass1()));
        } catch (Exception e) {
            return;
        }
        user.setUserLogin(userInfo.getUser_login());
        user.setUserNicename(userInfo.getUser_login());
        user.setUserEmail(userInfo.getEmail());
        user.setUserUrl(userInfo.getUrl());
        user.setUserRegistered(DateUtil.getTimeStamp());
        user.setUserActivationKey("");
        user.setUserStatus(0);
        user.setDisplayName(userInfo.getFirst_name() + userInfo.getLast_name());

        if (domain != AccountConst.DOMAIN_BYMARX) {
            userBymarxMapper.insert(user);
            user = userBymarxMapper.selectByUserLogin(userInfo.getUser_login());

            UsermetaCollection usermetaCollection = new UsermetaCollection(userInfo,domain,user.getId());

            for(Usermeta usermeta : usermetaCollection.getArrayList()){
                usermetaBymarxMapper.insert(usermeta);
            }
        }
        if (domain != AccountConst.DOMAIN_XINMINNEWS) {
            userNewsMapper.insert(user);
            user = userNewsMapper.selectByUserLogin(userInfo.getUser_login());

            UsermetaCollection usermetaCollection = new UsermetaCollection(userInfo,domain,user.getId());

            for(Usermeta usermeta : usermetaCollection.getArrayList()){
                usermetaNewsMapper.insert(usermeta);
            }
        }
        if (domain != AccountConst.DOMAIN_XINMINXUEHUI) {
            userXhMapper.insert(user);
            user = userXhMapper.selectByUserLogin(userInfo.getUser_login());

            UsermetaCollection usermetaCollection = new UsermetaCollection(userInfo,domain,user.getId());

            for(Usermeta usermeta : usermetaCollection.getArrayList()){
                usermetaXhMapper.insert(usermeta);
            }
        }
    }
}