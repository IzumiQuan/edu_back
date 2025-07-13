package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.entity.Order;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.service.OrderService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/query")//分页查询
    public R page(@RequestBody SearchCondition<Order> order){
        IPage<Order> page =new Page<>(order.getCurrentPage(),order.getPageSize());
        LambdaQueryChainWrapper<Order> queryWrapper = orderService.lambdaQuery();
        Order example = order.getExample();
        if(example!=null){
            queryWrapper.eq(Order::getName,example.getName())
                    .like(StringUtils.isNotEmpty(example.getClassName()),Order::getClassName,example.getClassName())
                    .like(StringUtils.isNotEmpty(example.getCode()),Order::getCode,example.getCode());

        }
        queryWrapper.page(page);
        return R.success(page);

    }
    @PostMapping("/pay")//支付，取消订单（改变订单状态）
    public R pay(Order order){
        boolean r=orderService.updateById(order);
        if(r){
            return R.success(order);
        }
        else {
            return R.fail();
        }

    }
}
