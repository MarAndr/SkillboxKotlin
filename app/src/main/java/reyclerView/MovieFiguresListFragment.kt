package reyclerView

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_listfragment.*

class MovieFiguresListFragment : Fragment(R.layout.fragment_listfragment), DialogButtonClick {
    private var movieFigures: List<MovieFigures> = emptyList()
    private var movieFiguresAdapter by autoCleared<Adapter>()
    private val KEY_MOVIE_FIGURES_LIST = "key_movie_figures_lis"
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            movieFigures = savedInstanceState.getParcelableArray(KEY_MOVIE_FIGURES_LIST)!!
                .toList() as List<MovieFigures>
        } else {
            movieFigures = listOf(
                MovieFigures.Actor(
                    name = resources.getString(R.string.DiCaprioName),
                    age = 45,
                    avatarLink = resources.getString(R.string.avatarDiCaprioLink),
                    isGetOscar = true
                ),
                MovieFigures.Actor(
                    name = resources.getString(R.string.PittName),
                    age = 56,
                    avatarLink = resources.getString(R.string.avatarPittLink),
                    isGetOscar = true
                ), MovieFigures.Actor(
                    name = resources.getString(R.string.CooperName),
                    age = 45,
                    avatarLink = resources.getString(R.string.avatarCooperLink),
                    isGetOscar = false
                ), MovieFigures.FilmDirector(
                    name = resources.getString(R.string.SpielbergName),
                    age = 73,
                    avatarLink = resources.getString(R.string.avatarSpielbergLink),
                    genres = resources.getString(R.string.adventuresGenre),
                    isGetOscar = true
                ), MovieFigures.FilmDirector(
                    name = resources.getString(R.string.JarmuschName),
                    age = 67,
                    avatarLink = resources.getString(R.string.avatarJarmuschLink),
                    genres = resources.getString(R.string.dramaGenre),
                    isGetOscar = false
                ), MovieFigures.FilmDirector(
                    name = resources.getString(R.string.GilliamName),
                    age = 79,
                    avatarLink = resources.getString(R.string.avatarGilliamLink),
                    genres = resources.getString(R.string.comedyGenre),
                    isGetOscar = false
                )
            )
        }


        init()
        fab_listFragment.setOnClickListener {
            makeDialog()
        }

        updateAdapter()
        movieFiguresAdapter.notifyDataSetChanged()

    }

    private fun init() {
        movieFiguresAdapter = Adapter { position: Int -> deleteMovieFigure(position) }
        with(itemView_listFragment) {
            adapter = movieFiguresAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun makeDialog() {
        InfoDialogFragment().show(childFragmentManager, "dialogFragment")
    }

    private fun addMovieFigure(name: String, age: Int, profession: String, isAward: Boolean) {
        val newMovieFigure = when (profession) {
            "Actor" -> MovieFigures.Actor(
                name = name,
                age = age,
                avatarLink = "",
                isGetOscar = isAward
            )
            "Director" -> MovieFigures.FilmDirector(
                name = name,
                age = age,
                avatarLink = "",
                isGetOscar = isAward,
                genres = "comedy"
            )
            else -> error("wrong choosing of profession")
        }
        movieFigures = listOf(newMovieFigure) + movieFigures
        updateAdapter()
        movieFiguresAdapter.notifyItemInserted(0)
        itemView_listFragment.scrollToPosition(0)
    }

    private fun deleteMovieFigure(position: Int) {
        movieFigures = movieFigures.filterIndexed { index, movieFigures -> index != position }
        updateAdapter()
        movieFiguresAdapter.notifyItemRemoved(position)
    }

    private fun updateAdapter() {
        tv_movieFiguresListFragment.isVisible = movieFigures.isEmpty()
        movieFiguresAdapter.updateMovieFigures(movieFigures)
    }

    override fun onPositiveButtonClick(
        name: String,
        age: Int,
        profession: String,
        isAward: Boolean
    ) {
        addMovieFigure(name, age, profession, isAward)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_MOVIE_FIGURES_LIST, movieFigures.toTypedArray())
    }
}