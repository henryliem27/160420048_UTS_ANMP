package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.util.loadImage
class DoctorListAdapter(private val doctorList:ArrayList<Doctor>)
    :RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>(){
    class DoctorViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val imageView = holder.view.findViewById<ImageView>(R.id.imgDoctor)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarDoctorList)
        holder.view.findViewById<TextView>(R.id.txtID).text = doctorList[position].profesi
        holder.view.findViewById<TextView>(R.id.txtName).text = doctorList[position].name
        holder.view.findViewById<Button>(R.id.btnlamakerja).text = doctorList[position].years+" tahun"
        holder.view.findViewById<Button>(R.id.btnrate).text = doctorList[position].rating+" %"
        imageView.loadImage(doctorList[position].photoUrl,progressBar)
//        Picasso.get().load(doctorList[position].photoUrl).into(holder.findViewById<ImageView>(R.id.imgDoctor))

        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            val id = doctorList[position].id.toString()
            val action = DoctorListFragmentDirections.actionItemTransaction(id)
            Navigation.findNavController(it).navigate(action)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateStudentList(newStudentList: ArrayList<Doctor>){
        doctorList.clear()
        doctorList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}