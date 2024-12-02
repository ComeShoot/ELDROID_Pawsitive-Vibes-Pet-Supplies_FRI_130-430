package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.ItemSellerAdapter
import com.project.pawsitivevibes.viewmodel.ItemViewModel

class HomeSellerFragment : Fragment() {
    private val viewModel: ItemViewModel by viewModels()

    private lateinit var fab: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        fab = view.findViewById(R.id.fabbtn)

        fab.setOnClickListener {
            // Create an intent to open AddProductActivity
            val intent = Intent(requireActivity(), AddProductActivity::class.java)
            startActivity(intent)
        }

        // Observe the ViewModel data
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            val adapter = ItemSellerAdapter(items) { item ->
                // Handle the item click here (e.g., redirect to CartActivity)
                val intent = Intent(requireActivity(), CartActivity::class.java)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        })
    }
}