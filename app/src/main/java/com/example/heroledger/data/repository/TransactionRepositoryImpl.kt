
package com.example.heroledger.data.repository

import com.example.heroledger.data.local.dao.TransactionDao
import com.example.heroledger.data.mapper.toDomain
import com.example.heroledger.data.mapper.toEntity
import com.example.heroledger.domain.model.Transaction
import com.example.heroledger.domain.repository.TransactionRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val firebaseAuth: FirebaseAuth
) : TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> {
        val userId = firebaseAuth.currentUser?.uid
        return if (userId != null) {
            transactionDao.getTransactions(userId).map { transactions ->
                transactions.map { it.toDomain() }
            }
        } else {
            kotlinx.coroutines.flow.flowOf(emptyList())
        }
    }

    override suspend fun getTransactionById(id: String): Transaction? {
        return transactionDao.getTransactionById(id)?.toDomain()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction.toEntity())
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction.toEntity())
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction.toEntity())
    }
}
