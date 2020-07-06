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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.fifty280.common.localization.Localizator.EmptyResourceBundle;

class LocalizatorUnitTest
{
    private Localizator localizator;

    @BeforeEach
    void setUp( )
    {
        this.localizator = new Localizator( "l10n" );
    }

    @Test
    void localize_missingResourceBundle_resourceBundleEqualsEmptyResourceBundle( )
    {
        Localizator localizator = new Localizator( "Does_Not_Exists_Resource_Bundle" );
        localizator.localize( "dummy.resource.key" );
        assertThat(localizator.resourceBundle).isInstanceOf( EmptyResourceBundle.class );
    }

    @Test
    void getKeys_resourceBundleGetKeys_returnsEmptyKeysEnumeration( )
    {
        Localizator localizator = new Localizator( "Does_Not_Exists_Resource_Bundle" );
        localizator.localize( "dummy.resource.key" );
        assertThat(localizator.resourceBundle).isInstanceOf( EmptyResourceBundle.class );
        assertThat(localizator.resourceBundle.getKeys()).isNotNull();
        assertThat(localizator.resourceBundle.getKeys().hasMoreElements()).isEqualTo( false );

        Throwable throwable =
                catchThrowable( () -> assertThat(localizator.resourceBundle.getKeys().nextElement()).isNull());

        assertThat(throwable).isInstanceOf( NoSuchElementException.class );

    }

    @Test
    void localize_resourceBundleValid_resourceBundleEqualsEmptyResourceBundle( )
    {
        localizator.localize( "conflict" );
        assertThat(localizator.resourceBundle).isNotInstanceOf( EmptyResourceBundle.class );
    }

    @Test
    void localize_validKey_returnsExpectedLocalizedString( )
    {
        // bad.request=The request could not be understood by the server due to malformed syntax.
        final String actual = localizator.localize( "bad.request" );
        assertThat( actual ).isEqualTo( "The request could not be understood by the server due to malformed syntax." );
    }

    @Test
    void localize_invalidKey_returnsKeyNotFound( )
    {
        final String actual = localizator.localize( "no.key" );
        assertThat( actual ).isEqualTo( "Localization string \"no.key\" not found!" );
    }

    @Test
    void localize_nullConstructorArgument_throwsNullPointerException( )
    {
        Throwable t = catchThrowable( ( ) -> new Localizator( null ) );
        assertThat( t ).isExactlyInstanceOf( NullPointerException.class );
        assertThat( t ).hasMessage( "localization filename must not be null." );
    }
}
