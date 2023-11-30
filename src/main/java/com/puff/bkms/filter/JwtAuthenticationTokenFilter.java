package com.puff.bkms.filter;

import com.puff.bkms.model.dto.user.UserDetail;
import com.puff.bkms.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
 * @author: Puff
 * @date: 2023/11/23 下午1:26
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;
    /**
     * 从请求中获取 JWT 令牌，
     * 并根据令牌获取用户信息，最后将用户信息封装到 Authentication 中，方便后续校验（只会执行一次）
     * 逻辑上有点不理解，所有请求都会经过OncePerRequestFilter 但是在未登录的时候才走jwtFilter
     * 可是登录之后登录状态被保持在SecurityContext
     * 用户下次请求也不需要token啊？
     *
     * @param request:
     * @param response:
     * @param filterChain:
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(tokenHeader);
        log.info(LOG_PRE+"JwtAuthenticationTokenFilter");
        // 如果携带了token则直接走自定义拦截器的拦截方法
        if(token != null) {
            String username = JwtUtil.parseJwtGetSubject(token);
            // 如果用户名不为空，并且 SecurityContextHolder 中的 Authentication 为空（表示该用户未登录）
            log.info(LOG_PRE+"Authentication "+SecurityContextHolder.getContext().getAuthentication());
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                // 从数据库中加载用户信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 如果 JWT 令牌有效
                if (JwtUtil.validateToken(token,userDetails)) {
                    // 将用户信息封装到 UsernamePasswordAuthenticationToken 对象中（即：Authentication）
                    // 参数：用户信息、密码（因为 JWT 令牌中没有密码，所以这里传 null）、用户权限
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    // 将请求中的详细信息（即：IP、SessionId 等）封装到 UsernamePasswordAuthenticationToken 对象中方便后续校验
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 记录日志
                    log.info("authenticated user:{}", username);
                    // 将 UsernamePasswordAuthenticationToken 对象封装到 SecurityContextHolder 中方便后续校验
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}

