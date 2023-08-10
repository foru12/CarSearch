package com.example.testpizza.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import com.example.testpizza.R


class DialogSort(private val backData: (Int) -> Unit) : DialogFragment(), View.OnClickListener,
    FragmentResultListener {


    private var REQUEST_CODE: Int = 0
    private lateinit var compareToUp_sw: SwitchCompat
    private lateinit var compareToDown_sw: SwitchCompat
    private lateinit var btnSort: Button


    override fun onDestroy() {
        super.onDestroy()
        backData(REQUEST_CODE)
    }


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.rel_bg);

        val view  = inflater.inflate(R.layout.dialog_custom_sort, container, false)


        compareToUp_sw = view.findViewById(R.id.compareToUp_sw);
        compareToDown_sw = view.findViewById(R.id.compareToDown_sw);
        btnSort = view.findViewById(R.id.btnSort);

        compareToUp_sw.setOnClickListener(this)
        compareToDown_sw.setOnClickListener(this);

        btnSort.setOnClickListener {
            if (compareToUp_sw.isChecked){
                //up
                REQUEST_CODE = 1
                dialog?.dismiss()

            }else{
                REQUEST_CODE = 2
                dialog?.dismiss()

            }
        }



        return view
    }







    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        Log.d("onFragmentResult", "Key: $requestKey, Bundle: $result")

    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.compareToUp_sw ->{
                compareToUp_sw.isChecked = true
                compareToDown_sw.isChecked = false



            } R.id.compareToDown_sw ->{
            compareToUp_sw.isChecked = false
            compareToDown_sw.isChecked = true
            }
        }
    }
}