package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.databinding.FragmentDoctorDetailBinding
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel
import com.example.a160420048_uts_anmp.util.loadImage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class DoctorDetailFragment : Fragment(),DetailDoctorClick {
    private lateinit var viewModel: ListViewModel
    private lateinit var dataBinding: FragmentDoctorDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentDoctorDetailBinding>(inflater,R.layout.fragment_doctor_detail,container,false)
        return dataBinding.root
    }

    //Attempt to invoke virtual method 'java.lang.String java.lang.Object.toString()' on a null object reference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val id = DoctorDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
            viewModel.detail(id)
            viewModel.jadwal(id)
            dataBinding.bookListener = this
            observeDetailViewModel()
        }
    }


    fun observeDetailViewModel() {
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            dataBinding.doctor = it
        })
        }

    override fun onBookClick(v:View,doctor: Doctor) {
        //belum di upgrade ppt week 12
        Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(
                    doctor.name.toString(),
                    "A new notification is created!!!!!!",
                    R.drawable.baseline_error_24
                )
            }
    }
}
