package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.a160420048_uts_anmp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.a160420048_uts_anmp.util.createNotificationChannel


@Suppress("CAST_NEVER_SUCCEEDS")
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId")
    private val fragments:ArrayList<Fragment> =ArrayList()
    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App Notification Channel")

        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNav.setupWithNavController(navController)

        //navigation drawer
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout = findViewById<View>(R.id.drawerLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        var drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout as DrawerLayout?, toolbar,R.string.app_name,
                R.string.app_name)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()



    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    companion object {
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