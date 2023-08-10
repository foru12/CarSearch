package com.example.testpizza.adpters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.example.testpizza.Data.data.NamesMarka
import com.example.testpizza.R

class CustomSpinnerAdapterMarks (activity: Activity) : BaseAdapter(), SpinnerAdapter{


    var carList = listOf<NamesMarka>()
    var carListString = listOf<String>()
    val activity = activity

    fun setCarMarks(cars: List<NamesMarka>) {
        this.carList = cars.toMutableList()
        notifyDataSetChanged()
    }

    fun setCarMarksString(cars : List<String>){
        this.carListString = cars.toMutableList()
        notifyDataSetChanged()
    }



    override fun getCount(): Int {
        return  carList.size
    }

    override fun getItem(position: Int): Any {
        return carList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(activity, R.layout.custom_spinner, null)

        val textView = view.findViewById<View>(R.id.main) as TextView

        textView.setText(carList.get(position).namesMarka)
        return textView
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        view = View.inflate(activity, R.layout.custom_dropdown_spinner, null)
        val textView = view.findViewById<View>(R.id.dropdown) as TextView
        textView.setText(carList.get(position).namesMarka)
        return view
    }




}