package reyclerView.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import reyclerView.data.MovieFigure

class Adapter(
    onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer<MovieFigure>(this, MovieFigureDiffUtilCallback())
    private val delegatesManager = AdapterDelegatesManager<List<MovieFigure>>()

    init {
        delegatesManager.addDelegate(ActorAdapterDelegate(onItemClick))
            .addDelegate(FilmDirectorAdapterDelegate(onItemClick))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(differ.currentList, position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(differ.currentList, position, holder)
    }

    class MovieFigureDiffUtilCallback : DiffUtil.ItemCallback<MovieFigure>() {
        override fun areItemsTheSame(oldItem: MovieFigure, newItem: MovieFigure): Boolean {
            return when {
                oldItem is MovieFigure.Actor && newItem is MovieFigure.Actor -> oldItem.id == newItem.id
                oldItem is MovieFigure.FilmDirector && newItem is MovieFigure.FilmDirector -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: MovieFigure, newItem: MovieFigure): Boolean {
            return oldItem == newItem
        }
    }

    fun updateMovieFigures(newMovieFigure: List<MovieFigure>) {
        differ.submitList(newMovieFigure)
    }


}