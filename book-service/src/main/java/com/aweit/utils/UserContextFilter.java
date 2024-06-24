package com.aweit.utils;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContextHolder.getContext().setAuthToken( httpServletRequest.getHeader(UserContext.AUTH_TOKEN) );
        UserContextHolder.getContext().setAuthorId( httpServletRequest.getHeader(UserContext.AUTHOR_ID) );
        UserContextHolder.getContext().setUserId( getUsername() );
        
        logger.debug("{} Calls Author Service Incoming Correlation id: {}" ,UserContextHolder.getContext().getUserId(),UserContextHolder.getContext().getCorrelationId());
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
    
	
	public String getUsername() {
		String username = "";
		if (UserContextHolder.getContext().getAuthToken()!= null) {
			JSONObject jsonObj = decodeJWT(UserContextHolder.getContext().getAuthToken().replace("Bearer ", ""));
			try {
				username = jsonObj.getString("preferred_username");
			} catch (Exception e) {
				throw e;
			}
		}
		return username;
	}

	private JSONObject decodeJWT(String JWTToken) {
		String[] splitString = JWTToken.split("\\.");
		String base64EncodedBody = splitString[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		JSONObject jsonObj = new JSONObject(body);
		return jsonObj;
	}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
