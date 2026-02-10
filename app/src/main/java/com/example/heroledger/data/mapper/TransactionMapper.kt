
package com.example.heroledger.data.mapper

import com.example.heroledger.data.local.model.Transaction as TransactionEntity
import com.example.heroledger.domain.model.Transaction

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        title = title,
        amount = amount,
        category = category,
        date = date,
        type = type,
        description = description,
        userId = userId
    )
}

fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        id = id,
        title = title,
        amount = amount,
        category = category,
        date = date,
        type = type,
        description = description,
        userId = userId
    )
}
