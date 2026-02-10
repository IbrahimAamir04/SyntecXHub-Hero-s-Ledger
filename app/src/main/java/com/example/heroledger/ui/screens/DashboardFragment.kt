
package com.example.heroledger.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heroledger.R
import com.example.heroledger.databinding.FragmentDashboardBinding
import com.example.heroledger.domain.model.TransactionType
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addTransactionFragment)
        }

        binding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transactionHistoryFragment)
        }

        viewModel.displayName.let { 
            binding.textViewWelcome.text = "Welcome, $it"
        }

        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            val totalIncome = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
            val totalExpenses = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
            val currentBalance = totalIncome - totalExpenses

            binding.textViewTotalIncome.text = "Total Income: $${totalIncome}"
            binding.textViewTotalExpenses.text = "Total Expenses: $${totalExpenses}"
            binding.textViewCurrentBalance.text = "Current Balance: $${currentBalance}"

            val expenseByCategory = transactions.filter { it.type == TransactionType.EXPENSE }
                .groupBy { it.title }
                .map { (title, transactions) ->
                    PieEntry(transactions.sumOf { it.amount }.toFloat(), title)
                }

            val pieDataSet = PieDataSet(expenseByCategory, "Expenses")
            pieDataSet.colors = listOf(
                requireContext().getColor(R.color.mgs_blue),
                requireContext().getColor(R.color.mgs_green_light),
                requireContext().getColor(R.color.mgs_green_medium),
                requireContext().getColor(R.color.mgs_grey_light),
                requireContext().getColor(R.color.mgs_grey_medium),
                requireContext().getColor(R.color.mgs_grey_dark)
            )
            pieDataSet.valueTextColor = Color.WHITE
            pieDataSet.valueTextSize = 12f

            val pieData = PieData(pieDataSet)
            pieData.setValueFormatter(PercentFormatter(binding.pieChart))

            binding.pieChart.data = pieData
            binding.pieChart.description.isEnabled = false
            binding.pieChart.isDrawHoleEnabled = true
            binding.pieChart.setUsePercentValues(true)
            binding.pieChart.setEntryLabelColor(Color.WHITE)
            binding.pieChart.setEntryLabelTextSize(12f)
            binding.pieChart.centerText = "Income: $${totalIncome}\nBalance: $${currentBalance}"
            binding.pieChart.setCenterTextSize(20f)
            binding.pieChart.setCenterTextColor(requireContext().getColor(R.color.mgs_white))
            binding.pieChart.animateY(1400)

            binding.pieChart.invalidate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
