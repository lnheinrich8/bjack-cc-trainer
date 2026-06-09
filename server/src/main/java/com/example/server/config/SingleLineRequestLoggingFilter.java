package com.example.server.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * A request logging filter that emits only ONE line per request.
 *
 * CommonsRequestLoggingFilter normally logs both a "before" and an "after"
 * message. We override beforeRequest() to a no-op so only the "after" message
 * (the one that can include the request body) remains.
 *
 * Important: the base class builds its logger from getClass(), so the logger
 * category is the name of THIS class. That's why the DEBUG level is enabled
 * against this class name in application.properties:
 *   logging.level.com.example.server.config.SingleLineRequestLoggingFilter=DEBUG
 */
public class SingleLineRequestLoggingFilter extends CommonsRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        // no-op: skip the "Before request [...]" line
    }
}
