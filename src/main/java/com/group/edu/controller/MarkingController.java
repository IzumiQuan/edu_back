package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.Marking;
import com.group.edu.service.MarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/marking")
public class MarkingController {
    @Autowired
    MarkingService markingService;

    @PostMapping("/add")
    public R add(@RequestBody Marking m) {
        boolean r = markingService.save(m);
        if (r) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        boolean r = markingService.removeById(Integer.parseInt(id));
        if(r){
            return R.success();
        }
        else {
            return R.fail();
        }
    }

    @PostMapping("/reset")
    public R set(@RequestBody Marking m){
        boolean r = markingService.updateById(m);
        if(r){
            return R.success(m);
        }
        else{
            return R.fail();
        }
    }

    @PostMapping("/query")
    public R page(@RequestBody SearchCondition<Marking> m){
        IPage<Marking> page = new Page<>(m.getCurrentPage(), m.getPageSize());//设置当前页和每页数量
        LambdaQueryChainWrapper<Marking> lq = markingService.lambdaQuery();
        Marking example = m.getExample();
        if(example!=null){
            lq
                    .eq(example.getId() != null,Marking::getId, example.getId())//判断id相同
                    .like(StringUtils.isNotEmpty(example.getName()), Marking::getName, example.getName());//模糊查询名称
        }
        lq.page(page);//分页查询
        return R.success(page);
    }
}

