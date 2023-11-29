package com.vertu_io.job_manager.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {
    public DecodedJWT validateToken(String token, String jwtSecret) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        try {
            var getSubject = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return getSubject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createCompanyToken(String companyId, String jwtSecret) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("vertuJobs")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withSubject(companyId)
                .sign(algorithm);
    }

    public String createCandidateToken(String candidateId, String jwtSecret) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("vertuJobs")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withSubject(candidateId)
                .sign(algorithm);
    }
}
