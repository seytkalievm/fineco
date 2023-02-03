package com.example.fineco.ui.finances

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fineco.databinding.FragmentFinancesBinding
import com.github.mikephil.charting.charts.PieChart

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


const val ARG_OBJECT = "fragment_type"
const val EXPENSES = "expenses"
const val INCOMES = "incomes"

class FinancesFragment : Fragment() {

    private lateinit var viewModel: FinancesViewModel
    private lateinit var binding: FragmentFinancesBinding
    private lateinit var type: String
    private lateinit var chart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinancesBinding.inflate(inflater, container, false)
        type = arguments?.getString(ARG_OBJECT)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart = binding.pieChart
        setUpPieChart()
        loadPieChartData()

    }

    private fun setUpPieChart() {
        chart.apply {
            setTouchEnabled(false)
            isRotationEnabled = false
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            centerText = "Всего 200000"
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
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(34945f, "Электроника"))
        entries.add(PieEntry(24908f, "Супермаркеты"))
        entries.add(PieEntry(12137f, "Переводы"))
        entries.add(PieEntry(10892f, "Фаст фуд"))
        entries.add(PieEntry(6566f, "Различные товары"))
        entries.add(PieEntry(20126f, "Остальное"))


        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "Расходы")
        dataSet.colors = colors

        chart.data = PieData(dataSet).apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(chart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }
        chart.invalidate()

    }
}