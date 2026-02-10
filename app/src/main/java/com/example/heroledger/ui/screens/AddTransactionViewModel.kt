
package com.example.heroledger.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heroledger.domain.model.Transaction
import com.example.heroledger.domain.usecase.AddTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val addTransaction: AddTransaction
) : ViewModel() {

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            addTransaction.invoke(transaction)
        }
    }
}
