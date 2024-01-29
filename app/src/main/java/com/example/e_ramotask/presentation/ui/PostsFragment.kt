package com.example.e_ramotask.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_ramotask.R
import com.example.e_ramotask.databinding.FragmentPostsBinding
import com.example.e_ramotask.presentation.adapters.PostsAdapter
import com.example.e_ramotask.presentation.navigators.PostsNavigator
import com.example.e_ramotask.presentation.viewmodels.PostsViewModel
import com.example.e_ramotask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), PostsNavigator {

    private lateinit var binding: FragmentPostsBinding
    private val postsViewModel: PostsViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_posts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerPostsData()
        postsViewModel.postsData()
        initResponseApiForPosts()
    }



    private fun initResponseApiForPosts() {
        postsViewModel.postsData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    postsAdapter.differ.submitList(it.data!!)
                    hideProgress()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
            }
        }
    }


    private fun setUpRecyclerPostsData() {
        postsAdapter = PostsAdapter(this)
        binding.postsRecyclerView.adapter = postsAdapter
    }


    private fun showProgress(){
        binding.progress.root.visibility = View.VISIBLE
    }


    private fun hideProgress(){
        binding.progress.root.visibility = View.GONE
    }

    override fun onItemClick(id: Int) {
        findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(id))
    }

}