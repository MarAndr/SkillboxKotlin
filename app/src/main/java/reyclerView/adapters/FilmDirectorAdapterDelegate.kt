package reyclerView.adapters

import android.view.View
import android.view.ViewGroup
import com.example.skillboxkotlin.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_director.*
import reyclerView.data.MovieFigure
import reyclerView.extensions.inflate

class FilmDirectorAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<MovieFigure.FilmDirector, MovieFigure, FilmDirectorAdapterDelegate.DirectorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): DirectorHolder {
        return DirectorHolder(
            parent.inflate(R.layout.item_director, false),
            onItemClick)
    }

    override fun isForViewType(
        item: MovieFigure,
        items: MutableList<MovieFigure>,
        position: Int
    ): Boolean {
        return item is MovieFigure.FilmDirector
    }

    override fun onBindViewHolder(
        item: MovieFigure.FilmDirector,
        holder: DirectorHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class DirectorHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): BaseMovieFiguresHolder(view, onItemClick), LayoutContainer{

        fun bind(movieFigure: MovieFigure.FilmDirector){
            bindMainInfo(movieFigure.name, movieFigure.age, movieFigure.isGetOscar, movieFigure.avatarLink)

            genreTextView.text = itemView.context.resources.getString(R.string.genrePlaceHolder, movieFigure.genres)
        }
    }


}