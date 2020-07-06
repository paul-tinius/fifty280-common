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
package io.github.fifty280.common.rest.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;


class BasicRolesUnitTest
{
    @Test
    void admin_validInput_successfulMatches( )
    {
        assertThat( BasicRoles.ADMIN ).isEqualToComparingFieldByField( Set.of( BasicRoles.ADMIN_ROLE ) );
    }

    @Test
    void admin_role_sizeOfOne( )
    {
        assertThat(BasicRoles.ADMIN.role()).size().isEqualTo( 1 );
    }

    @Test
    void tenant_validInput_successfulMatches( )
    {
        assertThat( BasicRoles.TENANT ).isEqualToComparingFieldByField( Set.of( BasicRoles.TENANT_ROLE ) );
    }

    @Test
    void tenant_role_sizeOfOne( )
    {
        assertThat(BasicRoles.TENANT.role()).size().isEqualTo( 1 );
    }

    @Test
    void user_validInput_successfulMatches( )
    {
        assertThat( BasicRoles.USER ).isEqualToComparingFieldByField( Set.of( BasicRoles.USER_ROLE ) );
    }

    @Test
    void user_role_sizeOfOne( )
    {
        assertThat(BasicRoles.USER.role()).size().isEqualTo( 1 );
    }

    @Test
    void dummyRole_extendsBasicValidInput_successfulMatches( )
    {
        assertThat( TestBasicRoles.DUMMY ).isEqualToComparingFieldByField( Set.of( TestBasicRoles.DUMMY_ROLE ) );
    }

    @Test
    void dummyRole_role_sizeOfOne( )
    {
        assertThat(BasicRoles.USER.role()).size().isEqualTo( 1 );
    }

    private static class TestBasicRoles
            extends BasicRoles
    {
        static String DUMMY_ROLE = "dummy.role";

        public static Role DUMMY = ( ) -> Set.of( DUMMY_ROLE );
    }
}
