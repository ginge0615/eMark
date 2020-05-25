package com.emart.zuul.filter;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class RequestFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	private static final String INVALID_TOKEN = "invalid token";
	
	@Value("${zuul.shouldNotFilter}")
	private String shouldNotFilter;
	
	@Value("${token.secret}")
    private String secret;

	@Override
	public String filterType() {
		//Before rooting
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		//First filter
		return 0;
	}
	
	@Override
	public boolean shouldFilter() {
		if (StringUtils.isEmpty(shouldNotFilter)) {
			return true;
		}
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String url = request.getRequestURI();
		logger.info(">>>url=" + url);
		
		List<String> lstNoTokenValid = Arrays.asList(shouldNotFilter.split(","));
		
		for (String noTolenValid : lstNoTokenValid) {
			if (noTolenValid.endsWith("/**")) {
				if (url.startsWith(noTolenValid.substring(0, noTolenValid.length() - 3))) {
					return false;
				}
			} else {
				if (url.equals(noTolenValid) && request.getMethod().equals("GET")) {
					return false;
				}
			}
		}
        
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info(">>>run start");
		logger.info("secret=" + secret);
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    
	    //If pre request, pass
	    if(request.getMethod().equals("OPTIONS")){
	    	ctx.setSendZuulResponse(true);
	    	return null;
        }
	    
	    String token = request.getHeader("Authorization");
	    
	    if (StringUtils.isEmpty(token) || token.length() <= 7) {
            setUnauthorizedResponse(ctx, INVALID_TOKEN);
        } else {
        	//Remove the header 'Bearer ' from token
        	token = token.substring(7);
        	
            if (verifyToken(token) > 0) {
            	ctx.setSendZuulResponse(true);
            } else {
            	ctx.setSendZuulResponse(false);
            	setUnauthorizedResponse(ctx, INVALID_TOKEN);
            }
        }
	    
		return null;
	}

	
	/**
	 * Set unauthorized response
	 * @param requestContext
	 * @param msg
	 */
    private void setUnauthorizedResponse(RequestContext requestContext, String msg) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        requestContext.setResponseBody(msg);
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
        	logger.error(e.toString());
            return 0;
        }
    }
}
