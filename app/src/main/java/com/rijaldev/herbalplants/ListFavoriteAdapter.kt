package com.rijaldev.herbalplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rijaldev.herbalplants.room.HerbalR

class ListFavoriteAdapter(private val herbalRs: ArrayList<HerbalR>): RecyclerView.Adapter<ListFavoriteAdapter.HerbalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerbalViewHolder {
        return HerbalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HerbalViewHolder, position: Int) {
        val herbal = herbalRs[position]

        holder.tvName.text =herbal.names
        holder.tvDetail.text = herbal.details
        Glide.with(holder.itemView.context)
            .load(herbal.photos)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)

    }

    override fun getItemCount(): Int = herbalRs.size

    class HerbalViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name_fav)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail_fav)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo_fav)
    }

    fun setData(list: List<HerbalR>) {
        herbalRs.clear()
        herbalRs.addAll(list)
        notifyDataSetChanged()
    }
}