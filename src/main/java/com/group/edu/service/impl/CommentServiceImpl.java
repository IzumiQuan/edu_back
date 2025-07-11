package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Comment;
import com.group.edu.service.CommentService;
import com.group.edu.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2025-07-11 19:33:26
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




