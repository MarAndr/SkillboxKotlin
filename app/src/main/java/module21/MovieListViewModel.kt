package module21

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestinglaboratory.my_training.network.movielist.RemoteMovie

class MovieListViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val _movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<Throwable>()
    private val _hasListFoundLiveData = MutableLiveData<Boolean>()

    val movieListLiveData: LiveData<List<RemoteMovie>> = _movieListLiveData
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
}