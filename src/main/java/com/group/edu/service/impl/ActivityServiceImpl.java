package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Activity;
import com.group.edu.service.ActivityService;
import com.group.edu.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2025-07-11 19:33:26
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




