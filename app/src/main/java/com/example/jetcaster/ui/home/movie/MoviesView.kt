package com.example.jetcaster.ui.home.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun ShowMovies() {
    //LaunchedEffect(key1 = true) { //hace que solo se ejecute una vez
    val movies = produceState<List<ServerMovie>>(initialValue = emptyList()) {

        value = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)
            .getMovies()
            .results
        // .forEach { Log.d("RetrofitRequest: ", it.toString()) }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Movies") })
        }) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier.padding(padding),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(movies.value) { movie ->
                    MovieItem(movie = movie)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: ServerMovie) {
    Column() {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w200/${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
        )
        Text(
            text = movie.title,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            maxLines = 2
        )
    }
}