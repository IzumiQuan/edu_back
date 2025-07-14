package com.group.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group.edu.entity.Bill;
import com.group.edu.service.BillService;
import com.group.edu.mapper.BillMapper;
import org.springframework.stereotype.Service;

/**
* @author 21809
* @description 针对表【bill】的数据库操作Service实现
* @createDate 2025-07-14 15:28:44
*/
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
    implements BillService{

}




