package com.icarus.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icarus.common.dto.LoginDto;
import com.icarus.common.lang.Result;
import com.icarus.entity.MUser;
import com.icarus.service.MUserService;
import com.icarus.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    MUserService userService;

    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/user/login")
    public Result userLogin(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        MUser user = userService.getOne(new QueryWrapper<MUser>().eq("username", loginDto.getUsername()));//通过用户名查找
        Assert.notNull(user,"用户不存在");//用户不存在则断言输出
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {//进行MD5加密，校验错误返回结果集
            return Result.fail("密码校验错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());//将userJwt注入响应头文件中
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("password", user.getPassword())
                .put("avatar", user.getAvatar())
                .map());
    }

    @GetMapping("/user/logout")
    @RequiresAuthentication//注销操作需要经过shiro验证
    public Result userLogout() {
        SecurityUtils.getSubject().logout();//通过shiro进行注销
        return Result.success(null);
    }

}
