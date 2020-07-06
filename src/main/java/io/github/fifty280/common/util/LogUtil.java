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

import org.slf4j.Logger;
import org.slf4j.event.Level;

public class LogUtil
{
    private static final String STACK_TRACE = "STACK TRACE:: ";

    /**
     * @param log the {@link Logger}
     * @param msg a {@link String} representing the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     */
    public static void infoException(final Logger log, final String msg, final Throwable throwable) {
        log.info( msg );
        log.debug( STACK_TRACE, throwable );
    }

    /**
     * @param log the {@link Logger}
     * @param fmt a {@link String} representing the format of the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     * @param objects the arguments to be applied to the {@code fmt}
     */
    public static void infoException(final Logger log, final String fmt, final Throwable throwable, final Object ... objects) {
        log.info( fmt, objects );
        log.debug( STACK_TRACE, throwable );
    }
    
    /**
     * @param log the {@link Logger}
     * @param msg a {@link String} representing the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     */
    public static void warnException(final Logger log, final String msg, final Throwable throwable) {
        log.warn( msg );
        log.debug( STACK_TRACE, throwable );
    }

    /**
     * @param log the {@link Logger}
     * @param fmt a {@link String} representing the format of the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     * @param objects the arguments to be applied to the {@code fmt}
     */
    public static void warnException(final Logger log, final String fmt, final Throwable throwable, final Object ... objects) {
        log.warn( fmt, objects );
        log.debug( STACK_TRACE, throwable );
    }

    /**
     * @param log the {@link Logger}
     * @param msg a {@link String} representing the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     */
    public static void errorException(final Logger log, final String msg, final Throwable throwable) {
        log.error( msg );
        log.debug( STACK_TRACE, throwable );
    }

    /**
     * @param log the {@link Logger}
     * @param fmt a {@link String} representing the format of the message to be logged.
     * @param throwable the {@link Throwable} exception to be logged at {@link Level#DEBUG}
     * @param objects the arguments to be applied to the {@code fmt}
     */
    public static void errorException(final Logger log, final String fmt, final Throwable throwable, final Object ... objects) {
        log.error( fmt, objects );
        log.debug( STACK_TRACE, throwable );
    }
}
