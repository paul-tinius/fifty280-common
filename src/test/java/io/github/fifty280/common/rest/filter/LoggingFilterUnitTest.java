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

import static io.github.fifty280.common.rest.filter.LoggingFilter.HEADER_REQUEST_ID;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

@ExtendWith( MockitoExtension.class)
class LoggingFilterUnitTest
{
    private final ServletRequest servletRequest = mock( HttpServletRequest.class);
    private final ServletResponse servletResponse = mock( HttpServletResponse.class);
    private final FilterChain filterChain = mock( FilterChain.class );

    private final Logger logger = mock( Logger.class);

    private final LoggingFilter loggingFilter = new LoggingFilter();

    private final String id = UUID.randomUUID().toString();
    private final String ip = "192.191.190.1";
    private final String method = "GET";
    private final String url = "/some/path";

    @BeforeEach
    public void setUp() {
        loggingFilter.setLogger(logger);

        when( ( ( HttpServletRequest ) servletRequest ).getMethod( )).thenReturn( method );
        when( servletRequest.getRemoteAddr( ) ).thenReturn( ip );
        when( ( ( HttpServletRequest ) servletRequest ).getHeader( HEADER_REQUEST_ID ) ).thenReturn( id );
        when( ( ( HttpServletRequest ) servletRequest ).getRequestURI( ) ).thenReturn( url );
    }

    @Test
    void doFilter_noExceptionThrown_logMessageExpected( )
    {
        loggingFilter.doFilter( servletRequest, servletResponse, filterChain );

        verify( logger ).info( "[{}:{}] - {} to {} began", id, ip, method, url );
        verify( logger ).info( "[{}:{}] - {} to {} end - total time {}ms", id, ip, method, url, loggingFilter.getDuration());
    }

    @Test
    void doFilter_throwsException_additionalLogMessageExpected( )
            throws IOException, ServletException
    {
        Throwable throwable = new IOException("some message");
        doThrow( throwable ).when( filterChain ).doFilter( servletRequest, servletResponse );

        loggingFilter.doFilter( servletRequest, servletResponse, filterChain );

        verify( logger ).info( "[{}:{}] - {} to {} began", id, ip, method, url );
        verify( logger ).error( "[{}:{}] - {} - exception: '{}'", id, ip, url, "some message" );
        verify( logger ).info( "[{}:{}] - {} to {} end - total time {}ms", id, ip, method, url, loggingFilter.getDuration() );
    }
}
