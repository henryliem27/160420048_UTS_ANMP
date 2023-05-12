package com.example.a160420048_uts_anmp.model

import com.google.gson.annotations.SerializedName

data class Doctor(@SerializedName("doctor_id")
                  val id:String?,
@SerializedName("name")
                  val name:String?,
@SerializedName("profesi")
                  val profesi:String?,
@SerializedName("pengalaman_kerja")
                  val years:String,
@SerializedName("alumnus")
                  val alumnus:String?,
@SerializedName("pratik_di")
                  val pratik_di:String,
@SerializedName("nomor_str")
                  val nomorstr:String,
@SerializedName("rating")
                  val rating:String,
@SerializedName("photo_url")
                  val photoUrl:String)

data class Jadwal(val id :String?, val doctor_id:String?,val lokasi:String?, val waktu:String, val tanggal:String?)

data class User(val id:String?, val name:String?, val username:String?, val password:String?, val photoUrl:String)

data class Obat(val obat_id: String?, val nama:String?, val perbuah:String, val harga:String?, val deskripsi:String, val indikasi_umum:String?, val komposisi:String?, val dosis:String?, val aturan_pakai:String?, val golongan_produk:String?, val kemasan:String?, val manufaktur:String?, val no_regristasi:String?, val photo_url:String?)