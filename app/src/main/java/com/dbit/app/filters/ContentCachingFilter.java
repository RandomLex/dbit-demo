package com.dbit.app.filters;

import com.dbit.app.controllers.interceptors.RealCachingRequestWrapper;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContentCachingFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        RealCachingRequestWrapper reqWrapper = new RealCachingRequestWrapper((HttpServletRequest) req);
        reqWrapper.getParameterMap();
        chain.doFilter(reqWrapper, resp);
    }
}
