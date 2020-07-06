/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.fifty280.common.rest.filter;

import java.time.Duration;
import java.time.Instant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import io.github.fifty280.common.util.LogUtil;

public class LoggingFilter implements Filter
{
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public static final String HEADER_REQUEST_ID = "X-Request-Id";
    public static final String MDC_URL = "URL";
    public static final String MDC_IP = "IP";

    private Duration duration;

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
    {
        Instant start = Instant.now();

        String requestURL = ( ( HttpServletRequest ) servletRequest ).getRequestURI( );
        String requestIP = servletRequest.getRemoteAddr( );
        String requestMethod = ( ( HttpServletRequest ) servletRequest ).getMethod( );
        String requestId = StringUtils.defaultString( ( ( HttpServletRequest ) servletRequest ).getHeader( HEADER_REQUEST_ID ) );

        MDC.put( HEADER_REQUEST_ID, requestId );
        MDC.put( MDC_URL, requestURL );
        MDC.put( MDC_IP, requestIP );

        logger.info( "[{}:{}] - {} to {} began", requestId, requestIP, requestMethod, requestURL );
        try
        {
            filterChain.doFilter( servletRequest, servletResponse );
        }
        catch( Exception exception )
        {
            LogUtil.errorException( logger, "[{}:{}] - {} - exception: '{}'", exception, requestId, requestIP,
                                    requestURL,
                                    exception.getMessage( ) );
        }
        finally
        {
            Instant finish = Instant.now();
            this.duration = Duration.between( start, finish);

            logger.info( "[{}:{}] - {} to {} end - total time {}ms", requestId, requestIP, requestMethod,
                         requestURL, this.getDuration() );
        }
    }

    long getDuration() {
        return this.duration.toMillis();
    }

    void setLogger(Logger logger) {
        this.logger = logger;
    }
}
