package module22

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import module22.extensions.autoCleared
import com.example.skillboxkotlin.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.fragment_movielist.*
import kotlinx.android.synthetic.main.fragment_movielist.autoCompleteText
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class MovieListFragment : Fragment(R.layout.fragment_movielist), GetMovieScore {


    companion object {
        private const val TAG = "MovieListFragment"
    }

    private var movieListAdapter: MovieListAdapter by autoCleared()
    private var movieList = listOf<RemoteMovie>()
    private val viewModel: MovieListViewModel by viewModels()
    private var type: String? = null
    private val repository = MovieRepository()
    private val mainHandler = Handler(Looper.getMainLooper())
    var source: String? = null
    var value: String? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val option = resources.getStringArray(R.array.filmTypes)
        val dropDownAdapter = ArrayAdapter<String>(requireContext(), R.layout.option_item, option)
        autoCompleteText.setText(dropDownAdapter.getItem(0).toString(), false)
        autoCompleteText.setAdapter(dropDownAdapter)
        initList()
        bind()
//        observeMovieListLiveData()
//        observeIsLoadingLiveData()
//        observeNoNetworkErrorLiveData()
//        observeHasFilmFoundLiveData()
    }

    private fun initList() {
        movieListAdapter = MovieListAdapter { index ->
            addScoreDialog(index)
        }
        with(rv_fragmentMovieList_filmList) {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun addScoreDialog(index: Int) {
        AddScoreDialog.newInstance(index)
            .show(childFragmentManager, "addScoreDialog")
        movieListAdapter.notifyDataSetChanged()
    }

    private fun bind() {
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    type = autoCompleteText.text.toString()
                    val queryText =
                        textInputLayout_fragmentMovieList_filmName.editText?.text.toString().trim()
                    val yearText =
                        textInputLayout_fragmentMovieList_filmYear.editText?.text.toString().trim()
                    if (type == "not selected") {
                        search(queryText, yearText, null)
                    } else if (!isYearValidate()) {
                        textInputLayout_fragmentMovieList_filmYear.error =
                            getString(R.string.errorYearFilm)
                    } else {
                        search(queryText, yearText, type)
                        textInputLayout_fragmentMovieList_filmYear.isErrorEnabled = false
                    }
                }
            }
    }

    private fun isYearValidate(): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy").withZone(ZoneId.systemDefault())
        val presentTime = formatter.format(org.threeten.bp.Instant.now())
        val validRange = 1850 until (presentTime.toInt() + 1)
        val text = textInputLayout_fragmentMovieList_filmYear.editText?.text.toString().trim()
            .toIntOrNull()
        return text in validRange
    }

    private fun changeElements(
        movies: List<RemoteMovie>,
        source: String?,
        value: String?,
        updateIndex: Int
    ) {
        repository.changeData(movies, source, value, updateIndex) { newMovies ->
            val moshi = Moshi.Builder().build()
            val types = Types.newParameterizedType(
                List::class.java,
                RemoteMovie::class.java
            )
            val adapter = moshi.adapter<List<RemoteMovie>>(types)
            val newMoviesJson = adapter.toJson(newMovies)
            Log.d(TAG, "movies = $newMoviesJson")
            movieListAdapter.updateMovieList(newMovies)

        }
    }

    private fun search(text: String, year: String, type: String?) {
        repository.searchMovie(
            text, year, type
        ) { movies ->
            movieList = movies
            mainHandler.post {
                movieListAdapter.updateMovieList(movies)
            }

        }
    }

    override fun getMovieScore(index: Int, source: String, value: String) {
        this.source = source
        this.value = value
        changeElements(movieList, source, value, index)
    }

    //    private fun bindViewModel() {
//        view.let { it as ViewGroup }
//            .children
//            .mapNotNull { it as? Button }
//            .forEach { button ->
//                button.setOnClickListener {
//                    type = autoCompleteText.text.toString()
//                    val queryText = textInputLayout_fragmentMovieList_filmName.editText?.text.toString().trim()
//                    val yearText = textInputLayout_fragmentMovieList_filmYear.editText?.text.toString().trim()
//                    if (type == "not selected"){
//                        viewModel.search(queryText, yearText, null)
//                    } else if(!isYearValidate()) {
//                        textInputLayout_fragmentMovieList_filmYear.error = getString(R.string.errorYearFilm)
//                    } else {
//                        viewModel.search(queryText, yearText, type)
//                        textInputLayout_fragmentMovieList_filmYear.isErrorEnabled = false
//                    }
//                }
//            }
//    }

    //    private fun updateLoadingState(isLoading: Boolean){
//        rv_fragmentMovieList_filmList.isVisible = isLoading.not()
//        prb_fragmentMovieList.isVisible = isLoading
//        btn_fragmentMovieList_searchFilm.isEnabled = isLoading.not()
//        textInputLayout_fragmentMovieList_filmName.isEnabled = isLoading.not()
//        textInputLayout_fragmentMovieList_filmYear.isEnabled = isLoading.not()
//    }

//    private fun changeFilmScore(index: Int){
//    }
//
//
//
//    private fun observeMovieScoreLiveData(){
//        viewModel.chosenMovie.observe(viewLifecycleOwner, Observer {
//
//        })
//    }


//    private fun observeMovieListLiveData(){
//        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer { movies ->
//            movieListAdapter.updateMovieList(movies)
//            movieListAdapter.notifyDataSetChanged()
//        })
//    }
//
//    private fun observeIsLoadingLiveData(){
//            viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer {isLoading ->
//                updateLoadingState(isLoading)
//            })
//    }
//
//    private fun observeNoNetworkErrorLiveData(){
//        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
//            handleNotNetworkError(it)
//        })
//    }
//
//    private fun observeHasFilmFoundLiveData(){
//        viewModel.hasListFoundLiveData.observe(viewLifecycleOwner, Observer {isFilmFound ->
//            handleEmptyList(isFilmFound)
//        })
//    }

//    private fun handleEmptyList(isFilmFound: Boolean){
//        tv_fragmentMovieList_filmNotFoundMessage.isVisible = isFilmFound
//        btn_fragmentMovieList_repeatRequest.isVisible = isFilmFound
//    }
//
//    private fun handleNotNetworkError(error: Throwable){
//        val errorMessage = error.message
//        tv_fragmentMovieList_noNetworkError.isVisible = errorMessage != "network"
//        btn_fragmentMovieList_repeatRequest.isVisible = errorMessage != "network"
//    }
}