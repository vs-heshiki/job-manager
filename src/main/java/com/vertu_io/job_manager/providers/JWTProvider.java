package com.vertu_io.job_manager.providers;

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

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
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
}
