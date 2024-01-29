package com.example.e_ramotask.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_ramotask.R
import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.databinding.PostItemBinding
import com.example.e_ramotask.presentation.navigators.PostsNavigator


class PostsAdapter(private val listener: PostsNavigator) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    class PostsViewHolder(itemView: View, val itemBinding: PostItemBinding) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<GetPostsResponse>() {
        override fun areItemsTheSame(
            oldItem: GetPostsResponse,
            newItem: GetPostsResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetPostsResponse,
            newItem: GetPostsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val itemBinding: PostItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.post_item, parent, false)
        return PostsViewHolder(itemBinding.root, itemBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemBinding.titleId.text = item.title
        holder.itemBinding.descriptionId.text = item.body

        holder.itemBinding.cardItemID.setOnClickListener {
            listener.onClickPostItem(item.id)
        }

    }

}