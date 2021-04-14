package com.example.githup.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githup.databinding.FollowerItemBinding
import com.example.githup.models.User

class FollowerListAdapter : RecyclerView.Adapter<FollowerListAdapter.ViewHolder>() {

    private val mUsers = ArrayList<User>()

    fun setData(items: ArrayList<User>) {
        mUsers.clear()
        mUsers.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mUsers[position])
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    inner class ViewHolder(private val binding: FollowerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvName.text = user.username
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.imgProfile)
        }

    }
}