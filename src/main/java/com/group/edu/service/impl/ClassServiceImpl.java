package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Class;
import com.group.edu.service.ClassService;
import com.group.edu.mapper.ClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【class(课程表)】的数据库操作Service实现
* @createDate 2025-07-12 22:13:01
*/
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class>
    implements ClassService{

}




