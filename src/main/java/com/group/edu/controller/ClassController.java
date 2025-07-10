package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.Class;
import com.group.edu.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @PostMapping("/add")
    public R add(@RequestBody Class c) {
        boolean r = classService.save(c);//用户注册
        if (r) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/remove")
    public R remove(@RequestParam String id){
        boolean r = classService.removeById(Integer.parseInt(id));//用户注销
        if(r){
            return R.success();
        }
        else {
            return R.fail();
        }
    }

    @PostMapping("/reset")
    public R set(@RequestBody Class c){
        boolean r = classService.updateById(c);//用户信息修改
        if(r){
            return R.success(c);
        }
        else{
            return R.fail();
        }
    }

    @PostMapping("/query")
    public R page(@RequestBody SearchCondition<Class> c){
        IPage<Class> page = new Page<>(c.getCurrentPage(), c.getPageSize());//设置当前页和每页数量
        LambdaQueryChainWrapper<Class> lq = classService.lambdaQuery();
        Class example = c.getExample();
        lq.in(c.getList() != null, Class::getSubject, c.getList());
        if(!c.getRange().isEmpty())
            lq.between(Class::getClassHour, c.getRange().get(0), c.getRange().get(1));
        if(example!=null){
            lq
                    .eq(example.getId() != null,Class::getId, example.getId())//判断id相同
                    .like(StringUtils.isNotEmpty(example.getName()), Class::getName, example.getName());//模糊查询名称
        }
        if(Objects.equals(c.getOrderBy(), "asc")){
            lq.orderByAsc(Class::getCreateTime);
        }
        else if(Objects.equals(c.getOrderBy(), "desc")){
            lq.orderByDesc(Class::getCreateTime);
        }
        lq.page(page);//分页查询
        return R.success(page);
    }
}
