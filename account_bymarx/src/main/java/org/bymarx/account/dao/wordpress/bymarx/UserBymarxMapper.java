package org.bymarx.account.dao.wordpress.bymarx;

import org.bymarx.account.model.User;

public interface UserBymarxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUserLogin(String userLogin);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}