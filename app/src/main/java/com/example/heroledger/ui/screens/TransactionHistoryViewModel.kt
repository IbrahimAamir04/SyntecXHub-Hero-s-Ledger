
package com.example.heroledger.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.heroledger.domain.usecase.GetTransactions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    getTransactions: GetTransactions
) : ViewModel() {

    val transactions = getTransactions().asLiveData()
}
