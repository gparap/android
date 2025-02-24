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
package gparap.apps.personal_finances.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert
    suspend fun addTransaction(vararg transaction: TransactionModel)

    @Query("SELECT * FROM `transaction`")
    suspend fun getAllTransactions(): List<TransactionModel>

    @Query("SELECT * FROM `transaction` WHERE `quantity` > 0")
    suspend fun getTopUpTransactions(): List<TransactionModel>

    @Query("SELECT * FROM `transaction` WHERE `quantity` < 0")
    suspend fun getExpenseTransactions(): List<TransactionModel>

    @Query("DELETE FROM `transaction` WHERE `id` = :id")
    suspend fun deleteTransaction(id: Int): Int
}