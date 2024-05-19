package com.example.rickandmortyapi.ui.extensions

import android.widget.ImageView
import com.example.rickandmortyapi.R

fun ImageView.setLifeStatusIndicator(status: String) {
    setImageResource(
        when (status) {
            "Alive" -> R.drawable.ic_dot_green
            "Dead" -> R.drawable.ic_dot_red
            else -> R.drawable.ic_dot_grey
        }
    )
}