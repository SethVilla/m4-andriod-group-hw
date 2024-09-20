package com.example.twoweeksofmovies

import android.os.Bundle
import android.os.ext.SdkExtensions
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twoweeksofmovies.data.MovieSource
import com.example.twoweeksofmovies.model.MovieRec
import com.example.twoweeksofmovies.ui.theme.TwoWeeksOfMoviesTheme

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwoWeeksOfMoviesTheme {
                Surface (modifier = Modifier.fillMaxSize().statusBarsPadding(),
                ) {
                    MoviesGrid(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                        ),
                    )
                }
            }
        }
    }
}

@RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
@Composable
fun MoviesGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(MovieSource.movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movieRec: MovieRec, modifier: Modifier = Modifier) {
    Card {
        Column  {
            Box {
                Image(
                    painter = painterResource(id = movieRec.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
//                        .size(200.dp) // Set image size (optional)
                        .align(Alignment.Center) // Align image to center
                )
            }

            Column {
                Text(
                    text = stringResource(id = movieRec.description),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end =16.dp,
                        bottom = 8.dp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    TwoWeeksOfMoviesTheme {
        val movie = MovieRec(R.string.blade_runner_2049,  R.drawable.blade_runner_2049)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieCard(movieRec = movie)
        }
    }
}