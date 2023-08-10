package com.example.testpizza.ui.add


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testpizza.adpters.AdapterCar
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.databinding.FragmentAddBinding
import com.example.testpizza.ui.dialogs.DialogBottom


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null


    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root







        val addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)



        val rcView = binding.rcView
        val adapter = AdapterCar(requireActivity())
        adapter.setOnCarClickListener(object: AdapterCar.OnCarClickListener {
            override fun onCarClick(car: DataCar) {
               Log.e("Click", "-->" + car)
                val dialog = DialogBottom()
                val args = Bundle()
                args.putInt("id",car.id)
                args.putInt("modelPos", 0)
                args.putInt("markaPos", 0)
                args.putInt("years", car.yearsCar)
                args.putLong("edPrice", car.priceCar)
                args.putLong("edMileage", car.mileageCar)
                args.putInt("edDoor", car.doorCar)
                args.putString("edEngine", car.engineCar)
                args.putString("user", car.user)
                args.putString("color",car.colorCar)
                args.putBoolean("statusEdit",true)
                dialog.arguments = args
                dialog.show(requireActivity().supportFragmentManager,dialog.tag)

            }

        })
        rcView.adapter = adapter



        addViewModel.allCarByUser.observe(viewLifecycleOwner) {
           adapter.setCar(it)
        }







        binding.btnAdd.setOnClickListener {
            val dialog = DialogBottom()
            dialog.show(requireActivity().supportFragmentManager,dialog.tag)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}