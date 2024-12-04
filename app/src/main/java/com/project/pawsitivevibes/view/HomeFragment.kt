package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.ItemAdapter
import com.project.pawsitivevibes.viewmodel.AllProductViewModel

class HomeFragment : Fragment() {
    private val viewModel: AllProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ItemAdapter(mutableListOf()) { product ->
            val intent = Intent(requireContext(), CartActivity::class.java)
            intent.putExtra("productId", product.prodId)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                Log.d("HomeFragment", "Products received: $products")
                adapter.updateData(products) // Add an `updateData` function to your adapter
            }
        }
    }

}
