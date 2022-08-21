package com.turing.onebox.admin.service;

import com.turing.onebox.admin.mapper.UserMapper;
import com.turing.onebox.common.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByUsernameAndPwd(String username,String password){
        return userMapper.selectUserByUsernameAndPwd(username, password);
    }





}
