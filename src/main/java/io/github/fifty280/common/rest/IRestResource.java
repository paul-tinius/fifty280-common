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

import java.net.URI;
import java.util.Objects;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.github.fifty280.common.localization.Localizator;

public interface IRestResource
{
    // i10n resource keys
    String BAD_REQUEST = "bad.request";
    String CONFLICT = "conflict";
    String CREATED = "created";
    String FORBIDDEN = "forbidden";
    String NOT_FOUND = "not.found";
    String NOT_IMPLEMENTED = "not.implemented";
    String UNAUTHORIZED = "unauthorized";

    Localizator l10n = new Localizator( "l10n");

    default Response badRequest( )
    {
        return badRequest( l10n.localize( BAD_REQUEST ) );
    }

    default Response badRequest( final Object entity )
    {
        return Response.status( Status.BAD_REQUEST )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }

    default Response conflict( )
    {
        return conflict( l10n.localize( CONFLICT ) );
    }

    default Response conflict( final Object entity )
    {
        return Response.status( Status.CONFLICT )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }

    default Response created( final URI location, final String eTag )
    {
        return created( l10n.localize( CREATED ), location,
                        new EntityTag( Objects.requireNonNull( eTag, "etag must not be null." ) ) );
    }

    default Response created( final URI location, final EntityTag eTag )
    {
        return created( l10n.localize( CREATED ), location, eTag );
    }

    default Response created( final Object entity, final URI location, final EntityTag eTag )
    {
        return Response.status( Status.CREATED )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .location( Objects.requireNonNull( location, "location must not be null." ) )
                       .tag( Objects.requireNonNull( eTag, "etag must not be null." ) )
                       .build( );
    }

    default Response forbidden( )
    {
        return forbidden( l10n.localize( FORBIDDEN ) );
    }

    default Response forbidden( final Object entity )
    {
        return Response.status( Status.FORBIDDEN )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }

    default Response notFound( )
    {
        return notFound( l10n.localize( NOT_FOUND ) );
    }

    default Response notFound( final Object entity )
    {
        return Response.status( Status.NOT_FOUND )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }

    default Response notImplemented( )
    {
        return notImplemented( l10n.localize( NOT_IMPLEMENTED ) );
    }

    default Response notImplemented( final Object entity )
    {
        return Response.status( Status.NOT_IMPLEMENTED )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }

    default Response unauthorized( )
    {
        return unauthorized( l10n.localize( UNAUTHORIZED ) );
    }

    default Response unauthorized( final Object entity )
    {
        return Response.status( Status.UNAUTHORIZED )
                       .entity( Objects.requireNonNull( entity, "entity must not be null.") )
                       .build( );
    }
}
