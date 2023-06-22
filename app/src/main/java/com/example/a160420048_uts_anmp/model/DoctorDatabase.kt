package com.example.a160420048_uts_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


@Database(entities = [Doctor::class,Jadwal::class,User::class,Obat::class], version = 1)
abstract class DoctorDatabase :RoomDatabase() {

abstract fun utsDao():UTSDao
    companion object{

        @Volatile private var instance: DoctorDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = databaseBuilder(
            context.applicationContext,
            DoctorDatabase::class.java,
            "anmp_160420048_uts"
        ).createFromAsset("anmp_160420048_uts.db").build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }

}