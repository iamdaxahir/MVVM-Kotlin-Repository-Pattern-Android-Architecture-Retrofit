package com.dashrath.kotlinrepositorypattern.githubusers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dashrath.kotlinrepositorypattern.R
import com.dashrath.kotlinrepositorypattern.databinding.RowUserBinding
import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserModel

class UserListAdapter(context: Context) :
    ListAdapter<GitUserModel, UserListAdapter.UserViewHolder>(DiffCallback()) {

    private var userItemClick: UserItemClick

    init {
        userItemClick = context as UserItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val rowUserBinding: RowUserBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.row_user, parent, false)
        return UserViewHolder(rowUserBinding)
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
        viewHolder.rowUserBinding.userRow.setOnClickListener {
            userItemClick.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class UserViewHolder(val rowUserBinding: RowUserBinding) :
        RecyclerView.ViewHolder(rowUserBinding.root) {

        fun bind(user: GitUserModel) {
            rowUserBinding.userData = user

        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<GitUserModel>() {
        override fun areItemsTheSame(oldItem: GitUserModel, newItem: GitUserModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GitUserModel, newItem: GitUserModel) =
            oldItem == newItem
    }

    interface UserItemClick {
        fun onItemClick(gitUserModel: GitUserModel)
    }

}