package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.databinding.FragmentLoginBinding
import com.example.a160420048_uts_anmp.util.loadImage
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel

class ProfileFragment : Fragment() {
    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString("randomconst")
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        //Error, padahal sudah di bundle lewat MainActivity; MainActivity dapat username dari Login Fragment
        //java.lang.NullPointerException
        if (username != null) {
            val tmpArray: List<String> = username.split(" ")
            viewModel.login(tmpArray[0], tmpArray[1])
        }else{
            val txtError = view.findViewById<TextView>(R.id.txtNamaprofile)
            txtError.text = "Kosong bro di intent"
        }
        observeDetailViewModel()

    }
    @SuppressLint("CutPasteId")
    fun observeDetailViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

}