package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Order;
import com.group.edu.service.OrderService;
import com.group.edu.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 31822
* @description 针对表【order】的数据库操作Service实现
* @createDate 2025-07-09 23:09:16
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




