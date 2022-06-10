package com.icarus.service.impl;

import com.icarus.entity.MUser;
import com.icarus.mapper.MUserMapper;
import com.icarus.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author icarus
 * @since 2022-06-05
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

//    @Override
//    public MUser queryUserById(int id) {
//
//        return baseMapper.selectById(id);
//    }
}
