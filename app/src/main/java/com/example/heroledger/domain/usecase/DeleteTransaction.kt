
package com.example.heroledger.domain.usecase

import com.example.heroledger.domain.model.Transaction
import com.example.heroledger.domain.repository.TransactionRepository
import javax.inject.Inject

class DeleteTransaction @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.deleteTransaction(transaction)
    }
}
