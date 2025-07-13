package com.group.edu.controller;

import com.group.edu.common.R;
import com.group.edu.entity.Comment;
import com.group.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")//联系我们
    public R add(@RequestBody Comment comment){
        commentService.save(comment);
        if(comment!=null){
            return R.success();
        }
        else {
            return R.fail("提交错误");
        }
    }
}
