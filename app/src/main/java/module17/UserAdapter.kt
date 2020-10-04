package module17

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillboxkotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import my_training.recyclerview.inflate

class UserAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.Holder>() {

    var users: List<Person> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_user), onItemClick)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    class Holder(
        override val containerView: View, onItemClick: (position: Int) -> Unit

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

            containerView.setOnClickListener {
                containerView.findNavController()
                    .navigate(R.id.action_mainFragment_to_detailInfoFragment)
            }

            containerView.setOnLongClickListener {
                onItemClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind(user: Person) {
            nameTextView.text = user.name
            ageTextView.text = "${user.age}"
            if (user.isDeveloper) developerTextView.visibility =
                View.VISIBLE else developerTextView.visibility = View.GONE

            Glide.with(itemView)
                .load(user.avatarLink)
                .into(avatarImageView)
        }


    }


}