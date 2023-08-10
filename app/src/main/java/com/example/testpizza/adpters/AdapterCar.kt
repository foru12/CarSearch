package com.example.testpizza.adpters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.R
import com.example.testpizza.databinding.CarItemBinding

class AdapterCar(activity: Activity) : RecyclerView.Adapter<MyViewHolder>() {

    var carList = mutableListOf<DataCar>()
    val activity = activity

    var clickListener: ListClickListener<DataCar>? = null
    private var onCarClickListener: OnCarClickListener? = null

    fun setCar(cars: List<DataCar>) {
        this.carList = cars.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnCarClickListener(onCarClickListener: OnCarClickListener) {
        this.onCarClickListener = onCarClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val car = carList[position]
        holder.binding.run {

            txtCarCity.text = car.cityCar
            txtCarColor.text = car.colorCar
            txtCarDoor.text = car.doorCar.toString()
            txtCarEngine.text = car.engineCar
            txtCarMileage.text = car.mileageCar.toString()
            txtCarName.text = car.modelCar  +" "+ car.markaCar +" " + car.yearsCar
            txtCarPrice.text = car.priceCar.toString() + "Р"

            Glide
                .with(activity)
                .load(car.imageUrl)
                .centerCrop()
                .into(imageCar);

          /*  layout.setOnClickListener {
                clickListener?.onClick(user, position)
            }

            imgDelete.setOnClickListener {
                clickListener?.onDelete(user)
            }*/
        }
        holder.itemView.setOnClickListener {
            onCarClickListener?.onCarClick(car)
        }
    }

    fun setOnItemClick(listClickListener: ListClickListener<DataCar>) {
        this.clickListener = listClickListener
    }

    interface OnCarClickListener {
        fun onCarClick(car: DataCar)
    }

}
class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = CarItemBinding.bind(view)
}


interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(car: T)
}