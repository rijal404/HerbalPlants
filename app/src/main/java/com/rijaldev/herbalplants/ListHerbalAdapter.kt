/* package com.rijaldev.herbalplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListHerbalAdapter(private val listHerbal: ArrayList<Herbal>) : RecyclerView.Adapter<ListHerbalAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_row_herbal, viewGroup,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHerbal[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listHerbal[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHerbal.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        private var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        fun bind(herbal: Herbal) {
            with(itemView){
                tvName.text = herbal.name
                tvDetail.text = herbal.detail
                Glide.with(this)
                    .load(herbal.photo)
                    .apply(RequestOptions().override(55,55))
                    .into(imgPhoto)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(herbal: Herbal)
    }
}

 */
package com.rijaldev.herbalplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListHerbalAdapter(private val listHerbal: ArrayList<Herbal>) : RecyclerView.Adapter<ListHerbalAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_herbal, viewGroup,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val herbal = listHerbal[position]

        holder.tvName.text =herbal.name
        holder.tvDetail.text = herbal.detail
        Glide.with(holder.itemView.context)
            .load(herbal.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listHerbal[holder.absoluteAdapterPosition])
        }

    }

    override fun getItemCount(): Int {
        return listHerbal.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallback {
        fun onItemClick(herbal: Herbal)
    }
}