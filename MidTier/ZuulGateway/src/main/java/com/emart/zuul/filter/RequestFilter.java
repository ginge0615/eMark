package com.emart.zuul.filter;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import ch.qos.logback.core.joran.action.Action;

@Component
public class RequestFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	@Value("${zuul.noTokenValid}")
	private String noTokenValid;
	
	@Override
	public boolean shouldFilter() {
		if (StringUtils.isEmpty(noTokenValid)) {
			return true;
		}
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String url = request.getRequestURI();
		logger.info("url=" + url);
		
		List<String> lstNoTokenValid = Arrays.asList(noTokenValid.split(","));
		
		for (String noTolenValid : lstNoTokenValid) {
			if (url.startsWith(noTolenValid)) {
				return false;
			}
		}
        
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info(">>>>>>>>>>>>>>>>>>run");
		return null;
	}

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

}
