package com.example.testpizza.adpters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.annotation.NonNull
import com.example.testpizza.Data.data.NamesModel
import com.example.testpizza.R

class CustomSpinnerAdapterModel (activity: Activity) : BaseAdapter(), SpinnerAdapter {


    var carList = mutableListOf<NamesModel>()
    val activity = activity

    fun setCarModel(cars: List<NamesModel>) {
        this.carList = cars.toMutableList()
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
        if (position == 0){
            textView.setText("Марка машины")
        }
        textView.setText(carList.get(position).namesModel)
        return textView
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        view = View.inflate(activity, R.layout.custom_dropdown_spinner, null)
        val textView = view.findViewById<View>(R.id.dropdown) as TextView
        textView.setText(carList.get(position).namesModel)
        return view
    }
}