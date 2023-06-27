 package com.example.a160420048_uts_anmp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420048_uts_anmp.R
import com.example.a160420048_uts_anmp.databinding.FragmentLoginBinding

import com.example.a160420048_uts_anmp.model.User
import com.example.a160420048_uts_anmp.viewmodel.ListViewModel
import com.google.android.material.internal.ContextUtils


 class LoginFragment : Fragment(), LoginFragmentLayoutInterface {
    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var viewModel:ListViewModel
     companion object{
         const val NAME:String= "randomconst"
         var loginData:ArrayList<User> = ArrayList()
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return dataBinding.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         dataBinding.user
         dataBinding.loginListener = this
         viewModel = ViewModelProvider(this)[ListViewModel::class.java]
         observeViewModel()
     }

     override fun onLoginClick(v: View) {
         val email = dataBinding.txtEmail.text.toString()
         val password = dataBinding.txtPassword.text.toString()
         if(email.isEmpty()){
             dataBinding.textInputLayoutEmail.isErrorEnabled

             return onLoginClick(v.rootView)
         }else if (password.isEmpty()){
             dataBinding.textInputLayout2.isErrorEnabled
             return onLoginClick(v.rootView)
         }
         val intent = Intent(requireContext(), MainActivity::class.java)
         intent.putExtra(NAME, "$email $password")
         startActivity(intent)
         activity?.finish()
     }

     override fun onRegisterClick(v: View) {
         TODO("Not yet implemented")
     }

     fun observeViewModel(){
         viewModel.userLD.observe(viewLifecycleOwner, Observer {
             dataBinding.user=it
         })
     }
 }