package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.TransactionAdapter
import com.project.pawsitivevibes.viewmodel.TransactionViewModel

class TransactionFragment : Fragment() {
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_completed, container, false)

        // Find TextView
        val buttonToReceive: TextView = view.findViewById(R.id.buttonToReceive)

        // Set OnClickListener
        buttonToReceive.setOnClickListener {
            // Replace fragment
            replaceFragment(TransactionReceiveFragment())
        }

        return view
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_two, fragment) // ID of the container in MainActivity
        fragmentTransaction.addToBackStack(null) // Optional for back navigation
        fragmentTransaction.commit()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTransactionOne)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            recyclerView.adapter = TransactionAdapter(transactions)
        }
    }
}