package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.Activity;
import com.group.edu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @PostMapping("/add")
    public R add(@RequestBody Activity a) {
        boolean r = activityService.save(a);
        if (r) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        boolean r = activityService.removeById(Integer.parseInt(id));
        if(r){
            return R.success();
        }
        else {
            return R.fail();
        }
    }

    @PostMapping("/reset")
    public R set(@RequestBody Activity a){
        boolean r = activityService.updateById(a);
        if(r){
            return R.success(a);
        }
        else{
            return R.fail();
        }
    }

    @PostMapping("/query")
    public R page(@RequestBody SearchCondition<Activity> a){
        IPage<Activity> page = new Page<>(a.getCurrentPage(), a.getPageSize());//设置当前页和每页数量
        LambdaQueryChainWrapper<Activity> lq = activityService.lambdaQuery();
        Activity example = a.getExample();
        if(example!=null){
            lq
                    .eq(example.getId() != null,Activity::getId, example.getId())//判断id相同
                    .like(StringUtils.isNotEmpty(example.getName()), Activity::getName, example.getName());//模糊查询名
            if (example.getStartTime() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(example.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date start = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                Date end = calendar.getTime();
                lq.between(Activity::getStartTime, start, end);
            }

        }
        lq.page(page);//分页查询
        return R.success(page);
    }
}
