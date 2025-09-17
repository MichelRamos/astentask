package michel.astentask.config;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import michel.astentask.entities.User;

@Service
public class TokenService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    private SecretKey key;
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
            .issuer("astentask")
            .subject(user.getEmail())
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusMillis(jwtExpiration)))
            .signWith(key)
            .compact();
    }

    public String getNameFromToken(String token) {
        return Jwts.parser()
            .verifyWith(key).build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException e){
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e){
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e){
            System.out.println("JWT token is expired " + e.getMessage());
        } catch (UnsupportedJwtException e){
            System.out.println("JWT token is unsupported " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty " + e.getMessage());
        }
        return false;
    }
}