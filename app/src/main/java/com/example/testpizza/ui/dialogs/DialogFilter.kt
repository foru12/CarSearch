package com.example.testpizza.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.testpizza.Data.data.DataSort
import com.example.testpizza.R
import com.example.testpizza.adpters.AdapterCarString
import com.google.android.material.button.MaterialButton

class DialogFilter (private val backData: (DataSort) -> Unit) : DialogFragment(), View.OnClickListener,
    FragmentResultListener {


    private var REQUEST_CODE_LIST: DataSort? = null
    private lateinit var spinnerMarka: Spinner
    private lateinit var spinnerModel: Spinner
    private lateinit var spinnerYears: Spinner
    private lateinit var btnShow: MaterialButton
    private lateinit var edPrice: EditText
    private lateinit var edMileage: EditText


    private lateinit var listMarka : ArrayList<String>
    private lateinit var listModel : ArrayList<String>

    private var years: String = ""
    private var nameMarka: String? = null
    private var posMarkaInt: Int = 0
    private var nameModel: String? = null
    private var id : Int = 0
    private val defaultList: List<String> = listOf("")


    override fun onDestroy() {
        super.onDestroy()
        backData(REQUEST_CODE_LIST!!)
    }


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.rel_bg);

        val view  = inflater.inflate(R.layout.dialog_custom_filter, container, false)
        spinnerMarka = view.findViewById(R.id.spinner_marka);
        spinnerModel = view.findViewById(R.id.spinner_model);
        spinnerYears = view.findViewById(R.id.spinner_years);

        edPrice = view.findViewById(R.id.ed_price);
        edMileage = view.findViewById(R.id.ed_mileage);

        btnShow = view.findViewById(R.id.btn_show);




        val dialogViewModel = ViewModelProvider(this).get(DialogViewModel::class.java)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.years_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerYears.adapter = adapter
        }


        val customSpinnerAdapterMarks = AdapterCarString(requireActivity())
        val customSpinnerAdapterModel = AdapterCarString(requireActivity())

        dialogViewModel.allCarMarka.observe(viewLifecycleOwner) {
            listMarka = ArrayList()
            listMarka.add(0,"Выбрать марку")
            for (i in it){
                listMarka.add(i.namesMarka.toString())
            }
            Log.e("LIST",listMarka.toString())
            customSpinnerAdapterMarks.setCarString(listMarka)


        }
        spinnerMarka.adapter = customSpinnerAdapterMarks
        spinnerModel.adapter = customSpinnerAdapterModel




        spinnerMarka.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view == spinnerMarka.selectedView) {
                    listModel = ArrayList()
                    listModel.add(0,"Выбрать модель")
                    if (position != 0){
                        for (i in  dialogViewModel.getDataModel(
                            position -1
                        )){
                            listModel.add(i.namesModel.toString())
                        }
                        customSpinnerAdapterModel.setCarString(listModel)
                    }
                    else{
                        customSpinnerAdapterModel.setCarString(defaultList)
                    }
                    if (position == 0){
                        spinnerModel.isEnabled = false

                    }else{
                        spinnerModel.isEnabled = true

                        spinnerModel.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                if (position != 0){
                                    nameModel = dialogViewModel.getDataModel(posMarkaInt)
                                        .get(position -1).namesModel.toString()
                                    Log.e("MARKA", "--> " +nameModel)
                                }



                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }

                        })

                        dialogViewModel.allCarMarka.observe(viewLifecycleOwner) {
                            Log.e("MARKA", "--> " + it.get(position -1).namesMarka.toString())
                            nameMarka = it.get(position -1).namesMarka.toString()
                            posMarkaInt = position -1


                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })


        spinnerYears.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view == spinnerYears.selectedView) {
                    years = spinnerYears.selectedItem.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })
        btnShow.setOnClickListener {

            var edPriceInt: String? = ""
            var edMileageInt: String? = ""

            if(edPrice.text.toString() != ""){
                edPriceInt = edPrice.text.toString()
            }else{
                edPriceInt = null
            }



            if(edMileage.text.toString() != ""){
                edMileageInt = edMileage.text.toString()
            }
            else{
                edMileageInt = null
            }

            if(nameMarka == ""){
                nameMarka = null.toString()
            }




            var a = nameModel
            var b =  nameMarka
            var c = years



            Log.e("DataFilter", "Price" + "-->" + edPriceInt)
            Log.e("DataFilter", "Mileage"+"-->" + edMileageInt)
            Log.e("DataFilter", "-->" + a)
            Log.e("DataFilter", "-->" + b)
            Log.e("DataFilter", "-->" + c)




            if(years == ""){
                REQUEST_CODE_LIST = DataSort(
                    nameMarka,
                    nameModel ,
                    null,
                    edPriceInt?.toLong(),
                    edMileageInt?.toLong()

                )
            }else{
                REQUEST_CODE_LIST = DataSort(
                    nameMarka,
                    nameModel ,
                    years.toInt(),
                    edPriceInt?.toLong(),
                    edMileageInt?.toLong()

                )
            }











            dialog!!.dismiss()

        }



        return view
    }







    override fun onStart() {
        super.onStart()

        dialog!!.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        Log.d("onFragmentResult", "Key: $requestKey, Bundle: $result")

    }

    override fun onClick(v: View?) {


    }




}