package com.example.a160420048_uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel

class obatListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val doctorListAdapter = ObatListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recView: RecyclerView = view.findViewById(R.id.recViewObat)
        val txtError: TextView = view.findViewById(R.id.txtErrorObat)
        val progressLoad:View = view.findViewById(R.id.progressBarObat)
        val refreshLayout: SwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.SwipeRefreshLayout)

        viewModel= ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.obat()
        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recView.adapter = doctorListAdapter

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.obat()
            refreshLayout.isRefreshing = false
        }
        observeViewModel(recView,txtError,progressLoad)
    }
    fun observeViewModel(recView:RecyclerView,txtError:TextView,progressLoad:View){
        viewModel.obatsLD.observe(viewLifecycleOwner, Observer {
            doctorListAdapter.updateObatList(it)
        })
        viewModel.doctorsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
    }
}