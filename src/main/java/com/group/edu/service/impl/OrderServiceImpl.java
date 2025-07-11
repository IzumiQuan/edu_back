package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Order;
import com.group.edu.service.OrderService;
import com.group.edu.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【order】的数据库操作Service实现
* @createDate 2025-07-11 19:33:26
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




