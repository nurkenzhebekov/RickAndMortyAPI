package com.example.rickandmortyapi.ui.extensions

import android.widget.ImageView
import com.example.rickandmortyapi.R

fun ImageView.setLifeStatusIndicator(status: String) {
    val resourceId = when (status) {
        "Alive" -> R.drawable.ic_dot_green
        "Dead" -> R.drawable.ic_dot_red
        else -> R.drawable.ic_dot_grey
    }
    setImageResource(resourceId)
}