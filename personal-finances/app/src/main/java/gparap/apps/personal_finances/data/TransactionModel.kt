package gparap.apps.personal_finances.data

import java.sql.Date

/** This class describes a user transaction.
 * Transactions can be either of positive (+) or negative (-) balance. */
data class TransactionModel(
    val id: Int,
    val type: String,
    val quantity: Float,
    val date: Date,
    val details: String
)
