package io.github.fifty280.common.localization;

import io.github.fifty280.common.util.LogUtil;
import org.slf4j.Logger;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;

public class Localizator
{
    private static final Logger log = getLogger( Localizator.class );

    private static final String KEY_FMT = "%s";

    private static final String BUNDLE_NOT_FOUND = "locale.bundle.notfound";    // Bundle for missing localization.
    private static final String KEY_NOT_FOUND = "locale.key.notfound";          // Key for missing localization.

    private ResourceBundle resourceBundle = null;                               // Bundle containing the localization.

    private final String localizationFilename;

    public Localizator( final String localizationFilename)
    {
        this.localizationFilename = Objects.requireNonNull( localizationFilename,
                                                           "localization filename must not be null.");
    }

    private ResourceBundle getResourceBundle( )
    {
        if( resourceBundle == null )
        {
            reloadResourceBundle( );
        }

        return resourceBundle;
    }

    public String localize( final String key )
    {
        try
        {
            return getResourceBundle( ).getString( Objects.requireNonNull( key, "localization key must not be null.") );
        }
        catch( NullPointerException | MissingResourceException e )
        {
            final String s = String.format( localize( KEY_NOT_FOUND ), key );
            log.warn( s );
            return s;
        }
    }

    private synchronized void reloadResourceBundle( )
    {
        try
        {
            this.resourceBundle = ResourceBundle.getBundle( this.localizationFilename );
        }
        catch( MissingResourceException e )
        {
            String msg = String.format( localize( BUNDLE_NOT_FOUND ), this.localizationFilename );
            if( this.resourceBundle == null )
            {
                this.resourceBundle = new EmptyResourceBundle( );
            }

            LogUtil.warnException( msg, e );
        }
    }

    private static class EmptyResourceBundle
            extends ResourceBundle
    {
        @Override
        protected Object handleGetObject( final String key )
        {
            return String.format( KEY_FMT, Objects.requireNonNull( key, "key must not be null.") );
        }

        @Override
        public Enumeration<String> getKeys( )
        {
            return new EmptyEnumeration<>( );
        }

        private static class EmptyEnumeration<T>
                implements Enumeration<T>
        {

            @Override
            public boolean hasMoreElements( )
            {
                return false;
            }

            @Override
            public T nextElement( )
            {
                throw new NoSuchElementException( );
            }
        }
    }
}
