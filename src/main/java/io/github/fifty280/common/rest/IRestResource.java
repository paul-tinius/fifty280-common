package io.github.fifty280.common.rest;

import io.github.fifty280.common.localization.Localizator;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;

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

    Localizator i10n = new Localizator( "I10n");

    default Response badRequest( )
    {
        return badRequest( i10n.localize( BAD_REQUEST ) );
    }

    default Response badRequest( final String message )
    {
        return Response.status( Status.BAD_REQUEST ).entity( message ).build( );
    }

    default Response conflict( )
    {
        return conflict(i10n.localize( CONFLICT ) );
    }

    default Response conflict( final String message )
    {
        return Response.status( Status.CONFLICT ).entity( message ).build( );
    }

    default Response created( final URI location, final String eTag )
    {
        return created( i10n.localize( CREATED ), location, eTag );
    }

    default Response created( final String message, final URI location, final String eTag )
    {
        return Response.status( Status.CREATED ).entity( message ).location( location ).tag( eTag ).build( );
    }

    default Response forbidden( )
    {
        return forbidden( i10n.localize( FORBIDDEN ) );
    }

    default Response forbidden( final String message )
    {
        return Response.status( Status.FORBIDDEN ).entity( message ).build( );
    }

    default Response notFound( )
    {
        return notFound( i10n.localize( NOT_FOUND ) );
    }

    default Response notFound( final String message )
    {
        return Response.status( Status.NOT_FOUND ).entity( message ).build( );
    }

    default Response notImplemented( )
    {
        return notImplemented( i10n.localize( NOT_IMPLEMENTED ) );
    }

    default Response notImplemented( final String message )
    {
        return Response.status( Status.NOT_IMPLEMENTED ).entity( message ).build( );
    }

    default Response unauthorized( )
    {
        return notImplemented( i10n.localize( UNAUTHORIZED ) );
    }

    default Response unauthorized( final String message )
    {
        return Response.status( Status.UNAUTHORIZED ).entity( message ).build( );
    }
}
