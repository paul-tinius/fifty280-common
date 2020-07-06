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
package io.github.fifty280.common.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.net.URI;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Test;

class IRestResourceUnitTest
{
    private final IRestResource restResource = new TestRestResourceImpl();

    @Test
    void badRequest_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.badRequest();

        assertThat(response.getStatus()).isEqualTo( Status.BAD_REQUEST.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Bad Request" );
        assertThat(response.getEntity()).isEqualTo( "The request could not be understood by the server due to malformed syntax." );
    }

    @Test
    void badRequest_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.badRequest(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    @Test
    void conflict_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.conflict();

        assertThat(response.getStatus()).isEqualTo( Status.CONFLICT.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Conflict" );
        assertThat(response.getEntity()).isEqualTo( "The request could not be completed due to a conflict with the current state of the resource." );
    }

    @Test
    void conflict_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.conflict(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    @Test
    void created_validInput_expectedResponseReturned( )
    {
        final URI expectedURI = URI.create( "http://host:1234/resetful/api/resource/type/unique/identifier");
        final EntityTag expectedETAG = new EntityTag( "etag" );
        final Response response = restResource.created( expectedURI, expectedETAG);

        assertThat(response.getStatus()).isEqualTo( Status.CREATED.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Created" );
        assertThat(response.getEntity()).isEqualTo( "The request has been fulfilled and resulted in a new resource being created." );
        assertThat(response.getLocation()).isEqualTo( expectedURI );
        assertThat(response.getEntityTag()).isEqualTo( expectedETAG );
    }

    @Test
    void created_invalidInputNullUri_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.created(null, new EntityTag( "etag" )));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "location must not be null." );
    }

    @Test
    void created_invalidInputNullEtag_throwsNullPointerException( )
    {
        final URI expectedURI = URI.create( "http://host:1234/resetful/api/resource/type/unique/identifier");

        Throwable t = catchThrowable( () -> restResource.created(expectedURI, (String)null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "etag must not be null." );
    }

    @Test
    void forbidden_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.forbidden();

        assertThat(response.getStatus()).isEqualTo( Status.FORBIDDEN.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Forbidden" );
        assertThat(response.getEntity()).isEqualTo( "The server understood the request, but is refusing to fulfill it." );
    }

    @Test
    void forbidden_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.forbidden(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    @Test
    void notFound_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.notFound();

        assertThat(response.getStatus()).isEqualTo( Status.NOT_FOUND.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Not Found" );
        assertThat(response.getEntity()).isEqualTo( "Resource not found" );
    }

    @Test
    void notFound_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.notFound(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    @Test
    void notImplemented_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.notImplemented();

        assertThat(response.getStatus()).isEqualTo( Status.NOT_IMPLEMENTED.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Not Implemented" );
        assertThat(response.getEntity()).isEqualTo( "The server does not support the functionality required to fulfill the request." );
    }

    @Test
    void notImplemented_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.notImplemented(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    @Test
    void unauthorized_validInput_expectedResponseReturned( )
    {
        final Response response = restResource.unauthorized();

        assertThat(response.getStatus()).isEqualTo( Status.UNAUTHORIZED.getStatusCode() );
        assertThat(response.getStatusInfo().getReasonPhrase()).isEqualTo( "Unauthorized" );
        assertThat(response.getEntity()).isEqualTo( "The request requires user authentication." );
    }

    @Test
    void unauthorized_invalidInput_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( () -> restResource.unauthorized(null));

        assertThat(t).isExactlyInstanceOf( NullPointerException.class );
        assertThat(t).hasMessage( "entity must not be null." );
    }

    private static class TestRestResourceImpl implements IRestResource
    {
    }
}
