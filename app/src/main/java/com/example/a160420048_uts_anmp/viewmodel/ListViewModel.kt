package com.example.a160420048_uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420048_uts_anmp.model.Doctor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application) :AndroidViewModel(application){
    val doctorsLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val doctorLD = MutableLiveData<Doctor>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun detail(id:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.100.22/Native/detaildoctors.php/:"+id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Doctor>(it,Doctor::class.java)
                doctorLD.value = result as Doctor
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                doctorsLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    fun refresh() {
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.100.22/Native/get_doctors.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Doctor>>() {}.type
                val result = Gson().fromJson<ArrayList<Doctor>>(it,sType)
                doctorsLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                doctorsLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    companion object {
        val doctorLD = MutableLiveData<Doctor>()
    }
}