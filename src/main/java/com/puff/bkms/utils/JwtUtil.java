package com.puff.bkms.utils;

import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.BusinessException;
import com.puff.bkms.model.dto.user.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Jwt工具库
 *
 * @author: Puff
 * @date: 2023/11/20 上午3:14
 */
@Slf4j
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL =3 * 60 * 60 *1000L;//3 * 60 * 60 *1000  三个小时
    //设置秘钥明文
    public static final String JWT_KEY = "puff";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间,不设置就为默认的三个小时
        return builder.compact();
    }

    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
        String token = createJWT("dede");  // JWT可以支持JSON格式的加密
        System.out.println(token);
        // System.out.println("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhMTdmMjgxM2M2Mzc0MGVmYTc3MGQyYTczZGZmNDkyMSIsInN1YiI6IjEyMzQ1NiIsImlzcyI6InNnIiwiaWF0IjoxNjQ3MzQ3MTMyLCJleHAiOjE2NDczNTA3MzJ9.blDSBULCSg-1qHFzGYSrUwoHNAdkQzy-6xFqw3w_ep0");
        // Claims claims = parseJWT(token);   // 对加密后的数据进行解密
        // Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0MDUyMTg5ZTMwNWI0YzVjOTFmYWVhNDUyYzg2ZWJiMiIsInN1YiI6IjEyMzQ1NiIsImlzcyI6InNnIiwiaWF0IjoxNjQ3Mzk3Mzg4LCJleHAiOjE2NDc0MDA5ODh9.X_pKcZgmP_bU_JRrsQEXNNmaTsbeeX6jESC-4QGhSiQ");   // 对加密后的数据进行解密
        // System.out.println(claims.getSubject());  // 获取解密的详细内容
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 解析token获取subject
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseJwtGetSubject(String token){
        String username;
        try {
            Claims claims = parseJWT(token);
            username = claims.getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 验证 token 是否有效
     * @param token JWT 的 token
     * @param userDetail 从数据库中查询出来的用户信息（需要自定义 UserDetailsService 和 UserDetails）
     * @return token 是否有效 true：有效 false：无效
     */
    public static boolean validateToken(String token, UserDetails userDetails){
        // 从 token 中获取用户名
        String username = null;
        try {
            username = parseJwtGetSubject(token);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"无效Token");
        }
        // 条件一：用户名不为 null
        // 条件二：用户名和 UserDetail 中的用户名一致
        // 条件三：token 未过期
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 验证 token 是否过期
     * @param token JWT 的 token
     * @return token 是否过期 true：过期 false：未过期
     */
    private static boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从 token 中获取过期时间
     * @param token JWT 的 token
     * @return 过期时间
     */
    private static Date getExpiredDateFromToken(String token){
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 从 token 中获取 JWT 中的负载
     * @param token JWT 的 token
     * @return JWT 中的负载
     */
    private static Claims getClaimsFromToken(String token){
        SecretKey secretKey = generalKey();
        Claims claims = null;
        try{
            claims = Jwts.parser() // 解析 JWT 的 token
                    .setSigningKey(secretKey) // 指定签名使用的密钥（会自动推断签名的算法）
                    .parseClaimsJws(token) // 解析 JWT 的 token
                    .getBody(); // 获取 JWT 的负载（即要传输的数据）
        }catch (Exception e){
            log.info("JWT 格式验证失败：{}",token);
        }
        return claims;
    }

}
