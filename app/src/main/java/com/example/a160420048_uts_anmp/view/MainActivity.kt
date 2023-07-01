package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.util.createNotificationChannel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("MissingInflatedId")
    private val fragments:ArrayList<Fragment> =ArrayList()
    init {
        instance = this
    }
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.drawer_layout)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App Notification Channel")

        val username = intent.getStringExtra(NAME)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHostFragment.navController


        //toolbar button back sudah dicoding tapi tidak muncul
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration)


        //navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        val appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView,navController)

        //bottomNav
        setSupportActionBar(toolbar)
        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNav)
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
        NavigationUI.setupWithNavController(bottomNav,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerLayout)
                || super.onSupportNavigateUp()
    }

    companion object {
        const val NAME:String= "randomconst"
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int){

            val channelId = "${instance?.packageName}-" +
                    "${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(
                instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            manager.notify(1001, builder.build())

        }
    }
}