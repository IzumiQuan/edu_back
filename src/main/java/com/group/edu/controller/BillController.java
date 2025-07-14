package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.Bill;
import com.group.edu.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping("/add")
    public R add(@RequestBody Bill b) {
        boolean r = billService.save(b);
        if (r) {
            return R.success(b);
        } else {
            return R.fail();
        }
    }

    @PostMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        boolean r = billService.removeById(Integer.parseInt(id));
        if(r){
            return R.success();
        }
        else {
            return R.fail();
        }
    }

    @PostMapping("/reset")
    public R set(@RequestBody Bill b){
        boolean r = billService.updateById(b);
        if(r){
            return R.success(b);
        }
        else{
            return R.fail();
        }
    }

    @PostMapping("/query")
    public R page(@RequestBody SearchCondition<Bill> b){
        IPage<Bill> page = new Page<>(b.getCurrentPage(), b.getPageSize());//设置当前页和每页数量
        LambdaQueryChainWrapper<Bill> lq = billService.lambdaQuery();
        Bill example = b.getExample();
        if(example!=null){
            lq
                    .eq(example.getId() != null,Bill::getId, example.getId())//判断id相同
                    .eq(StringUtils.isNotEmpty(example.getStatus()), Bill::getStatus, example.getStatus());//模糊查询名称
        }
        lq.page(page);//分页查询
        return R.success(page);
    }
}