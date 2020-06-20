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
package io.github.fifty280.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fifty280Util
{
    private static final Logger log = LoggerFactory.getLogger( Fifty280Util.class );

    private static final String ENV_VARIABLE_NOT_FOUND =
            "Environment variable '{}' was not found, returning default value of '{}'.";
    private static final String RESOURCE_NAME_NOT_FOUND =
            "Resource key '{}' was not found in {}, returning default value of '{}'.";
    private static final String RESOURCE_FILE_NOT_FOUND =
            "Resource file was not found '{}', returning default value of '{}'.";
    private static final String FAILURE_REASON =
            "Failed retrieving '{}' from '{}', returning default value of '{}', failure reason: '{}'.";

    /**
     * Returns a config value from the environment, converting it to the required type.
     *
     * @param envVariableName a {@link String} representing the environment variable name to read.
     * @param converter a function that converts the string from the environment into the appropriate domain type.
     * @param defaultValue a default value to use if the environment variable is unset.
     * @param <T> the type of the config value to return.
     *
     * @return Returns a @{code T} from the environment, or the default value.
     */
    public static <T> T envVariable( final String envVariableName,
                                     final Function<? super String, ? extends T> converter,
                                     final T defaultValue )
    {
        final String value = System.getenv( envVariableName );
        if ( Objects.isNull( value ) || value.length() == 0 )
        {
            log.warn( ENV_VARIABLE_NOT_FOUND, envVariableName, defaultValue );

            return defaultValue;
        }
        
        return converter.apply(value);
    }

    /**
     * Returns a config value from a provided resource file, converting it to the required type.
     *
     * @param resourceKey a {@link String} representing the resource key to lookup.
     * @param resourceFilename a {@link Path} representing the resource filename to read.
     * @param converter a function that converts the string from the resource into the appropriate domain type.
     * @param defaultValue a default value to use if the resource variable is unset.
     * @param <T> the type of the config value to return.
     *
     * @return Returns a @{code T} from the environment, or the default value.
     */
    public static <T> T resourceFile( final String resourceKey,
                                      final String resourceFilename,
                                      final Function<? super String, ? extends T> converter,
                                      final T defaultValue )
    {
        try( final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream( resourceFilename ) )
        {
            if(Objects.isNull( in )) {
                log.warn( RESOURCE_FILE_NOT_FOUND, resourceFilename, defaultValue );
                return defaultValue;
            }

            final Properties properties = new Properties();
            properties.load( in );

            final String value = properties.getProperty( resourceKey );
            if( Objects.isNull( value ) || value.length( ) == 0 )
            {
                log.warn( RESOURCE_NAME_NOT_FOUND, resourceKey, resourceFilename, defaultValue );

                return defaultValue;
            }

            return converter.apply( value );
        }
        catch( IOException e )
        {
            LogUtil.warnException( FAILURE_REASON, e, resourceKey, resourceFilename, defaultValue, e.getMessage() );

            return defaultValue;
        }
    }
}
