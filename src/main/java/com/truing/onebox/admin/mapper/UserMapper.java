package com.truing.onebox.admin.mapper;


import com.truing.onebox.common.model.dto.User;


public interface UserMapper{
    User selectUserByUsernameAndPwd(String username,String password);
}
