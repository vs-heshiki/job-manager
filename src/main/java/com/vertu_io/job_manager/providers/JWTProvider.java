package com.vertu_io.job_manager.providers;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {

    @Value("${security.secret.key}")
    private String jwtSecret;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
        try {
            var getSubject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return getSubject;
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    public String createToken(String companyId) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
        return JWT.create()
                .withIssuer("vertuJobs")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(companyId)
                .sign(algorithm);
    }
}
