package com.hhplus.commerce.spring.old.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

//        makeLogMessage(requestWrapper, responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

//    private void makeLogMessage(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {
//        log.info("REQ_METHOD={} | REQ_URI={} | REQ_BODY={} | RES_HTTP_STATUS={} | RES_BODY={}",
//                 requestWrapper.getMethod(),
//                 requestWrapper.getRequestURI(),
//                 objectMapper.readTree(requestWrapper.getContentAsByteArray()),
//                 HttpStatus.valueOf(responseWrapper.getStatus()),
//                 objectMapper.readTree(responseWrapper.getContentAsByteArray()));
//    }
}
