package com.icarus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author icarus
 * @since 2022-06-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_user")
public class MUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "用户名不得为空")
    private String username;

    private String avatar;

    @NotBlank(message = "email不得为空")
    @Email(message = "email格式出错")
    private String email;

    private String password;

    private Integer status;

    private LocalDateTime created;

    private LocalDateTime lastLogin;


}
