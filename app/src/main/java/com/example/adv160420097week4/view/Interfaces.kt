package com.example.adv160420097week4.view

import android.view.View
import com.example.adv160420097week4.model.Student

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonNotifClickListener{
    fun onButtonNotifClick(v: View, obj:Student)
}