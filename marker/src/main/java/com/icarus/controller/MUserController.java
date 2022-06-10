package com.icarus.controller;

import com.icarus.entity.MUser;
import com.icarus.service.MUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.icarus.common.lang.Result.success;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author icarus
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/user")

public class MUserController {
    @Autowired
    private MUserService userService;

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Object userTest (@PathVariable("id") int id){
        MUser user = userService.getById(id);
        return success(user);
    }
}
