package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class ObatDetailFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val id = ObatDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
//            viewModel.obatdetail(id)
            observeDetailViewModel()
        }
    }

    @SuppressLint("SetTextI18n")
    fun observeDetailViewModel() {
        val imgUrl = view?.findViewById<ImageView>(R.id.imgDetailObat)
//        val id = view?.findViewById<EditText>(R.id.txtId)
        val name = view?.findViewById<TextView>(R.id.txtNameObatdetail)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBardetailobat)
        val harga = view?.findViewById<TextView>(R.id.txtHargadetail)
        val description = view?.findViewById<TextView>(R.id.txtDescriptionobat)


        viewModel.obatLD.observe(viewLifecycleOwner) {
            imgUrl?.loadImage(it.photo_url, progressBar!!)
            name?.text = it.nama+"\n"+it.perbuah
            harga?.text = it.harga+"\n\n"
            description?.text =
                "Deskripsi\n${it.deskripsi}\n\nIndikasi Umum\n${it.indikasi_umum}\n\nKomposisi\n${it.komposisi}\n\nDosis\n${it.dosis}\n\nAturan pakai\n${it.aturan_pakai}\n\nGolongan Produk\n${it.golongan_produk}\n\nKemasan\n${it.kemasan}\n\nManufaktur\n${it.manufaktur}\n\nNo Regristasi\n${it.no_regristasi}"
        }

    }
}