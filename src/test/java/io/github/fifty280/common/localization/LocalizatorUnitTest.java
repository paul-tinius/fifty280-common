package io.github.fifty280.common.localization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LocalizatorUnitTest
{
    private Localizator localizator;

    @BeforeEach
    void setUp( )
    {
        this.localizator = new Localizator( "l10n" );
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