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
package io.github.fifty280.common.localization;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ResourceBundle;

import org.slf4j.Logger;

import io.github.fifty280.common.util.LogUtil;

public class Localizator
{
    private static final Logger log = getLogger( Localizator.class );

    private static final String KEY_FMT = "%s";

    private static final String BUNDLE_NOT_FOUND = "Localization resource \"%s\" not found!";
    private static final String KEY_NOT_FOUND = "Localization string \"%s\" not found!";

    private static final String FILENAME_MUST_NOT_BE_NULL = "localization filename must not be null.";
    private static final String LOCALE_MUST_NOT_BE_NULL = "locale must not be null.";
    private static final String KEY_MUST_NOT_BE_NULL = "localization key must not be null.";

    ResourceBundle resourceBundle = null;                               // Bundle containing the localization.

    private final String localizationFilename;
    private final Locale locale;

    public Localizator( final String localizationFilename )
    {
        this(localizationFilename, Locale.getDefault());
    }

    public Localizator( final String localizationFilename, final Locale locale )
    {
        this.localizationFilename = Objects.requireNonNull( localizationFilename, FILENAME_MUST_NOT_BE_NULL );
        this.locale = Objects.requireNonNull( locale, LOCALE_MUST_NOT_BE_NULL );;
    }

    public String localize( final String key )
    {
        try
        {
            return getResourceBundle( ).getString( Objects.requireNonNull( key, KEY_MUST_NOT_BE_NULL ) );
        }
        catch( NullPointerException | MissingResourceException e )
        {
            final String s = String.format( KEY_NOT_FOUND, key );
            log.warn( s );
            return s;
        }
    }

    private ResourceBundle getResourceBundle( )
    {
        if( resourceBundle == null )
        {
            reloadResourceBundle( );
        }

        return resourceBundle;
    }

    private void reloadResourceBundle( )
    {
        try
        {
            this.resourceBundle = ResourceBundle.getBundle( this.localizationFilename, this.locale );
        }
        catch( MissingResourceException e )
        {
            String msg = String.format( BUNDLE_NOT_FOUND, this.localizationFilename );
            if( this.resourceBundle == null )
            {
                this.resourceBundle = new EmptyResourceBundle( );
            }

            LogUtil.warnException( log, msg, e );
        }
    }

    static class EmptyResourceBundle
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
