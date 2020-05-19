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
import com.auth0.jwt.algorithms.Algorithm;
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
		logger.debug(">>>url=" + url);
		
		List<String> lstNoTokenValid = Arrays.asList(shouldNotFilter.split(","));
		
		for (String noTolenValid : lstNoTokenValid) {
			if (url.startsWith(noTolenValid)) {
				return false;
			}
		}
        
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.debug(">>>run start");
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    
	    //If pre request, pass
	    if(request.getMethod().equals("OPTIONS")){
	    	ctx.setSendZuulResponse(true);
	    	return null;
        }
	    
	    String token = request.getHeader("Authorization");
	    
	    if (StringUtils.isEmpty(token)) {
            setUnauthorizedResponse(ctx, INVALID_TOKEN);
        } else {
            if (verifyToken(token)) {
            	ctx.setSendZuulResponse(true);
            } else {
            	ctx.setSendZuulResponse(false);
            	setUnauthorizedResponse(ctx, INVALID_TOKEN);
            }
        }
	    
		return null;
	}
	
	 /**
     * Verify token
     * @param token
     * @return true if passed, false if not passed
     */
    private boolean verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm).build().verify(token);
            logger.debug(">>>token is right");
            return true;
        } catch (Exception e){
        	logger.debug(">>>invalid token ");
            return false;
        }
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
}
