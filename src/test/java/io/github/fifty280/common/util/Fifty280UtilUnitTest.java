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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Fifty280UtilUnitTest
{
    private static final String ENV_VAR = "DUMMY_ENV_VAR";
    private static final String ENV_VAR_NOT_FOUND = "DUMMY_ENV_VAR_NOT_FOUND";

    private static final String RESOURCE_KEY = "dummy.resource.key";
    private static final String RESOURCE_KEY_NOT_FOUND = "dummy.resource.not.found.key";
    private static final String RESOURCE_FILE = "test-resource-file.properties";
    private static final String RESOURCE_FILE_NOT_FOUND = "test-resource-file-not-found.properties";

    @Test
    void envVariable_validInput_expectedValueReturned( )
    {
        final int actual = Fifty280Util.envVariable( ENV_VAR, Integer::valueOf, 5);
        assertThat(actual).isEqualTo( 10 );
    }

    @Test
    void resourceFile_validInput_expectedValueReturned( )
    {
        final int actual = Fifty280Util.resourceFile( RESOURCE_KEY, RESOURCE_FILE, Integer::valueOf, 5 );
        assertThat(actual).isEqualTo( 10 );
    }

    @Test
    void envVariable_envVarNotSet_expectedDefaultValueReturned( )
    {
        final int actual = Fifty280Util.envVariable( ENV_VAR_NOT_FOUND, Integer::valueOf, 5);
        assertThat(actual).isEqualTo( 5 );
    }

    @Test
    void resourceFile_resourceKeyNotFound_expectedDefaultValueReturned( )
    {
        final int actual = Fifty280Util.resourceFile( RESOURCE_KEY_NOT_FOUND, RESOURCE_FILE, Integer::valueOf, 5 );
        assertThat(actual).isEqualTo( 5 );
    }

    @Test
    void resourceFile_invalidResourceFileInput_expectedDefaultValueReturned( )
    {
        final int actual = Fifty280Util.resourceFile( RESOURCE_KEY, RESOURCE_FILE_NOT_FOUND, Integer::valueOf, 5 );
        assertThat(actual).isEqualTo( 5 );
    }
}
