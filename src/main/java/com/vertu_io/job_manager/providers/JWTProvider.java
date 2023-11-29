package com.vertu_io.job_manager.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {
    public String validateToken(String token, String jwtSecret) {
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

    public String createCompanyToken(String companyId, String jwtSecret) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("vertuJobs")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(companyId)
                .sign(algorithm);
    }

    public String createCandidateToken(String candidateId, String jwtSecret) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("vertuJobs")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("candidate"))
                .withSubject(candidateId)
                .sign(algorithm);
    }
}
