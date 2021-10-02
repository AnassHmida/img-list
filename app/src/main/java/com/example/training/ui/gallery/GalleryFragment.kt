package com.example.training.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.example.training.R
import com.example.training.databinding.FragmentGalleryBinding
import com.example.training.models.picture
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



@AndroidEntryPoint class GalleryFragment : Fragment(R.layout.fragment_gallery),GalleryAdapter.onItemClickListener {
    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        val adapter = GalleryAdapter(this)
        with(binding){
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        lifecycleScope.launch {
            viewModel.searchPictures().collectLatest {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun onItemClick(photo: picture) {
val action = GalleryFragmentDirections.actionGalleryfragmentToDetailsFragment(photo)
 findNavController().navigate(action)
    }


}