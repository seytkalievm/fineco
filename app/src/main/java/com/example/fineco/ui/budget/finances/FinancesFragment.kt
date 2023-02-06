package com.example.fineco.ui.budget.finances

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fineco.databinding.FragmentFinancesBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint


const val ARG_OBJECT = "fragment_type"
const val EXPENSES = "expenses"
const val INCOMES = "incomes"

@AndroidEntryPoint
class FinancesFragment : Fragment() {

    private val viewModel: FinancesViewModel by viewModels()
    private lateinit var binding: FragmentFinancesBinding
    private lateinit var type: String
    private lateinit var chart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinancesBinding.inflate(inflater, container, false)
        type = arguments?.getString(ARG_OBJECT)!!
        viewModel.setData(type)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart = binding.pieChart
        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        setUpPieChart()
        val label = if (type == EXPENSES) "Расходы" else "Доходы"

        viewModel.data.observe(viewLifecycleOwner) {
            val dataSet = PieDataSet(it, label)
            dataSet.colors = colors
            chart.data = PieData(dataSet)

            loadPieChartData()
        }

        viewModel.totalAmount.observe(viewLifecycleOwner) {
            chart.centerText = "Всего $it"
        }

    }

    private fun setUpPieChart() {
        chart.apply {
            setTouchEnabled(false)
            isRotationEnabled = false
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            description.isEnabled = false
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(10F)
        }
        chart.legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(true)
        }
    }


    private fun loadPieChartData() {

        chart.data?.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(chart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }
        chart.invalidate()

    }
}