package com.example.testpizza.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testpizza.MainActivity
import com.example.testpizza.adpters.AdapterCar
import com.example.testpizza.databinding.FragmentSearchBinding
import com.example.testpizza.ui.dialogs.DialogFilter
import com.example.testpizza.ui.dialogs.DialogSort

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null


    private val binding get() = _binding!!


    private lateinit var txtCount:TextView
    private lateinit var btnFilter: ImageButton
    private lateinit var btnSort:ImageButton

    private lateinit var btnDelete:Button




    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        txtCount = binding.txtCountCar
        btnFilter = binding.btnFilter
        btnSort = binding.btnSort

        btnDelete = binding.btnDelete




        val rcView = binding.rcView
        val adapter = AdapterCar(requireActivity())
        rcView.adapter = adapter

        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.allCar.observe( viewLifecycleOwner){
            adapter.setCar(it)


            txtCount.text = it.size.toString() + " предложений"

        }


        btnSort.setOnClickListener{
            DialogSort {
                if (it == 1){
                    Log.e("SORTRED",searchViewModel.sort(true).toString())
                    adapter.setCar(searchViewModel.sort(true))
                }else if(it == 2) {
                    searchViewModel.sort(false)
                    adapter.setCar(searchViewModel.sort(false))
                }
                else{
                    Toast.makeText(context, "Sorting is not selected", Toast.LENGTH_SHORT).show()

                }
            }.show((activity as MainActivity).supportFragmentManager, "MyCustomFragmentSort")
        }


        btnFilter.setOnClickListener{
            Log.e("filter", "-->" +  searchViewModel.filter(1,2020,"BMW", null,60000))
            DialogFilter {
                btnDelete.visibility = View.VISIBLE
               var s = searchViewModel.filter(it.price,it.years,it.filterMarka,it.filterModel,it.mileageCar)
                adapter.setCar(s!!)
                txtCount.text = s.size.toString() + " предложений"

            }.show((activity as MainActivity).supportFragmentManager, "MyCustomFragmentSort")



        }


        btnDelete.setOnClickListener{
            val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
            searchViewModel.allCar.observe( viewLifecycleOwner){
                adapter.setCar(it)
                txtCount.text = it.size.toString() + " предложений"
                btnDelete.visibility = View.GONE

            }

        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}