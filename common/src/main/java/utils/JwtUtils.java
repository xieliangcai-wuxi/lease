package utils;

import com.swu.lease.common.ExceptionHandle.LeaseException;
import com.swu.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET = "SDADAFDEFSDFSFS";
    public static String createToken(Long id, String username) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
        String compact = Jwts.builder()
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .setSubject("LOGIN_USER")
                .claim("username", username)
                .claim("id", id)
                .signWith(secretKey, SignatureAlgorithm.ES256)
                .compact();
        return compact;
    }

    public static Claims parseToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return body;
        }catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            e.printStackTrace();
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }


    }

}
