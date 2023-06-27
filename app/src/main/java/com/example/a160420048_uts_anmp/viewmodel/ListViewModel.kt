package com.example.a160420048_uts_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.model.DoctorDatabase
import com.example.a160420048_uts_anmp.model.Jadwal
import com.example.a160420048_uts_anmp.model.Obat
import com.example.a160420048_uts_anmp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ListViewModel(application: Application)
    :AndroidViewModel(application),CoroutineScope{

    val userLD = MutableLiveData<User>()
    val jadwalLD = MutableLiveData<Jadwal>()

    val obatsLD = MutableLiveData<List<Obat>>()
    val obatLD = MutableLiveData<Obat>()
    //for detail recView
    val doctorLD = MutableLiveData<Doctor>()
    //for recview 1
    val doctorsLD = MutableLiveData<List<Doctor>>()
    val doctorsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        doctorsLoadErrorLD.value=false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DoctorDatabase::class.java,"anmp_160420048_uts").build()

            doctorsLD.postValue(db.utsDao().selectAllDoctor())
        }
    }
    //to Delete
    fun clearTask(doctor: Doctor) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DoctorDatabase::class.java, "anmp_160420048_uts").build()
            db.utsDao().deleteDoctor(doctor)
            doctorsLD.postValue(db.utsDao().selectAllDoctor() as ArrayList<Doctor>?)
        }
    }

    fun detail(id:Int){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DoctorDatabase::class.java, "anmp_160420048_uts").build()
            db.utsDao().selectDoctor(id)
            doctorLD.postValue(db.utsDao().selectDoctor(id))
        }
    }
    fun jadwal(id: Int){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DoctorDatabase::class.java, "anmp_160420048_uts").build()
            db.utsDao().selectJadwal(id)
            jadwalLD.postValue(db.utsDao().selectJadwal(id))
        }
    }
    fun login(username:String, password:String){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),DoctorDatabase::class.java,"anmp_160420048_uts").build()
            db.utsDao().loginUser(username,password)
        }
    }
    /*
    //to add doctors as admin
    fun addDoctors(list: List<Doctor>){
    launch {
    val db = Room.databaseBuilder(getApplication(),DoctorDatabase::class.java,"anmp_160420048_uts").build()
    doctorLD.postValue(db.utsDao().insertAll() as Doctor)
    }

    }
    fun login(email: String,password: String){
    launch {
    val db = Room.databaseBuilder(getApplication(),DoctorDatabase::class.java,"anmp_160420048_uts").build()
    userLD.postValue(db.utsDao().loginUser(email,password))
    }
    }
    =================================================================================================================
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
    fun login(email:String,password:String){
    loadingLD.value = true
    doctorsLoadErrorLD.value = false

    queue = Volley.newRequestQueue(getApplication())
    //ganti ke localhost db
    val url = "http://192.168.18.35/Native/get_jadwal.php/?username='$email'&password='$password'"
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
    val url = "http://192.168.18.35/Native/detaildoctors.php/?id=$id"

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
    */
}