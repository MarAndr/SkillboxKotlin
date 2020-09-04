package reyclerView

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillboxkotlin.R

class Adapter(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movieFigures: List<MovieFigure> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_ACTOR -> ActorHolder(parent.inflate(R.layout.item_actor, false), onItemClick)
            TYPE_DIRECTOR -> DirectorHodler(parent.inflate(R.layout.item_director, false), onItemClick)
            else -> error("Incorrect viewType=$viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(movieFigures[position]){
            is  MovieFigure.Actor -> TYPE_ACTOR
            is  MovieFigure.FilmDirector -> TYPE_DIRECTOR
        }
    }

    override fun getItemCount(): Int {
        return movieFigures.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ActorHolder -> {
                val movieFigure = movieFigures[position].let { it as? MovieFigure.Actor }
                    ?: error("Movie Figure on position = $position is not actor")
                holder.bind(movieFigure)
            }
            is DirectorHodler -> {
            val movieFigure = movieFigures[position].let { it as? MovieFigure.FilmDirector }
                ?: error("Movie Figure on position = $position is not film director")
            holder.bind(movieFigure)
        }
            else -> error("Incorrect view holder = $holder")
        }
    }

    abstract class BaseMovieFiguresHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.nameTextView)
        val textViewAge = view.findViewById<TextView>(R.id.ageTextView)
        val textViewAward = view.findViewById<TextView>(R.id.awardTextView)
        val imageViewAvatar = view.findViewById<ImageView>(R.id.avatarImageView)

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindMainInfo(
            name: String,
            age: Int,
            isAward: Boolean,
            avatarLink: String
        ) {
            textViewAge.text = itemView.context.resources.getString(R.string.agePlaceHolder, age)
            textViewName.text = name
            if (isAward) textViewAward.visibility = View.VISIBLE else textViewAward.visibility = View.GONE

            Glide.with(itemView)
                .load(avatarLink)
                .placeholder(R.drawable.ic_baseline_child)
                .into(imageViewAvatar)
        }
    }

    class ActorHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): BaseMovieFiguresHolder(view, onItemClick){
        fun bind(movieFigure: MovieFigure.Actor){
            bindMainInfo(movieFigure.name, movieFigure.age, movieFigure.isGetOscar, movieFigure.avatarLink)
        }
    }

    class DirectorHodler(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): BaseMovieFiguresHolder(view, onItemClick){
        val textViewGenres = view.findViewById<TextView>(R.id.genreTextView)

        fun bind(movieFigure: MovieFigure.FilmDirector){
            bindMainInfo(movieFigure.name, movieFigure.age, movieFigure.isGetOscar, movieFigure.avatarLink)

            textViewGenres.text = itemView.context.resources.getString(R.string.genrePlaceHolder, movieFigure.genres)
        }
    }

    fun updateMovieFigures(newMovieFigure: List<MovieFigure>){
        movieFigures = newMovieFigure
    }

    companion object{
        const val TYPE_ACTOR = 1
        const val TYPE_DIRECTOR = 2
    }
}