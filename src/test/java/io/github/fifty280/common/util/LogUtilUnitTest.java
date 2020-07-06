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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

class LogUtilUnitTest
{
    private final Logger logger = mock( Logger.class);
    private final NullPointerException npe = new NullPointerException();

    @Test
    void infoException_scenarioBeingTested_expectedResult( )
    {
        LogUtil.infoException( logger, "message", npe );

        verify( logger ).info( "message" );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }

    @Test
    void testInfoException_scenarioBeingTested_expectedResult( )
    {
        String argument = "argument";
        String argument1 = "argument1";

        LogUtil.infoException( logger, "message {} {}", npe, argument, argument1);

//        verify( logger ).info( "message {} {}", argument, argument1 );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }

    @Test
    void warnException_scenarioBeingTested_expectedResult( )
    {
        LogUtil.warnException( logger, "message", npe );

        verify( logger ).warn( "message" );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }

    @Test
    void testWarnException_scenarioBeingTested_expectedResult( )
    {
        String argument = "argument";
        String argument1 = "argument1";

        LogUtil.warnException( logger, "message {} {}", npe, argument, argument1);

//        verify( logger ).warn( "message {} {}", argument, argument1 );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }

    @Test
    void errorException_scenarioBeingTested_expectedResult( )
    {
        LogUtil.errorException( logger, "message", npe );

        verify( logger ).error( "message" );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }

    @Test
    void testErrorException_scenarioBeingTested_expectedResult( )
    {
        String argument = "argument";
        String argument1 = "argument1";

        LogUtil.errorException( logger, "message {} {}", npe, argument, argument1);

//        verify( logger ).error( "message {} {}", argument, argument1 );
        verify( logger ).debug( "STACK TRACE:: ", npe );
    }
}
