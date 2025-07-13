package com.group.edu.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.ClassSelect;
import com.group.edu.service.ClassSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/select")
public class ClassSelectController {
    @Autowired
    private ClassSelectService classSelectService;
    @PostMapping("/add")//活动报名
    public R attend(@RequestBody ClassSelect info){
        classSelectService.save(info);
        return R.success("报名成功");
    }
    @PostMapping("/query")//分页查询
    public R page(@RequestBody SearchCondition<ClassSelect> classSelect){
        IPage<ClassSelect> page = new Page<>(classSelect.getCurrentPage(),classSelect.getPageSize());
        LambdaQueryChainWrapper<ClassSelect> queryWrapper = classSelectService.lambdaQuery();
        ClassSelect example=classSelect.getExample();
        if(example!=null){
                    queryWrapper.like(ClassSelect::getName,example.getName());
        }
        queryWrapper.page(page);
        return R.success(page);
    }
}
