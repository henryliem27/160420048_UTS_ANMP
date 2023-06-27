package com.example.a160420048_uts_anmp.view

import android.view.View
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.model.User

interface LoginFragmentLayoutInterface{
    fun onLoginClick(v:View)
    fun onRegisterClick(v:View)
}

interface DoctorListClick{
    fun onDoctorDetailClick(v:View)
}

interface DetailDoctorClick{
    fun onBookClick(v:View,obj:Doctor)
}