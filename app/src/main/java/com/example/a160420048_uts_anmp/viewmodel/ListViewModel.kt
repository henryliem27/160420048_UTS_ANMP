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
import com.example.a160420048_uts_anmp.model.Jadwal
import com.example.a160420048_uts_anmp.model.Obat
import com.example.a160420048_uts_anmp.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application) :AndroidViewModel(application){
    val userLD = MutableLiveData<User>()
    val jadwalLD = MutableLiveData<Jadwal>()
    val obatsLD = MutableLiveData<ArrayList<Obat>>()
    val obatLD = MutableLiveData<Obat>()
    val doctorLD = MutableLiveData<Doctor>()
    val doctorsLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()


    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun obatdetail(id:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/get_obatdetail.php/?id='$id'"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Obat>(it,Obat::class.java)
                obatLD.value = result as Obat
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
    fun obat(){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/get_obat.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Obat>>() {}.type
                val result = Gson().fromJson<ArrayList<Obat>>(it,sType)
                obatsLD.value = result
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
    fun login(username:String,password:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/get_jadwal.php/?username='$username'&password='$password'"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<User>(it,Jadwal::class.java)
                userLD.value = result as User
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
    fun jadwal(id:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/get_jadwal.php/?jadwal='$id'"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Jadwal>(it,Jadwal::class.java)
                jadwalLD.value = result as Jadwal
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
    fun kategori(profesi:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/kategori.php/?profesi='$profesi'"

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
    fun detail(id:String){
        loadingLD.value = true
        doctorsLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //ganti ke localhost db
        val url = "http://192.168.18.35/Native/detaildoctors.php/?id="+id

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
        val url = "http://192.168.18.35/Native/get_doctors.php"

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


}