package module22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieListViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val _movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    private val _chosenMovie = MutableLiveData<RemoteMovie>()
    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<Throwable>()
    private val _hasListFoundLiveData = MutableLiveData<Boolean>()

    val movieListLiveData: LiveData<List<RemoteMovie>> = _movieListLiveData
    val chosenMovie: LiveData<RemoteMovie> = _chosenMovie
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    val errorLiveData: LiveData<Throwable> = _errorLiveData
    val hasListFoundLiveData: LiveData<Boolean> = _hasListFoundLiveData


    fun search(text: String, year: String, type: String?) {
        _isLoadingLiveData.postValue(true)
        movieRepository.searchMovie(text, year, type,
            { movies ->
                _isLoadingLiveData.postValue(false)
                _movieListLiveData.postValue(movies)
            },
            { error ->
                _errorLiveData.postValue(error)
            },
            { isFilmFound ->
                _hasListFoundLiveData.postValue(isFilmFound)
            }
        )
    }

    fun addScore(index: Int){
        val chosenMovie = _movieListLiveData.value?.get(index)
        chosenMovie?.score?.forEach {
            it.source = "My Panorama"
            it.value = "22"
        }
        _chosenMovie.postValue(chosenMovie)
    }
}