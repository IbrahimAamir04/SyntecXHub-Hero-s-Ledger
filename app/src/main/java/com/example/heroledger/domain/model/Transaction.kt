
package com.example.heroledger.domain.model

import java.util.Date

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Date,
    val type: TransactionType,
    val description: String? = null,
    val userId: String
)

enum class TransactionType {
    INCOME,
    EXPENSE
}
