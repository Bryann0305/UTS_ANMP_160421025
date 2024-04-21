package com.example.anmphobby.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmphobby.databinding.CardListArticleBinding
import com.example.anmphobby.model.Model
import com.squareup.picasso.Picasso

class HobbyListAdapter (val hobbyList:ArrayList<Model>)
    : RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>(){
    class HobbyViewHolder(var binding: CardListArticleBinding)
        :RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = CardListArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HobbyViewHolder(binding)

    }
    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.binding.txtTitle.text = hobbyList[position].title
        holder.binding.textAuthor.text = hobbyList[position].creator
        holder.binding.textDescriptionShort.text = hobbyList[position].descriptionShort
        Picasso.get().load(hobbyList[position].image).into(holder.binding.imageView)
        holder.binding.buttonRead.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentDetailFragment2(hobbyList[position].title?:"",hobbyList[position].creator?:"",
                hobbyList[position].descriptionLong?:"",hobbyList[position].image?:"")
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun getItemCount(): Int {
        return hobbyList.size
    }


    fun updateHobbyList(newHobbyList: ArrayList<Model>) {
        hobbyList.clear()
        hobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }

}