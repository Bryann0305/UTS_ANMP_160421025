package com.example.anmphobby.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.example.anmphobby.R
import com.example.anmphobby.databinding.FragmentDetailBinding

class DetailFragment : Fragment()  {
    private lateinit var  binding:FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title =
            DetailFragmentArgs.fromBundle(requireArguments()).title
        val author =
            DetailFragmentArgs.fromBundle(requireArguments()).author
        val descriptionLong =
            DetailFragmentArgs.fromBundle(requireArguments()).descriptionLong
        val image =
            DetailFragmentArgs.fromBundle(requireArguments()).image
        binding.textTitle.text = title
        binding.textAuthor.text = "@"+author
        binding.textDescriptionLong.text = descriptionLong
        Picasso.get().load(image).into(binding.imageView2)
    }


}