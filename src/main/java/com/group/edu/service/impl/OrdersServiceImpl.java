package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Orders;
import com.group.edu.service.OrdersService;
import com.group.edu.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2025-07-15 18:47:27
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




