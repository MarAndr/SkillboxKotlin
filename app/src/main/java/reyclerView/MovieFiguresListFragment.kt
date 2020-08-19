package reyclerView

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.dialog_dialogfragment.*
import kotlinx.android.synthetic.main.fragment_listfragment.*

class MovieFiguresListFragment : Fragment(R.layout.fragment_listfragment), DialogButtonClick {
    var movieFigures: List<MovieFigures> = emptyList()
    var movieFiguresAdapter: Adapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    private fun addMovieFigure(){
        val movieFiguresName = etName_dialogFragment.text.toString() //компилятор пишет, что это поле не может быть null, хотя инфу я туда передаю.
        val movieFiguresAge = etAge_dialogFragment.text.toString().toInt()
        val newMovieFigure = MovieFigures.Actor(name = movieFiguresName, age = movieFiguresAge, avatarLink = "https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_960_720.png", isGetOscar = true)
        movieFigures = listOf(newMovieFigure) + movieFigures
        movieFiguresAdapter?.updateMovieFigures(movieFigures)
        movieFiguresAdapter?.notifyItemInserted(0)
        itemView_listFragment.scrollToPosition(0)
    }

    private fun deleteMovieFigure(position: Int){
        movieFigures = movieFigures.filterIndexed { index, movieFigures -> index != position }
        movieFiguresAdapter?.updateMovieFigures(movieFigures)
        movieFiguresAdapter?.notifyItemRemoved(position)
    }

    override fun onPositiveButtonClick() {
        addMovieFigure()
    }

    override fun onNegativeButtonClick() {
        Toast.makeText(context, "on negative", Toast.LENGTH_SHORT).show()
    }
}