package com.example.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculatebutton.setOnClickListener(){
            calculatetip()
        }
    }

    fun calculatetip(){
        val cost = (binding.costOfService.text).toString().toDoubleOrNull()

        if(cost==null){
            Toast.makeText(applicationContext,"Please enter a valid value!",Toast.LENGTH_LONG).show()
            return
        }

        val opt = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(opt){
            R.id.option_1 -> 0.2
            R.id.option_2 -> 0.18
            else->0.15
        }

        var tip = cost*tipPercentage
        val roundup = binding.roundUpSwitch.isChecked
        if(roundup){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }
}