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