package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.ActivityEnroll;
import com.group.edu.service.ActivityEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/enroll")
public class ActivityEnrollController {
    @Autowired
    private ActivityEnrollService activityEnrollService;
    @PostMapping("/add")//活动报名
    public R attend(@RequestBody ActivityEnroll info){
        boolean r = activityEnrollService.save(info);
        if(r){
            SearchCondition<ActivityEnroll> ae = new SearchCondition<>();
            ae.setExample(info);
            Object data = page(ae).getData();
            if(data instanceof IPage<?>) {
                IPage<ActivityEnroll> page = (IPage<ActivityEnroll>) data;
                return R.success(page.getRecords().get(0));
            }
        }
        return R.fail();
    }
    @PostMapping("/query")//分页查询
    public R page(@RequestBody SearchCondition<ActivityEnroll> ae){
        IPage<ActivityEnroll> page = new Page<>(ae.getCurrentPage(),ae.getPageSize());
        LambdaQueryChainWrapper<ActivityEnroll> queryWrapper = activityEnrollService.lambdaQuery();
        ActivityEnroll example = ae.getExample();
        if(example!=null){
            queryWrapper
                    .like(StringUtils.isNotEmpty(example.getName()),ActivityEnroll::getName,example.getName())
                    .like(StringUtils.isNotEmpty(example.getTel()),ActivityEnroll::getTel,example.getTel())
                    .like(StringUtils.isNotEmpty(example.getSex()),ActivityEnroll::getSex,example.getSex())
                    .like(StringUtils.isNotEmpty(example.getActivity()),ActivityEnroll::getActivity,example.getActivity());
        }
        queryWrapper.page(page);
        return R.success(page);
    }
}
