package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.Orders;
import com.group.edu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService orderService;
    @PostMapping("/query")//分页查询
    public R page(@RequestBody SearchCondition<Orders> order){
        IPage<Orders> page = new Page<>(order.getCurrentPage(),order.getPageSize());
        LambdaQueryChainWrapper<Orders> queryWrapper = orderService.lambdaQuery();
        Orders example = order.getExample();
        if(example!=null){
            queryWrapper.eq(StringUtils.isNotEmpty(example.getName()),Orders::getName,example.getName())
                    .eq(StringUtils.isNotEmpty(example.getStatus()),Orders::getStatus,example.getStatus())
                    .like(StringUtils.isNotEmpty(example.getClassName()),Orders::getClassName,example.getClassName())
                    .like(StringUtils.isNotEmpty(example.getCode()),Orders::getCode,example.getCode());
        }
        queryWrapper.page(page);
        return R.success(page);
    }
    @PostMapping("/pay")//支付，取消订单（改变订单状态）
    public R pay(Orders order){
        boolean r=orderService.updateById(order);
        if(r){
            return R.success(order);
        }
        else {
            return R.fail();
        }
    }
}
