/*
 * Copyright 2024 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.personal_finances.utils

object AppConstants {
    const val APP_DATABASE                  = "PersonalFinancesDB"

    const val INTENT_EXTRA_TRANSACTION_TYPE = "transaction_type"

    const val APP_SHARED_PREFERENCES        = "app_shared_prefs"
    const val SHARED_PREF_ACCOUNT_BALANCE   = "account_balance"
    const val ZERO_ACCOUNT_BALANCE          = 0f
}