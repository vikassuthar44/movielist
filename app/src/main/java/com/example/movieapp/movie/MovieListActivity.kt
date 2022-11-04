package com.example.movieapp.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.MovieListResponse.MovieData
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.util.RequestRender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme() {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    RequestRender(
        state = movieListViewModel.movieList.collectAsState(),
        onSuccess = {
            LazyVerticalGrid(
                columns = Fixed(2),
                contentPadding = PaddingValues(all = 20.dp),
                content = {
                    items(items = it.results) { item ->
                        SingleMovieItem(movie = item)
                    }
                })

        },
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading....")
            }
        },
        onError = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it)
            }
        }
    )
}

@Composable
fun SingleMovieItem(
    movie: MovieData,
) {
    val painterImage = rememberAsyncImagePainter(model = BuildConfig.IMAGE_PATH + movie.posterPath)
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(all = 10.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(size = 20.dp))
            .padding(bottom = 10.dp)
            .clickable {

            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterImage,
                contentDescription = "movie",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = movie.title)
        }
    }
}