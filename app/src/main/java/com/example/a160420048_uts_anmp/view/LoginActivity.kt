package com.example.a160420048_uts_anmp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.model.User

class LoginActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    companion object{
        const val NAME:String= "randomconst"
        var loginData:ArrayList<User> = ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavView) as NavHostFragment
        navController = navHostFragment.navController
    }
}