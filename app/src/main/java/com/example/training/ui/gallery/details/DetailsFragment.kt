package com.example.training.ui.gallery.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.training.R
import com.example.training.databinding.FragmentDetailsBinding
import com.example.training.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    //  private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(FragmentDetailsBinding.bind(view)) {
            val photo = args.photo
            Glide.with(this@DetailsFragment).load(photo.url)
                .error(com.google.android.material.R.drawable.mtrl_ic_error).listener(

                object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.isVisible = true
                        description.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.isVisible = false
                        description.isVisible = true
                        return false
                    }
                }


            ).into(imageView)
            description.text = photo.author

        }


//        val adapter = GalleryAdapter()
//        binding.apply {
//
//            recyclerView.setHasFixedSize(true)
//            recyclerView.adapter = adapter
//        }
//        lifecycleScope.launch {
//            viewModel.searchPictures().collectLatest {
//                adapter.submitData(viewLifecycleOwner.lifecycle, it)
//            }
//        }
    }


}