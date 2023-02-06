package com.example.fineco.ui.add_finance

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.fineco.R
import com.example.fineco.databinding.AmountInputBinding
import com.example.fineco.ui.budget.finances.EXPENSES
import com.example.fineco.ui.budget.finances.FinancesViewModel

class AddFinanceDialogFragment (private val viewModel: FinancesViewModel)
    : DialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: AmountInputBinding
    private val type: String get() = requireArguments().getString(ARG_TYPE)!!
    private var selectedCategory = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AmountInputBinding.inflate(layoutInflater)
        setupSpinner()
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Добавить")
            .setView(binding.root)
            .setPositiveButton("Добавить", null)
            .create()

        dialog.setOnShowListener {

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enteredText = binding.editTextNumberDecimal.text.toString()
                if (enteredText.isBlank()) {
                    binding.editTextNumberDecimalLayout.error = getString(R.string.enter_value)
                    return@setOnClickListener
                }
                val amount = enteredText.toFloatOrNull()

                if (amount == null || amount < 0) {
                    binding.editTextNumberDecimalLayout.error = getString(R.string.invalid_value)
                    return@setOnClickListener
                }

                viewModel.add(type, selectedCategory, amount)
                dismiss()
            }
        }
        return dialog
    }

    private fun setupSpinner() {
        val spinner = binding.spinner
        val categories = if (type == EXPENSES) R.array.expense_categories else R.array.income_categories
        ArrayAdapter.createFromResource(
            this.requireContext(),
            categories,
            android.R.layout.simple_spinner_item
        ).also {arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = arrayAdapter
        }

        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        selectedCategory = p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    companion object {
        @JvmStatic private val TAG = AddFinanceDialogFragment::class.java.simpleName
        @JvmStatic private val ARG_TYPE = "ARG_TYPE"

        fun show(manager: FragmentManager, arg_type: String, viewModel: FinancesViewModel) {
            val dialogFragment = AddFinanceDialogFragment(viewModel)
            dialogFragment.arguments = bundleOf(
                ARG_TYPE to arg_type
            )
            dialogFragment.show(manager, TAG)
        }
    }

}