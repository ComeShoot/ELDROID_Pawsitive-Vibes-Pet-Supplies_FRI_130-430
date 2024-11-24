package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.TransactionAdapter
import com.project.pawsitivevibes.adapter.TransactionReceiveAdapter
import com.project.pawsitivevibes.viewmodel.TransactionReceiveViewModel

class TransactionReceiveFragment : Fragment() {

    // Using by viewModels() for ViewModel initialization
    private val viewModel: TransactionReceiveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_to_receive, container, false)

        // Find the complete button
        val buttonComplete: TextView = view.findViewById(R.id.buttonCompleted)

        // Set an OnClickListener for the button to replace the fragment
        buttonComplete.setOnClickListener {
            replaceFragment(TransactionFragment()) // Transition to TransactionFragment
        }

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTransactionTwo)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the transactions data from the ViewModel and set the adapter
        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            recyclerView.adapter = TransactionReceiveAdapter(transactions)
        }

        return view
    }

    // Helper function to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_two, fragment) // Replace container with the new fragment
        fragmentTransaction.addToBackStack(null) // Optional for back navigation
        fragmentTransaction.commit()
    }
}
