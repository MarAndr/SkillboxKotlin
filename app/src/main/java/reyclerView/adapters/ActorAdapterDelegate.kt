package reyclerView.adapters

import android.view.View
import android.view.ViewGroup
import com.example.skillboxkotlin.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import reyclerView.data.MovieFigure
import reyclerView.extensions.inflate

class ActorAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<MovieFigure.Actor, MovieFigure, ActorAdapterDelegate.ActorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ActorHolder {
        return ActorHolder(
            parent.inflate(R.layout.item_actor, false), onItemClick
        )
    }

    override fun isForViewType(
        item: MovieFigure,
        items: MutableList<MovieFigure>,
        position: Int
    ): Boolean {
        return item is MovieFigure.Actor
    }

    override fun onBindViewHolder(
        item: MovieFigure.Actor,
        holder: ActorHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ActorHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseMovieFiguresHolder(view, onItemClick), LayoutContainer {
        fun bind(movieFigure: MovieFigure.Actor) {
            bindMainInfo(
                movieFigure.name,
                movieFigure.age,
                movieFigure.isGetOscar,
                movieFigure.avatarLink
            )
        }
    }


}