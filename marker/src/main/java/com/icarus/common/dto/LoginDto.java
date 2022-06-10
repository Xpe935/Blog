package com.icarus.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data

public class LoginDto implements Serializable {

    @NotBlank(message = "用户名不得为空")
    private String username;

    @NotBlank(message = "密码不得为空")
    private String password;
}
