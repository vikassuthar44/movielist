package com.example.movieapp.network

import com.example.movieapp.data.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apikey: String
    ): Response<MovieListResponse>

}