package com.turing.onebox.admin.mapper;


import com.turing.onebox.common.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper{
    User selectUserByUsernameAndPwd(@Param("username") String username, @Param("password") String password);
}
