package com.nikhil.hiltwithdatabinding.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikhil.hiltwithdatabinding.R
import com.nikhil.hiltwithdatabinding.databinding.ItemHerosListBinding
import com.nikhil.hiltwithdatabinding.responses.HeroResponse
import kotlinx.android.synthetic.main.item_heros_list.view.*


class HerosHomeAdapter :
    ListAdapter<HeroResponse, HerosHomeAdapter.HerosViewHolder> {

    inner class HerosViewHolder(itemView: ItemHerosListBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        var binding: ItemHerosListBinding = itemView

    }

    constructor() : super(differCallback) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosViewHolder {
        val itemHeroListBinding: ItemHerosListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_heros_list, parent, false
        )

        return HerosViewHolder(itemHeroListBinding)
    }

    override fun onBindViewHolder(holder: HerosViewHolder, position: Int) {
        val hero = getItem(position)
        holder.binding.homeAdapterItem = hero
        /*holder.itemView.apply {
            Glide.with(this).load(hero.imageurl).into(iv_heroImage)
            tv_name.text = hero.name
            tv_Team.text = hero.team
            tv_realName.text = hero.realname
            tv_publisher.text = hero.publisher
            tv_createdBy.text = hero.createdby
        }*/
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<HeroResponse>() {
            override fun areItemsTheSame(oldItem: HeroResponse, newItem: HeroResponse): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: HeroResponse, newItem: HeroResponse): Boolean {
                return oldItem == newItem
            }

        }
    }
}