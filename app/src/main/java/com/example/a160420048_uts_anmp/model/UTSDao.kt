package com.example.a160420048_uts_anmp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a160420048_uts_anmp.model.Jadwal as Jadwal

@Dao
interface UTSDao {
    //Doctor Database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg doctor: Doctor)

    @Query("SELECT * FROM doctors")
    fun selectAllDoctor(): List<Doctor>

    @Query("SELECT * FROM doctors WHERE doctors.doctor_id = :id")
    fun selectDoctor(id:Int): Doctor

    @Delete
    fun deleteDoctor(todo:Doctor)

    //User Database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM user WHERE user.username = :username AND user.password= :password")
    fun loginUser(username:String,password:String):User

    //JadwalDB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJadwal(vararg jadwal: Jadwal)

    @Query("SELECT * FROM jadwal WHERE jadwal.jadwal_id = :id")
    fun selectJadwal(id:Int): Jadwal

    //ObatDb
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertObat(vararg obat: Obat)

}