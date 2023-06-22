package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel
import com.example.a160420048_uts_anmp.util.loadImage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
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

        if (arguments != null) {
            val id = DoctorDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
//            viewModel.detail(id)
//            viewModel.jadwal(id)
            observeDetailViewModel()
        }
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    fun observeDetailViewModel() {
        val imgUrl = view?.findViewById<ImageView>(R.id.imgDoctor1)
//        val id = view?.findViewById<EditText>(R.id.txtId)
        val name = view?.findViewById<TextView>(R.id.txtNama)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBardetaildoctor)
        val profesi = view?.findViewById<TextView>(R.id.txtProfesi)
        val alumnus = view?.findViewById<TextView>(R.id.txtAlumnus)
        val pratikdi = view?.findViewById<TextView>(R.id.txtPraktikDi)
        val nomorstr = view?.findViewById<TextView>(R.id.txtNomorSTR)
        val lamakerja = view?.findViewById<Button>(R.id.btnlamakerja2)
        val rate = view?.findViewById<Button>(R.id.btnrate2)
        val jadwal = view?.findViewById<TextView>(R.id.txtJadwal1)

        viewModel.doctorLD.observe(viewLifecycleOwner) {
            imgUrl?.loadImage(it.photoUrl, progressBar!!)
            name?.text = it.name
            profesi?.text = it.profesi
            alumnus?.text = "Alumnus: \n" + it.alumnus
            pratikdi?.text = "Pratik di: \n" + it.pratik_di
            nomorstr?.text = "Nomor STR: \n" + it.nomorstr
            lamakerja?.text = it.years.toString() + " tahun"
            rate?.text = it.rating.toString() + " %"
            val doctor = it
            val btnBook = view?.findViewById<Button>(R.id.btnBook)
            btnBook?.setOnClickListener {
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
        viewModel.jadwalLD.observe(viewLifecycleOwner) {
            jadwal?.text =
                "Next Free Appointment\n" + "Lokasi: " + it.lokasi + "\nTanggal: " + it.tanggal + " Jam: " + it.waktu
        }

    }
}