package ru.hivislav.testcinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.hivislav.testcinema.model.entities.MovieListDTO
import ru.hivislav.testcinema.model.repository.RepositoryImpl

class MainFragmentViewModel(
    private val repositoryImpl: RepositoryImpl = RepositoryImpl(),
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
): ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getMoviesFromServer() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getMovies(callback)
    }

    private val callback = object: Callback<MovieListDTO> {
        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(AppState.Success(it))
                }
            } else {
                response.body()?.let {
                    val error = Throwable(response.code().toString())
                    liveData.postValue(AppState.Error(error))
                }
            }
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            liveData.postValue(AppState.Error(t))
        }
    }
}