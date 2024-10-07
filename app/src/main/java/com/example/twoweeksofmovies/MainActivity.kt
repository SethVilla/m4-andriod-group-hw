package com.example.twoweeksofmovies

import android.os.Bundle
import android.os.ext.SdkExtensions
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                Surface (modifier = Modifier.fillMaxSize(),
                ) {
                    TwoWeeksOfMoviesApp()
                }
            }
        }
    }
}

@RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
@Composable
fun TwoWeeksOfMoviesApp(
    // sls-1
    movies : List<MovieRec> = MovieSource.movies
) {
    Scaffold ( modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }) { innerPadding ->
        MoviesGrid(
            movies, // sls01
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
    }
}

@RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
@Composable
fun MoviesGrid(
    //sls01
    movies: List<MovieRec>,
    modifier: Modifier = Modifier) {
    //sls01
    val expandedStatesMap = remember { mutableStateMapOf<MovieRec,Boolean>().apply {
        movies.forEach { movie -> this[movie] = false }
    } }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        //sls01
//        items(MovieSource.movies) { movie ->
        items(movies) { movie ->
            var isExpanded = expandedStatesMap[movie] ?: false
            MovieCard(
                movieRec = movie,
                onIconClick = {
                    expandedStatesMap[movie] = !(expandedStatesMap[movie] ?: false)
                },
                isExpanded = isExpanded
            )
        }
    }
}

@Composable
fun MovieCard(
    // sls01
    isExpanded: Boolean = false,
    onIconClick: () -> Unit,
    movieRec: MovieRec,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        // sls01
//        var expanded by remember { mutableStateOf(false) }

        Column (
            // sls01
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Box {
                Image(
                    painter = painterResource(id = movieRec.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )

            }
            // sls01
//            Spacer(modifier = Modifier.weight(1f))
//            MovieCardButton(
//                modifier = Modifier.align(Alignment.End),
//                isExpanded = isExpanded,
//                onIconClick = onIconClick //{ expanded = !expanded }
//            )
            // sls01
//            if (expanded) {
//            if(isExpanded){
                Column(
                ) {

                    Row {
                        Text(
                            text = stringResource(id = movieRec.title),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    top = 16.dp,
                                    end = 16.dp,
                                    bottom = 8.dp
                                )
//                                .fillMaxWidth()
                        )
                        // sls01
                        Spacer(modifier = Modifier.weight(1f))
                        MovieCardButton(
//                            modifier = Modifier.align(Alignment.End),
                            isExpanded = isExpanded,
                            onIconClick = onIconClick //{ expanded = !expanded }
                        )
                    }
                    if(isExpanded){
                        Text(
                            text = stringResource(id = movieRec.description),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 16.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            )
                        )
                    }
                }
//            } // if (isExpanded)
        }
    }
}

// sls01
@Composable
private fun MovieCardButton(
    isExpanded: Boolean,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onIconClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    TwoWeeksOfMoviesTheme {
//        val movie = MovieRec(R.string.blade_runner_2049_title, R.string.blade_runner_2049, R.drawable.blade_runner_2049)
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            MovieCard(movieRec = movie)
        TwoWeeksOfMoviesApp()
        }
    }
