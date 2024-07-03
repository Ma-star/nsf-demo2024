package com.netease.cloud.nsf.demo.stock.advisor.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2021/8/25 5:02 下午
 */
@Component
public class LogMessageFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogMessageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        LOGGER.info("do init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{

        LOGGER.info("============== [LOGGER MESSAGE START] ==============");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOGGER.info("[URI] {}", httpServletRequest.getRequestURI());
        LOGGER.info("[QUERY_STRING] {}", httpServletRequest.getQueryString());

        Enumeration <String> headerNames = httpServletRequest.getHeaderNames();
        String headerName;
        while (!StringUtils.isEmpty(headerName = headerNames.nextElement())) {
            String headerValue = httpServletRequest.getHeader(headerName);
            LOGGER.info("[HEADER] {}:{}", headerName, headerValue);
        }
        LOGGER.info("============== [LOGGER MESSAGE END] ==============");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy(){
        LOGGER.info("do destroy.");
    }
}
