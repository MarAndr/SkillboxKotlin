package reyclerView

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_listfragment.*

class MovieFiguresListFragment : Fragment(R.layout.fragment_listfragment), DialogButtonClick {
    var movieFigures: List<MovieFigures> = emptyList()
    var movieFiguresAdapter: Adapter? = null
    val KEY_MOVIE_FIGURES_LIST = "key_movie_figures_lis"
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){
            movieFigures = savedInstanceState.getParcelableArray("key")!!
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

        movieFiguresAdapter?.updateMovieFigures(movieFigures)
        movieFiguresAdapter?.notifyDataSetChanged()

    }

    private fun init() {
        movieFiguresAdapter = Adapter { position: Int -> deleteMovieFigure(position) }
        with(itemView_listFragment){
            adapter = movieFiguresAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun makeDialog(){
        AddInfoDialogFragment().show(childFragmentManager, "dialogFragment")
    }

    private fun addMovieFigure(name: String, age: Int, profession: String){
        val newMovieFigure = when(profession){
            "Actor" -> MovieFigures.Actor(name = name, age = age, avatarLink = "", isGetOscar = true)
            "Director" -> MovieFigures.FilmDirector(name = name, age = age, avatarLink = "", isGetOscar = true, genres = "comedy")
            else -> error("wrong choosing of profession")
        }

        movieFigures = listOf(newMovieFigure) + movieFigures
        movieFiguresAdapter?.updateMovieFigures(movieFigures)
        movieFiguresAdapter?.notifyItemInserted(0)
        itemView_listFragment.scrollToPosition(0)
    }

    private fun deleteMovieFigure(position: Int){
        movieFigures = movieFigures.filterIndexed { index, movieFigures -> index != position }
        movieFiguresAdapter?.updateMovieFigures(movieFigures)
        movieFiguresAdapter?.notifyItemRemoved(position)
        if (movieFigures.isEmpty()){
            container_listFragment.addView(addTextView(requireContext()))
        }

    }

    override fun onPositiveButtonClick(name: String, age: Int, profession: String) {
        addMovieFigure(name, age, profession)
    }

    override fun onNegativeButtonClick() {
        Toast.makeText(context, "on negative", Toast.LENGTH_SHORT).show()
    }

    private fun addTextView(context: Context) = TextView(context).apply {
        text = "The list is empty"
        textSize = 30f
        setPadding(220,600,0,0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_MOVIE_FIGURES_LIST, movieFigures.toTypedArray())
    }
}