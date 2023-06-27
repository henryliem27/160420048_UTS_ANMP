package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.model.DoctorDatabase
import com.example.a160420048_uts_anmp.model.UTSDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.a160420048_uts_anmp.util.createNotificationChannel
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bundle: Bundle

    @SuppressLint("MissingInflatedId")
    private val fragments:ArrayList<Fragment> =ArrayList()
    init {
        instance = this
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App Notification Channel")

        val username = intent.getStringExtra(NAME)
        bundle.putString(NAME,username)
        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNav,navController)
        //toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //navigation drawer
        val drawerLayout = findViewById<View>(R.id.drawerLayout1)
        var drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout as DrawerLayout?, toolbar,R.string.app_name,
                R.string.app_name)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView,navController)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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