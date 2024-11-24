package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.NotificationItem

class NotificationViewModel : ViewModel() {

    private val _notifications = MutableLiveData<List<NotificationItem>>()
    val notifications: LiveData<List<NotificationItem>> = _notifications

    init {
        loadDummyNotifications()
    }

    private fun loadDummyNotifications() {
        _notifications.value = listOf(
            NotificationItem(
                imageResId = R.drawable.eye_drops, // Replace with your drawable resource
                title = "Eye Drops",
                message = "Parcel has departed from sorting facility",
                time = "6:45 PM"
            ),
            NotificationItem(
                imageResId = R.drawable.magic_milk, // Replace with your drawable resource
                title = "Magic Milk",
                message = "Order confirmed, preparing for shipment",
                time = "3:15 PM"
            )
        )
    }
}
