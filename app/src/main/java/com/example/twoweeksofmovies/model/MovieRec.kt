package com.example.twoweeksofmovies.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieRec(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)
