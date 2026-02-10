
package com.example.heroledger.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.heroledger.domain.usecase.GetTransactions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getTransactions: GetTransactions,
    firebaseAuth: FirebaseAuth
) : ViewModel() {

    val transactions = getTransactions().asLiveData()
    val displayName = firebaseAuth.currentUser?.displayName
}
