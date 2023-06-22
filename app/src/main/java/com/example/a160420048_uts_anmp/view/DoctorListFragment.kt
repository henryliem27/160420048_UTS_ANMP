package com.example.a160420048_uts_anmp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.model.Doctor
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel


class DoctorListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()
        val recView: RecyclerView = view.findViewById(R.id.recyclerView)
        val progressLoad:View = view.findViewById(R.id.progressBar)
        val refreshLayout: SwipeRefreshLayout = view.findViewById(R.id.SwipeRefreshLayout)

        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recView.adapter = doctorListAdapter

        refreshLayout.setOnRefreshListener {
            progressLoad.visibility = VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }
    @SuppressLint("SetTextI18n")
    private fun observeViewModel(){
        viewModel.doctorsLD.observe(viewLifecycleOwner, Observer {
            val arrayList = ArrayList<Doctor>(it)
            doctorListAdapter.updateStudentList(arrayList)
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            val recView: RecyclerView? = view?.findViewById(R.id.recyclerView)
            val progressLoad:View? = view?.findViewById(R.id.progressBar)
            if(it.isEmpty()){
                txtError?.visibility = VISIBLE
                recView?.visibility = View.GONE
                progressLoad?.visibility = VISIBLE
            }else{
                txtError?.visibility = View.GONE
                recView?.visibility = VISIBLE
                progressLoad?.visibility = View.GONE
            }

        })
    }
}