package com.group.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.edu.common.R;
import com.group.edu.common.SearchCondition;
import com.group.edu.entity.User;
import com.group.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        LambdaQueryChainWrapper<User> lq = userService.lambdaQuery();
        List<User> l = lq.eq(User::getTel, user.getTel()).list();//查询数据库中是否有该用户
        if(l.isEmpty()){
            boolean r = userService.save(user);//用户注册
            if(r){
                return R.success();
            }else{
                return R.fail("注册失败");
            }
        }else{
            l = lq.eq(User::getTel, user.getTel()).eq(User::getPwd, user.getPwd()).list();//查询数据库中该用户密码是否正确
            if(l.isEmpty()){
                return R.fail("密码错误");
            }
            return R.success(l.get(0));
        }
    }

    @PostMapping("/avatar")
    public R avatar(@RequestBody User user){
        LambdaQueryChainWrapper<User> lq = userService.lambdaQuery();
        List<User> l = lq.eq(User::getTel, user.getTel()).list();
        if(l.isEmpty()){
            return R.success();
        }else{
            User u = new User();
            u.setAvatar(l.get(0).getAvatar());
            return R.success(u);
        }
    }

    @PostMapping("/remove")
    public R remove(@RequestParam String id){
        boolean r = userService.removeById(Integer.parseInt(id));//用户注销
        if(r){
            return R.success();
        }
        else {
            return R.fail();
        }
    }

    @PostMapping("/reset")
    public R set(@RequestBody User user){
        boolean r = userService.updateById(user);//用户信息修改
        if(r){
            return R.success(user);
        }
        else{
            return R.fail();
        }
    }

    @PostMapping("/query")
    public R page(@RequestBody SearchCondition<User> user){
        IPage<User> page = new Page<>(user.getCurrentPage(), user.getPageSize());//设置当前页和每页数量
        LambdaQueryChainWrapper<User> lq = userService.lambdaQuery();
        User example = user.getExample();
        if(example!=null){
            lq
                    .eq(example.getId() != null,User::getId, example.getId())//判断id相同
                    .like(StringUtils.isNotEmpty(example.getName()), User::getName, example.getName())//模糊查询姓名
                    .like(StringUtils.isNotEmpty(example.getTel()), User::getTel, example.getTel())//模糊查询手机号
                    .like(StringUtils.isNotEmpty(example.getEmail()), User::getEmail, example.getEmail());//模糊查询邮箱
        }
        lq.page(page);//分页查询
        return R.success(page);
    }
}
