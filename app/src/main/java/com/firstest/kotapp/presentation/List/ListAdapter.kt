package com.firstest.kotapp.presentation.List

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstest.kotapp.R
import com.firstest.kotapp.data.local.models.GenshinChar
import com.squareup.picasso.Picasso

class ListAdapter
    (private val values: List<GenshinChar>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        var txtHeader: TextView
        val CharIcon: ImageView = itemView.findViewById(R.id.icon)

        init {
            txtHeader = layout.findViewById<View>(R.id.firstLine) as TextView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(
            parent.context)
        val v: View = inflater.inflate(R.layout.text_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = values[position].name
        holder.txtHeader.text = values[position].name
        Picasso.get().load(values[position].image).resize(100,100).into(holder.CharIcon)
    }

    override fun getItemCount(): Int {
        return values.size
    }

}