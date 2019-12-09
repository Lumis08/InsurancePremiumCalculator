package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var premium: PremiumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        premium = ViewModelProviders.of(this).get(PremiumViewModel::class.java)

        setTextViewPremium()

        buttonCalc.setOnClickListener {
            premium.Premium = getPremium()
            setTextViewPremium()
        }

        buttonReset.setOnClickListener {
            reset()
        }
    }

    private fun getPremium(): Double {

        return when(spinnerAge.selectedItemPosition) {
            0 -> 60.00
            1 -> 70.00 +
                 (if(radioButtonMale.isChecked) 50.00 else 0.0) +
                 (if(checkBoxSmoker.isChecked) 100.00 else 0.0)
            2 -> 90.00 +
                 (if(radioButtonMale.isChecked) 100.00 else 0.0) +
                 (if(checkBoxSmoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 +
                 (if(radioButtonMale.isChecked) 150.00 else 0.0) +
                 (if(checkBoxSmoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 +
                 (if(radioButtonMale.isChecked) 200.00 else 0.0) +
                 (if(checkBoxSmoker.isChecked) 250.00 else 0.0)
            else -> 150.00 +
                    (if(radioButtonMale.isChecked) 200.00 else 0.0) +
                    (if(checkBoxSmoker.isChecked) 300.00 else 0.0)
        }

    }

    private fun setTextViewPremium() {
        textViewResultPremium.text = "RM: %.2f".format(premium.Premium)
    }

    private fun reset(){
        spinnerAge.setSelection(0)
        radGroupGender.clearCheck()
        checkBoxSmoker.setChecked(false)
        textViewResultPremium.text = ""
    }
}
