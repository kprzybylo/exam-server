package pl.edu.prz.ai.exam.security.infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE)
class JwtService {
    @Value("${app.config.jwt.secret}")
    String secret;
    @Value("${app.config.jwt.expirationTime}")
    Long expirationTime;

    final LoadingCache<String, String> jwtCache = CacheBuilder.newBuilder()
            .expireAfterWrite(expirationTime, TimeUnit.MILLISECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return jwtCache.get(key);
                }
            });


    public String getTokenForUser(UserDetails userDetails) throws IllegalArgumentException, JWTCreationException {
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("role", userDetails.getAuthorities().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        jwtCache.put(userDetails.getUsername(), token);

        return token;
    }

    public String getUserNameFromToken(String token) throws JWTVerificationException {
        String username = JWT.require(Algorithm.HMAC256(secret))
            .build()
            .verify(token)
            .getSubject();

        if (jwtCache.getIfPresent(username) == null) {
            throw new JWTVerificationException("Provided token does not exists in application cache.");
        }

        return username;
    }
}
