package com.bulochka.maximum_education.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.bulochka.maximum_education.databinding.FragmentImageBinding

class ImageFragment: Fragment() {

    private lateinit var _binding: FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)

        showImage()

        return _binding.root
    }

    private fun showImage() {
        val args: ImageFragmentArgs by navArgs()
        val imageUrl = args.selectedImage
        _binding.image.load(imageUrl) {
            crossfade(true)
        }
    }
}