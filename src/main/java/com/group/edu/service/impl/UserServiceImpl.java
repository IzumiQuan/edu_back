package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.User;
import com.group.edu.service.UserService;
import com.group.edu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-07-11 21:47:54
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




