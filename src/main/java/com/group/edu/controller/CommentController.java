package com.group.edu.controller;
import com.group.edu.common.R;
import com.group.edu.entity.Comment;
import com.group.edu.entity.Marking;
import com.group.edu.service.CommentService;
import com.group.edu.service.MarkingService;
import com.group.edu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    private OrderService orderService;
    @Autowired
    private MarkingService markingService;

    @PostMapping("/submitquestion")//联系我们
    public R submitquestion(@RequestBody Comment comment){
        commentService.save(comment);
        if(comment!=null){
            return R.success("提交成功");
        }
        else {
            return R.fail("请填写内容");
        }

    }
    @PostMapping("submitpoint")//课程打分
    public R submitpoint(@RequestBody Marking marking){
        markingService.save(marking);
        if(marking!=null){
            return R.success("提交成功");
        }
        else  {
            return R.fail("请填写内容");
        }
    }

}
