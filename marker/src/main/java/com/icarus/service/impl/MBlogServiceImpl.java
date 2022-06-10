package com.icarus.service.impl;

import com.icarus.entity.MBlog;
import com.icarus.mapper.MBlogMapper;
import com.icarus.service.MBlogService;
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
public class MBlogServiceImpl extends ServiceImpl<MBlogMapper, MBlog> implements MBlogService {


}
