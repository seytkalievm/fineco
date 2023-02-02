package com.example.fineco.ui

import android.app.Activity
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.fineco.R

fun Button(activity: Activity, title: String, context: Context, onClick: () -> Unit): Button {
    val marginSide = activity.resources.getDimensionPixelSize(R.dimen.button_margin_side)
    val white = ContextCompat.getColor(context, R.color.white)

    val layoutParameters = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        activity.resources.getDimensionPixelSize(R.dimen.button_height)
    ).apply {
        marginEnd = marginSide
        marginStart = marginSide
        topMargin = activity.resources.getDimensionPixelSize(R.dimen.button_margin_top)
    }

    val button = Button(context).apply {
        text = title
        if (Build.VERSION.SDK_INT >= 29)
            background.colorFilter = BlendModeColorFilter(white, BlendMode.MULTIPLY)
        else
            background.setColorFilter(white, PorterDuff.Mode.MULTIPLY)
        layoutParams = layoutParameters
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        setOnClickListener {
            onClick()
        }
    }

    return button
}