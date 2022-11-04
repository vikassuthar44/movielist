package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    val apiService: ApiService
):ApiHelper {

    override suspend fun getMovieList(): Response<MovieListResponse> = apiService.getMovieList(apikey = BuildConfig.API_KEY)

}