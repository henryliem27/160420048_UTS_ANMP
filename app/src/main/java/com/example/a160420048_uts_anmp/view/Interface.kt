package com.example.a160420048_uts_anmp.view

import android.view.View
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.model.User

interface LoginFragmentLayoutInterface{
    fun onLoginClick(v:View,obj:User)
    fun onRegisterClick(v:View, obj:User)
}

interface DoctorListClick{
    fun onDoctorListClick(v:View)
}

interface DetailDoctorClick{
    fun onBookClick(v:View,obj:Doctor)
}