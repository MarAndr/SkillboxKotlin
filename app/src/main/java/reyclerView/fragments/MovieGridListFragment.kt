package reyclerView.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.skillboxkotlin.R
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.fragment_listfragment.*
import reyclerView.OffsetItemDecoration
import reyclerView.extensions.DialogButtonClick
import reyclerView.data.MovieFigure
import reyclerView.data.MovieFigureEnum
import reyclerView.adapters.Adapter
import reyclerView.dialogs.InfoDialogFragment
import reyclerView.extensions.autoCleared

class MovieGridListFragment : Fragment(R.layout.fragment_listfragment),
    DialogButtonClick {

    private var movieFigures = arrayListOf<MovieFigure>()
    private var movieFiguresAdapter by autoCleared<Adapter>()
    private var dialog: AlertDialog? = null
    private var isDialog = false

    companion object {
        private const val KEY_MOVIE_FIGURES_LIST = "key_movie_figures_list"
        private const val IS_DIALOG = "is_dialog"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            movieFigures = savedInstanceState.getParcelableArrayList(KEY_MOVIE_FIGURES_LIST)!!
            isDialog = savedInstanceState.getBoolean(IS_DIALOG)
        } else {
            movieFigures = arrayListOf(
                MovieFigure.Actor(
                    id = 1,
                    name = resources.getString(R.string.DiCaprioName),
                    age = 45,
                    avatarLink = resources.getString(R.string.avatarDiCaprioLink),
                    isGetOscar = true
                ),
                MovieFigure.Actor(
                    id = 2,
                    name = resources.getString(R.string.PittName),
                    age = 56,
                    avatarLink = resources.getString(R.string.avatarPittLink),
                    isGetOscar = true
                ), MovieFigure.Actor(
                    id = 3,
                    name = resources.getString(R.string.CooperName),
                    age = 45,
                    avatarLink = resources.getString(R.string.avatarCooperLink),
                    isGetOscar = false
                ), MovieFigure.FilmDirector(
                    id = 4,
                    name = resources.getString(R.string.SpielbergName),
                    age = 73,
                    avatarLink = resources.getString(R.string.avatarSpielbergLink),
                    genres = resources.getString(R.string.adventuresGenre),
                    isGetOscar = true
                ), MovieFigure.FilmDirector(
                    id = 5,
                    name = resources.getString(R.string.JarmuschName),
                    age = 67,
                    avatarLink = resources.getString(R.string.avatarJarmuschLink),
                    genres = resources.getString(R.string.dramaGenre),
                    isGetOscar = false
                ), MovieFigure.FilmDirector(
                    id = 6,
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
            selectMovieFigure()
        }
        updateAdapter()

        if (isDialog) selectMovieFigure()

    }


    private fun init() {
        movieFiguresAdapter = Adapter { position: Int ->
            deleteMovieFigure(position)
        }

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        with(itemView_listFragment) {
            adapter = movieFiguresAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            val offsetItemDecoration = OffsetItemDecoration()
            addItemDecoration(offsetItemDecoration)
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun selectMovieFigure() {
        isDialog = true
        val listOfFigures = MovieFigureEnum.values().map { it.movieFigureName }.toTypedArray()
        dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose a profession of a movie figure")
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .setItems(listOfFigures) { _, which ->
                val movieFigureDialogFragment = InfoDialogFragment.newInstance(which)
                movieFigureDialogFragment.show(childFragmentManager, listOfFigures[which])
                isDialog = false
            }
            .setOnCancelListener { isDialog = false }
            .show()

    }

    private fun addMovieFigure(movieFigure: MovieFigure) {
        movieFigures.add(0, movieFigure)
        updateAdapter()
        movieFiguresAdapter.notifyItemInserted(0)
        itemView_listFragment.scrollToPosition(0)
    }

    private fun deleteMovieFigure(position: Int) {
        if (position !in 0..movieFigures.size) return
        movieFigures.removeAt(position)
        movieFiguresAdapter.notifyItemRemoved(position)
        updateAdapter()


    }

    private fun updateAdapter() {
        tv_movieFiguresListFragment.isVisible = movieFigures.isEmpty()
        movieFiguresAdapter.updateMovieFigures(movieFigures)
    }

    override fun onPositiveButtonClick(movieFigure: MovieFigure) {
        addMovieFigure(movieFigure)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_MOVIE_FIGURES_LIST, movieFigures)
        outState.putBoolean(IS_DIALOG, isDialog)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }
}