package com.emart.user.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
	
	@Value("token.secret")
	private String secret;
	
	/** Token expire time (minutes)*/
	@Value("token.expire-time")
    private String expireTime;
    
    private static JwtUtil util = new JwtUtil();
    
    public static JwtUtil getInstance() {
    	return util;
    }
    
    private JwtUtil() {
    }
    
    /**
     * Generate token by user id.
     * @param userId
     * @return token string
     */
    public String generateToken(int userId) {
        try {
        	int expireMillis = Integer.parseInt(this.expireTime) * 60 * 1000;
            // Set expiration time
            Date date = new Date(System.currentTimeMillis() + expireMillis);
            // Private key and encryption algorithm
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Set header information
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // Return token string
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
    
    /**
     * Verify token
     * @param token
     * @return user id, if not passed, return 0
     */
    public int verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            int userId = jwt.getClaim("userId").asInt();
            return userId;
        } catch (Exception e){
        	log.error(e.toString());
            return 0;
        }
    }
}