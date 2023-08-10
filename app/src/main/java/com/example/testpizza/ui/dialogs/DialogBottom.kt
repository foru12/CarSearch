package com.example.testpizza.ui.dialogs

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.testpizza.adpters.CustomSpinnerAdapterMarks
import com.example.testpizza.adpters.CustomSpinnerAdapterModel
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.R
import com.example.testpizza.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton


open class DialogBottom : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBinding? = null


    private val binding get() = _binding!!

    private lateinit var btnReady: Button
    private lateinit var btnCarBlack: MaterialButton
    private lateinit var btnCarBrown: MaterialButton
    private lateinit var btnCarGray: MaterialButton
    private lateinit var btnCarWhite: MaterialButton
    private lateinit var btnCarYellow: MaterialButton


    private lateinit var edDoor: EditText
    private lateinit var edEngine: EditText
    private lateinit var edPrice: EditText
    private lateinit var edMileage: EditText

    private lateinit var spinerName: Spinner
    private lateinit var spinerModel: Spinner
    private lateinit var spinerYears: Spinner

    private lateinit var addImage: LottieAnimationView

    private var years: String = ""
    private lateinit var color: String
    private var posMarka: String = ""
    private var posMarkaInt: Int = 0
    private var posModel: String = ""
    private var id : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);

    }

    fun setDefault() {
        edPrice.setText(arguments?.getLong("edPrice").toString());
        edDoor.setText(getArguments()?.getInt("edDoor").toString())
        edMileage.setText(getArguments()?.getLong("edMileage").toString())
        edEngine.setText(getArguments()?.getString("edEngine").toString());

        id = arguments?.getInt("id",0)!!
        when (getArguments()?.getString("color").toString()) {
            "Черный" -> {
                btnCarBlack.text = getString(R.string.check)
            }

            "Коричневый" -> {
                btnCarBrown.text = getString(R.string.check)
            }

            "Белый" -> {
                btnCarWhite.text = getString(R.string.check)
            }

            "Серый" -> {
                btnCarGray.text = getString(R.string.check)
            }

            "Желтый" -> {
                btnCarYellow.text = getString(R.string.check)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btnReady = binding.btnReady

        btnCarBlack = binding.btnCarBlack
        btnCarBrown = binding.btnCarBrown
        btnCarGray = binding.btnCarGray
        btnCarWhite = binding.btnCarWhite
        btnCarYellow = binding.btnCarYellow

        edDoor = binding.edDoor
        edEngine = binding.edEngine
        edPrice = binding.edPrice
        edMileage = binding.edMileage

        addImage = binding.imgAnim

        spinerName = binding.spinnerName
        spinerModel = binding.spinnerModel
        spinerYears = binding.spinnerYears


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setTextWatcher()
        setClick()
        if (arguments?.getBoolean("statusEdit") == true) {
            setDefault()
        }


        val dialogViewModel = ViewModelProvider(this).get(DialogViewModel::class.java)

        val bottomDialog = BottomSheetBehavior.from(view.parent as View)
        bottomDialog.state = BottomSheetBehavior.STATE_EXPANDED
        val layout: CoordinatorLayout = dialog!!.findViewById(R.id.bottom_sheet)
        layout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.years_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinerYears.adapter = adapter
        }


        val customSpinnerAdapterMarks = CustomSpinnerAdapterMarks(requireActivity())
        val customSpinnerAdapterModel = CustomSpinnerAdapterModel(requireActivity())

        dialogViewModel.allCarMarka.observe(viewLifecycleOwner) {
            customSpinnerAdapterMarks.setCarMarks(it)


        }
        spinerName.adapter = customSpinnerAdapterMarks
        spinerModel.adapter = customSpinnerAdapterModel



        spinerName.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view == spinerName.selectedView) {
                    customSpinnerAdapterModel.setCarModel(
                        dialogViewModel.getDataModel(
                            position
                        )
                    )
                    dialogViewModel.allCarMarka.observe(viewLifecycleOwner) {
                        Log.e("MARKA", "--> " + it.get(position).namesMarka.toString())
                        posMarka = it.get(position).namesMarka.toString()
                        posMarkaInt = position


                    }


                    spinerModel.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            posModel = dialogViewModel.getDataModel(posMarkaInt)
                                .get(position).namesModel.toString()
                            Log.e("Model", "-->" + posModel)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    })


                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })
        spinerYears.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view == spinerYears.selectedView) {
                    Log.e("OIWEJWOEITGWE", spinerYears.selectedItem.toString())
                    years = spinerYears.selectedItem.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })


        btnReady.setOnClickListener {
            var idd = 0
            var edPriceInt = edPrice.text.toString().toLong()
            var edMileageInt = edMileage.text.toString().toLong()
            var edDoorInt = edDoor.text.toString().toInt()
            if (arguments?.getBoolean("statusEdit") == true) {
               idd = id
            } else {
                idd = 0
            }
            dialogViewModel.insert(
                DataCar(
                    idd,
                    "",
                    posModel,
                    posMarka,
                    years.toInt(),
                    edPriceInt,
                    edMileageInt,
                    edDoorInt,
                    edEngine.text.toString(),
                    color,
                    "Москва",
                    "me"
                )
            )
            dialog!!.dismiss()


        }


    }


    private fun setTextWatcher() {
        val edList = arrayOf<EditText>(edDoor, edEngine, edPrice, edMileage)
        val textWatcher = TextWatcher(edList, btnReady)
        for (editText in edList) editText.addTextChangedListener(textWatcher)

    }

    private fun setClick() {
        btnCarBlack.setOnClickListener {
            Log.e("Ckick", "!!!")
            color = "Черный"
            btnCarBlack.text = getString(R.string.check)
            btnCarBrown.text = ""
            btnCarGray.text = ""
            btnCarWhite.text = ""
            btnCarYellow.text = ""

        }
        btnCarBrown.setOnClickListener {
            btnCarBrown.text = getString(R.string.check)
            color = "Коричневый"
            btnCarBlack.text = ""
            btnCarGray.text = ""
            btnCarWhite.text = ""
            btnCarYellow.text = ""
        }
        btnCarGray.setOnClickListener {
            btnCarGray.text = getString(R.string.check)
            color = "Серый"
            btnCarBrown.text = ""
            btnCarBlack.text = ""
            btnCarWhite.text = ""
            btnCarYellow.text = ""
        }
        btnCarYellow.setOnClickListener {
            btnCarYellow.text = getString(R.string.check)
            color = "Желтый"
            btnCarBrown.text = ""
            btnCarGray.text = ""
            btnCarWhite.text = ""
            btnCarBlack.text = ""
        }
        btnCarWhite.setOnClickListener {
            btnCarWhite.text = getString(R.string.check)
            color = "Белый"
            btnCarBrown.text = ""
            btnCarGray.text = ""
            btnCarBlack.text = ""
            btnCarYellow.text = ""
        }


    }


}