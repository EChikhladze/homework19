package com.example.homework18.presentation.usersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.homework18.data.User
import com.example.homework18.databinding.ItemUserBinding

class UsersRecyclerAdapter(
    private val users: List<User>,
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val user = users[adapterPosition]
            with(binding) {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvEmail.text = user.email
                Glide.with(itemView.context).load(user.avatar).transform(CircleCrop())
                    .into(imgAvatar)
            }

            itemView.setOnClickListener {
                onItemClickListener.invoke(user.id)
            }
        }
    }
}