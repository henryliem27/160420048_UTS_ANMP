package com.example.a160420048_uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "doctors")
data class Doctor(
    @PrimaryKey(autoGenerate = true)
                  val doctor_id:Int,
    @ColumnInfo("name")
                  val name:String?,
    @ColumnInfo("profesi")
                  val profesi:String?,
    @ColumnInfo("pengalaman_kerja")
                  val years:Int,
    @ColumnInfo("alumnus")
                  val alumnus:String?,
    @ColumnInfo("pratik_di")
                  val pratik_di:String,
    @ColumnInfo("nomor_str")
                  val nomorstr:String,
    @ColumnInfo("rating")
                  val rating:Double,
    @ColumnInfo("photo_url")
                  val photoUrl:String)

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey(autoGenerate = true)
    val jadwal_id :Int, val doctor_id:Int, val lokasi:String?, val waktu:String, val tanggal:String?)

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
                val user_id:Int, val name:String?, val username:String?, val password:String?, val photoUrl:String)

@Entity(tableName = "obat")
data class Obat(
    @PrimaryKey(autoGenerate = true)
                val obat_id: Int?, val nama:String?, val perbuah:String, val harga:String?, val deskripsi:String, val indikasi_umum:String?, val komposisi:String?, val dosis:String?, val aturan_pakai:String?, val golongan_produk:String?, val kemasan:String?, val manufaktur:String?, val no_regristasi:String?, val photo_url:String?)
