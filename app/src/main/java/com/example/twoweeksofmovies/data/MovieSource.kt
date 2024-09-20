package com.example.twoweeksofmovies.data

import android.os.ext.SdkExtensions
import androidx.annotation.RequiresExtension
import com.example.twoweeksofmovies.R
import com.example.twoweeksofmovies.model.MovieRec

object MovieSource {
    @RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
    val movies = listOf(
        MovieRec(R.string.alien_romulus, R.drawable.alien_romulus),
        MovieRec(R.string.alien_covenant, R.drawable.alien_covenant),
        MovieRec(R.string.alien_prometheus, R.drawable.alien_prometheus),
        MovieRec(R.string.blade_runner, R.drawable.blade_runner),
        MovieRec(R.string.blade_runner_2049, R.drawable.blade_runner_2049),
        MovieRec(R.string.matrix, R.drawable.matrix),
        MovieRec(R.string.kill_bill, R.drawable.kill_bill),
        MovieRec(R.string.kill_bill_2, R.drawable.kill_bill_2),
        MovieRec(R.string.deadpool, R.drawable.deadpool),
        MovieRec(R.string.biutiful, R.drawable.biutiful),
        MovieRec(R.string.cowboy_bebop,  R.drawable.cowboy_bebop),
        MovieRec(R.string.royal_tenenbaums, R.drawable.royal_tenenbaums),
        MovieRec(R.string.wes_anderson_hotel, R.drawable.wes_anderson_hotel),
        MovieRec(R.string.oldboy, R.drawable.oldboy)
    )
}