package com.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Logs one line per incoming HTTP request (method, URI, query string, and body).
 *
 * Uses SingleLineRequestLoggingFilter (a CommonsRequestLoggingFilter that skips
 * the "before" message). The filter only logs at DEBUG, so the level is enabled
 * in application.properties against that filter's class name:
 *   logging.level.com.example.server.config.SingleLineRequestLoggingFilter=DEBUG
 */
@Configuration
public class RequestLoggingConfig {

    @Bean
    public SingleLineRequestLoggingFilter requestLoggingFilter() {
        SingleLineRequestLoggingFilter filter = new SingleLineRequestLoggingFilter();
        filter.setIncludeQueryString(true);   // append ?foo=bar to the logged URI
        filter.setIncludePayload(true);        // log the request body...
        filter.setMaxPayloadLength(10_000);    // ...up to this many characters
        filter.setIncludeHeaders(false);       // keep headers out of the log for now
        filter.setAfterMessagePrefix("REQUEST: ");
        filter.setAfterMessageSuffix("");      // drop the default trailing "]"
        return filter;
    }
}
