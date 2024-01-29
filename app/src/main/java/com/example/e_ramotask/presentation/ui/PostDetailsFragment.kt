package com.example.e_ramotask.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.e_ramotask.R
import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.databinding.FragmentPostDetailsBinding
import com.example.e_ramotask.presentation.viewmodels.PostDetailsViewModel
import com.example.e_ramotask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailsBinding
    private val postsDetailsViewModel: PostDetailsViewModel by viewModels()
    private val args: PostDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_post_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postsDetailsViewModel.postDetailsData(args.postId)
        initResponseApiForPostDetails()
    }

    private fun initResponseApiForPostDetails() {
        postsDetailsViewModel.postDetailsUseCaseData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    setData(it.data!!)
                    hideProgress()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
            }
        }
    }

    private fun setData(data: GetPostsResponse) {
        binding.titleId.text = data.title
        binding.descriptionId.text = data.body
    }


    private fun showProgress(){
        binding.progress.root.visibility = View.VISIBLE
    }


    private fun hideProgress(){
        binding.progress.root.visibility = View.GONE
    }
}