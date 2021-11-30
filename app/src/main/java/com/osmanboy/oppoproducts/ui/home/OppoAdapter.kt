package com.osmanboy.oppoproducts.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.osmanboy.oppoproducts.R
import com.osmanboy.oppoproducts.data.Oppo
import com.osmanboy.oppoproducts.databinding.OppoItemBinding
typealias OnClick = (oppo:Oppo)->Unit
/**
 * Created by Osman Boy on 11/28/2021.
 **/
class OppoAdapter(val context:Context,val oppoProductsList:List<Oppo>): RecyclerView
.Adapter<OppoAdapter
.OppoViewHolder>() {

    var onClick:OnClick?= null

    inner class OppoViewHolder(val view: OppoItemBinding):RecyclerView.ViewHolder(view.root){

        init {
            view.root.setOnClickListener {
                onClick?.invoke(oppoProductsList[adapterPosition])
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OppoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OppoItemBinding.inflate(inflater,parent,false)
        return OppoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OppoViewHolder, position: Int) {
        val positionO = oppoProductsList[position]
        holder.view.productImage.setImageURI(positionO.imageUri.toUri())
        holder.view.productName.setText(positionO.name)
        holder.view.productPrice.setText((positionO.price).toString() + " Т")
    }

    override fun getItemCount()= oppoProductsList.size

}

