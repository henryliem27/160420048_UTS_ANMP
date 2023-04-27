package com.example.a160420048_uts_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel
import com.example.advweek4.util.loadImage
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!= null){
            val id =  DoctorDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
            viewModel.detail(id)
            observeDetailViewModel()
        }
    }

    fun observeDetailViewModel()
    {
        val imgUrl = view?.findViewById<ImageView>(R.id.imgDoctor1)
//        val id = view?.findViewById<EditText>(R.id.txtId)
        val name = view?.findViewById<EditText>(R.id.txtName)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBardetaildoctor)

        ListViewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            imgUrl?.loadImage(it.photoUrl, progressBar!!)
            name?.setText(it.name)
        })
    }
}