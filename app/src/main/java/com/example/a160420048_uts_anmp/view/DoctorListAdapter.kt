package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.databinding.DoctorListItemBinding
import com.example.a160420048_uts_anmp.model.Doctor

class DoctorListAdapter(private val doctorList:ArrayList<Doctor>)
    :RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>(),DoctorListClick{
    class DoctorViewHolder(var view:DoctorListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DoctorListItemBinding>(inflater, R.layout.doctor_list_item,parent,false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.view.doctors = doctorList[position]
        holder.view.detailListener = this
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateStudentList(newStudentList: ArrayList<Doctor>){
        doctorList.clear()
        doctorList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onDoctorDetailClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = DoctorListFragmentDirections.actionDoctorListDoctorDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}