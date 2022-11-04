package com.example.movieapp.network

import com.example.movieapp.data.MovieListResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovieList(): Response<MovieListResponse>

}