package com.project.pawsitivevibes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.ItemSellerAdapter
import com.project.pawsitivevibes.model.Item
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.repository.ItemRepository
import com.project.pawsitivevibes.viewmodel.ItemViewModelFactory
import com.project.pawsitivevibes.viewmodel.SellerProductsViewModel

class HomeSellerFragment : Fragment() {

    private lateinit var viewModel: SellerProductsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ItemRepository(ApiService.create())
        val factory = ItemViewModelFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this, factory)[SellerProductsViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerView)
        fab = view.findViewById(R.id.fabbtn)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fab.setOnClickListener {
            val intent = Intent(requireActivity(), AddProductActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val authToken = sharedPreferences.getString("auth_token", null)
        val sellerId = sharedPreferences.getString("seller_id", null)

        if (authToken != null && sellerId != null) {
            viewModel.loadProducts(authToken, sellerId)
        } else {
            Toast.makeText(requireContext(), "Authentication failed!", Toast.LENGTH_SHORT).show()
        }

        viewModel.products.observe(viewLifecycleOwner) { products ->
            val adapter = ItemSellerAdapter(products.map {
                Item(
                    id = it.seller_id,
                    name = it.prod_name,
                    description = it.prod_description,
                    imageUrl = it.prod_image,
                    price = it.prod_price,
                    quantity = it.prod_quantity
                )
            }) { selectedItem ->
                val intent = Intent(requireActivity(), EditProductActivity::class.java).apply {
                    putExtra("product_id", selectedItem.id)
                    putExtra("product_name", selectedItem.name)
                    putExtra("product_description", selectedItem.description)
                    putExtra("product_price", selectedItem.price)
                    putExtra("product_quantity", selectedItem.quantity)
                    putExtra("product_image", selectedItem.imageUrl)
                }
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

