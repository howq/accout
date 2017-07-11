package org.bymarx.account.dao;

import org.bymarx.account.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUserLogin(String userLogin);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}