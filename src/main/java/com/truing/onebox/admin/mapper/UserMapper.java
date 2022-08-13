package com.truing.onebox.admin.mapper;


import com.truing.onebox.home.model.dto.User;

public interface UserMapper {
    User selectUserByUsernameAndPwd(String username,String password);
}
