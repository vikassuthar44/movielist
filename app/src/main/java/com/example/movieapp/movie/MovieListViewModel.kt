package com.example.movieapp.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieListResponse
import com.example.movieapp.network.NetWorkRepository
import com.example.movieapp.util.RequestState
import com.example.movieapp.util.RequestState.Idle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val netWorkRepository: NetWorkRepository
): ViewModel() {

    private val _movieList = MutableStateFlow<RequestState<MovieListResponse>>(Idle)
    val movieList = _movieList.asStateFlow()
    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieList.value = RequestState.Loading
            netWorkRepository.getMovieList().let {
                if(it.isSuccessful) {
                    _movieList.value = RequestState.Success(it.body())
                } else {
                    _movieList.value = RequestState.Error("Went something wrong!")
                }
            }
        }
    }
}