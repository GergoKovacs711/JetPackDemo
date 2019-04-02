package hu.gergo.kovacs .jetpackdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.gergo.kovacs.jetpackdemo.R
import hu.gergo.kovacs.jetpackdemo.model.Item
import kotlinx.android.synthetic.main.activity_main_item.view.*

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.03.30.
 */

class ItemAdapter(val appContext: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var items: List<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(appContext)
        val view = inflater.inflate(R.layout.activity_main_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position))

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Item?){
            with(itemView){
                item_text.text = item?.text ?: "No text"
                item_image.setImageResource(item?.imageResourceId ?: -1)
            }
        }
    }

    fun setItems(items: List<Item> ){
        this.items = items
        notifyDataSetChanged()
    }
}

