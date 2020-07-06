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

import java.util.Set;

public abstract class BasicRoles
{
    static final String ADMIN_ROLE = "admin.role";          // Overall Super Role ( Platform Admin )
    static final String TENANT_ROLE = "tenant.role";        // Tenant Super Role ( Tenant Admin )
    static final String USER_ROLE = "user.role";            // User Role

    public static Role ADMIN = () -> Set.of(ADMIN_ROLE);
    public static Role TENANT = () -> Set.of(TENANT_ROLE);
    public static Role USER = () -> Set.of(USER_ROLE);
}
