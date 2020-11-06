package module22

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytestinglaboratory.list_pets.extensions.inflate
import com.example.skillboxkotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.iv_itemMovie_poster
import kotlinx.android.synthetic.main.item_movie.tv_itemMovie_id
import kotlinx.android.synthetic.main.item_movie.tv_itemMovie_title
import kotlinx.android.synthetic.main.item_movie.tv_itemMovie_type
import kotlinx.android.synthetic.main.item_movie.tv_itemMovie_year
import kotlinx.android.synthetic.main.item_movie_linear.*

class MovieListAdapter(
    val onItemClick: (index: Int) -> Unit
): RecyclerView.Adapter<MovieListAdapter.Holder>() {

    private var movies: List<RemoteMovie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_movie_linear), onItemClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    class Holder(override val containerView: View, onItemClick: (index: Int) -> Unit): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(movie: RemoteMovie){
            tv_itemMovie_title.text = movie.title
            tv_itemMovie_id.text = movie.id
            tv_itemMovie_year.text = movie.year
            tv_itemMovie_type.text = movie.type
            tv_itemMovie_rating.text = movie.rating.toString()
            tv_itemMovie_score.text = movie.score.toString()

            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.poster_isnot_available)
                .into(iv_itemMovie_poster)
        }
    }

    fun updateMovieList(newList: List<RemoteMovie>){
        movies = newList
    }
}