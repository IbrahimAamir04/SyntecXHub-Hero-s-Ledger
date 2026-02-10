
package com.example.heroledger.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heroledger.R
import com.example.heroledger.databinding.FragmentAddTransactionBinding
import com.example.heroledger.domain.model.Transaction
import com.example.heroledger.domain.model.TransactionType
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTransactionViewModel by viewModels()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddTransaction.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val amount = binding.editTextAmount.text.toString().toDoubleOrNull()
            val type = if (binding.radioIncome.isChecked) TransactionType.INCOME else TransactionType.EXPENSE
            val description = binding.editTextDescription.text.toString()
            val userId = firebaseAuth.currentUser?.uid

            if (title.isNotEmpty() && amount != null && userId != null) {
                val transaction = Transaction(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    amount = amount,
                    category = title, // Set category from title
                    date = Date(),
                    type = type,
                    description = description.takeIf { it.isNotEmpty() },
                    userId = userId
                )
                viewModel.addTransaction(transaction)
                findNavController().popBackStack()
            } else {
                // Show error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
