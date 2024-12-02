package com.project.pawsitivevibes.viewmodel

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.Item

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        _items.value = listOf(

        )
    }
}
