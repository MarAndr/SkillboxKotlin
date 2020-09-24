package module16

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillboxkotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_location.*
import module16.data.Data
import module16.extensions.inflate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class AdapterLocation(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<AdapterLocation.Holder>() {

    private val differ = AsyncListDiffer<Data>(this, LocationDiffUtilCallback())

    fun updateAdapter(newItem: List<Data>){
        differ.submitList(newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_location), onItemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    class Holder(override val containerView: View, onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        private val formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy").withZone(ZoneId.systemDefault())

        fun bind(locationItem: Data){
            tv_locationItem_latData.text = locationItem.lat.toString()
            tv_locationItem_lngData.text = locationItem.lng.toString()
            tv_locationItem_speedData.text = locationItem.speed.toString()
            tv_locationItem_accuracyData.text = locationItem.accuracy.toString()
            tv_locationItem_timeData.text = formatter.format(locationItem.currentTime)
            Glide.with(itemView)
                .load(locationItem.imageLink)
                .centerCrop()
                .into(iv_locationItem)
        }
    }

    class LocationDiffUtilCallback: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return when{
                oldItem is Data && newItem is Data -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

    }
}