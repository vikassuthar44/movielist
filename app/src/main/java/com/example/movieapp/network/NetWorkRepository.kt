package com.example.movieapp.network

import javax.inject.Inject

class NetWorkRepository @Inject constructor(
    val apiHelper: ApiHelper
) {

    suspend fun getMovieList() = apiHelper.getMovieList()
}

