package com.example.a160420048_uts_anmp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UTSDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg doctor: Doctor)

    @Query("SELECT * FROM doctors")
    fun selectAllDoctor(): List<Doctor>

    @Query("SELECT * FROM doctors WHERE doctors.doctor_id = :id")
    fun selectDoctor(id:Int): Doctor

    @Query("SELECT * FROM user WHERE user.username = :username AND user.password= :password")
    fun loginUser(username:String,password:String):User //error karena tidak ada tabel user dalam database

    @Delete
    fun deleteDoctor(todo:Doctor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJadwal(vararg jadwal: Jadwal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertObat(vararg obat: Obat)

}