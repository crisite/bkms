package com.puff.bkms.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Puff
 * @date: 2023/11/19 下午12:37
 */
@Data
public class UserDetail implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    // 用户拥有角色列表
    private List<String> roleList;
    // 用户权限列表
    private List<String> powerList;
    // security识别的权限
    private List<GrantedAuthority> authList;
    // 用户最后一次登录时间
    private Date last_login;


    //JsonIgnore在接口传递时隐藏敏感信息
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //已赋值权限则直接返回
        if (authList == null) {
            return authList;
        }
        // 赋值
        authList = powerList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authList;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
