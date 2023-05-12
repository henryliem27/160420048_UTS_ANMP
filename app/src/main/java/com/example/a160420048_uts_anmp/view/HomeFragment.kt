package com.example.a160420048_uts_anmp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDokterUmum = view.findViewById<Button>(R.id.btnDokterUmum)
        val btnDokterTHT = view.findViewById<Button>(R.id.btnHT)
        val btnDokterAnak = view.findViewById<Button>(R.id.btnanak)
        val btnObat = view.findViewById<Button>(R.id.btnObat)
        btnDokterUmum.setOnClickListener {
            val kategori:String = "Dokter Umum"
            val action = HomeFragmentDirections.actionDokterUmum(kategori)
            Navigation.findNavController(it).navigate(action)
        }

        btnDokterTHT.setOnClickListener {
            val kategori:String = "sp.THT"
            val action = HomeFragmentDirections.actionDokterUmum(kategori)
            Navigation.findNavController(it).navigate(action)
        }

        btnDokterAnak.setOnClickListener {
            val kategori:String = "Sp.Anak"
            val action = HomeFragmentDirections.actionDokterUmum(kategori)
            Navigation.findNavController(it).navigate(action)
        }
        btnObat.setOnClickListener {
            val action = HomeFragmentDirections.actionObatListFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}