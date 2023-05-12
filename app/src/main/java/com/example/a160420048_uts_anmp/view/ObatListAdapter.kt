package com.example.a160420048_uts_anmp.view

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
import com.example.a160420048_uts_anmp.model.Obat
import com.example.a160420048_uts_anmp.util.loadImage

class ObatListAdapter(private val obatList:ArrayList<Obat>):
    RecyclerView.Adapter<ObatListAdapter.ObatViewHolder>() {
    class ObatViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.obat_list_item, parent, false)
        return ObatViewHolder(view)
    }

    override fun getItemCount(): Int = obatList.size

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        val imageView = holder.view.findViewById<ImageView>(R.id.imgObat)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarlistobat)
        (obatList[position].nama+"\n"+obatList[position].perbuah).also { holder.view.findViewById<TextView>(R.id.txtNamaObat).text = it }
        holder.view.findViewById<TextView>(R.id.txtHarga).text = obatList[position].harga
        imageView.loadImage(obatList[position].photo_url,progressBar)
        holder.view.findViewById<Button>(R.id.btnDetailobat).setOnClickListener {
            val id = obatList[position].obat_id.toString()
            val action = obatListFragmentDirections.actionObatDetailFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateObatList(newStudentList: ArrayList<Obat>){
        obatList.clear()
        obatList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}