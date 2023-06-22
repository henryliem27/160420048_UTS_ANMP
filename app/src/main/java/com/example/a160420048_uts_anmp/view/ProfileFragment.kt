package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.util.loadImage
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!= null){
            val id =  DoctorDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
//            viewModel.login(id, password = "1134215")
            observeDetailViewModel()
        }
    }
    @SuppressLint("CutPasteId")
    fun observeDetailViewModel()
    {
        val imgUrl = view?.findViewById<ImageView>(R.id.txtsince)
        val txtNama = view?.findViewById<TextView>(R.id.txtNamaprofile)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar3)
        val txtSince = view?.findViewById<TextView>(R.id.txtsince)
        val txtusername = view?.findViewById<TextView>(R.id.txtusername)
        val btnUpdate = view?.findViewById<Button>(R.id.btnUpdate)

        viewModel.doctorLD.observe(viewLifecycleOwner) {
            imgUrl?.loadImage(it.photoUrl, progressBar!!)
            txtNama?.text = it.name
            txtSince?.text = it.profesi
            txtusername?.text = "Alumnus: \n"+it.alumnus
            btnUpdate?.text = "Pratik di: \n"+it.pratik_di

        }
    }
}