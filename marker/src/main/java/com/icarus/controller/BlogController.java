package com.icarus.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icarus.common.lang.Result;
import com.icarus.entity.MBlog;
import com.icarus.service.MBlogService;
import com.icarus.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author icarus
 * @since 2022-06-05
 */
@RestController
public class BlogController {

    @Autowired
    MBlogService blogService;

    //查询全部列表分页查询方法
    @GetMapping("/blogs")
    public Result getBlogList(@RequestParam(defaultValue = "1") Integer cutterPage) {
        Page page = new Page(cutterPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<MBlog>().orderByDesc("created"));
        return Result.success(pageData);
    }
    //通过id查询单个对象方法
    @GetMapping("/blog/{id}")
    public Result getOneBlog(@PathVariable String id) {
        MBlog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客不存在");
        return Result.success(blog);
    }


    //编辑操作， 无id添加，有id编辑
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result editBlog(@Validated @RequestBody MBlog blog) {
        MBlog buf = null;
        if (blog.getId() != null){//检验传入blog是否存在,如果存在便使用更新，否则是保存
            buf = blogService.getById(blog.getId());
            Assert.isTrue(buf.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");//验证是否有编辑权限
        } else {
            buf = new MBlog();
            buf.setUserId(ShiroUtil.getProfile().getId());
            buf.setCreated(LocalDateTime.now());
            buf.setStatus(0);
        }
        BeanUtil.copyProperties(blog, buf, "id", "userid", "created", "status");//将内容复制
        return Result.success(blogService.saveOrUpdate(buf));
    }


    @RequiresAuthentication
    @GetMapping("/blog/delete/{id}")
    public Result deleteBlog(@PathVariable Long id) {
        boolean flag = blogService.removeById(id);
        if (flag) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

}
