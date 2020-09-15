package reyclerView.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillboxkotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_actor.*

abstract class BaseMovieFiguresHolder(
    final override val containerView: View,
    onItemClick: (position: Int) -> Unit
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    protected fun bindMainInfo(
        name: String,
        age: Int,
        isAward: Boolean,
        avatarLink: String
    ) {
        ageTextView.text = itemView.context.resources.getString(R.string.agePlaceHolder, age)
        nameTextView.text = name
        if (isAward) awardTextView.visibility = View.VISIBLE else awardTextView.visibility = View.GONE

        Glide.with(itemView)
            .load(avatarLink)
            .placeholder(R.drawable.ic_baseline_person)
            .into(avatarImageView)
    }
}