package com.icarus.mapper;

import com.icarus.entity.MUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author icarus
 * @since 2022-06-05
 */
public interface MUserMapper extends BaseMapper<MUser> {

    @Override
    MUser selectById(Serializable id);


}
