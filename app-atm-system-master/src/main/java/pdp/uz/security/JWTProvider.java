package pdp.uz.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
public class JWTProvider {
    private static final long EXPIRE_TIME = 1000 * 60 * 60L;
    private static final String KEY = "secretKey";

    public String generateToken(String login, Collection<GrantedAuthority> roles) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        return Jwts
                .builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public String getLoginFromToken(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
