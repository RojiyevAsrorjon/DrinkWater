package uz.gita.drinkwater.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.model.WaterClass
import uz.gita.drinkwater.presentation.convertToDate
import java.text.SimpleDateFormat
import java.util.*

class RecyclerAdapter : ListAdapter<WaterClass, RecyclerAdapter.ViewHolder>(DiffItem) {
    private var listener : ((WaterClass, ImageView) -> Unit)? = null
    object DiffItem : DiffUtil.ItemCallback<WaterClass>() {
        override fun areItemsTheSame(oldItem: WaterClass, newItem: WaterClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WaterClass, newItem: WaterClass): Boolean {
            return oldItem.drunkTime == newItem.drunkTime && oldItem.volume == newItem.volume
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.drunkTime)
        private val volume : TextView = view.findViewById(R.id.cupVolume)
        init {
            itemView.setOnClickListener {
                listener?.invoke(getItem(absoluteAdapterPosition),view.findViewById(R.id.moreButton))
            }
        }
        fun bind() {
            val data: WaterClass = getItem(absoluteAdapterPosition)
            time.text = convertToDate(data.drunkTime)
            volume.text = data.volume.toString()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItemListener(block: (WaterClass, ImageView) -> Unit) {
        listener = block
    }
}