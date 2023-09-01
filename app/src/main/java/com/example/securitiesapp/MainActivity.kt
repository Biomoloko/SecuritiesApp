package com.example.securitiesapp

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.securitiesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private lateinit var datePickerDialog : DatePickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myError : String = "Ошибка!"

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        //Calculations Button
        bindingClass.CalculationsButton.setOnClickListener {
            val myGreenColor = Color.argb(250, 15,120,0)
            val myRedColor = Color.argb(250, 190,0,0)

            var nominal : Float = ProjectFunctions.GetParseFloat(bindingClass.NominalPrice.text.toString(), myError) // ошибка или число
            bindingClass.NominalPrice.setText(ProjectFunctions.TextInUpField(nominal))


            val couponSize = ProjectFunctions.GetParseFloat(bindingClass.CouponSize.text.toString(),myError)
            bindingClass.CouponSize.setText(ProjectFunctions.TextInUpField(couponSize))

            val paymentsAtYear = ProjectFunctions.GetParseFloat(bindingClass.PaymentsAtYear.text.toString(), myError)
            bindingClass.PaymentsAtYear.setText(ProjectFunctions.TextInUpField(paymentsAtYear))


            val allPayments = ProjectFunctions.GetParseFloat(bindingClass.AllPayments.text.toString(), myError)
            bindingClass.AllPayments.setText(ProjectFunctions.TextInUpField(allPayments))


            val cleanPrice = ProjectFunctions.GetParseFloat(bindingClass.CleanPrice.text.toString(), myError)
            bindingClass.CleanPrice.setText(ProjectFunctions.TextInUpField(cleanPrice))



            val nKD = ProjectFunctions.GetParseFloat(bindingClass.NKD.text.toString(), myError)
            bindingClass.NKD.setText(nKD.toString())

            //установка значений в нижние поля
            bindingClass.DirtyPrice.text = ProjectFunctions.DirtyPriceCalc(cleanPrice, nKD).toString()

            bindingClass.CouponIncome.text = ProjectFunctions.CouponInkomeCalc(paymentsAtYear, couponSize, nominal).toString()

            bindingClass.CurrentIncome.text = ProjectFunctions.CurrentIncomeCalc(paymentsAtYear, couponSize, cleanPrice).toString()

        }

        //Reset Button
        bindingClass.ResetButton.setOnClickListener(){
            bindingClass.NominalPrice.text.clear()
            bindingClass.CouponSize.text.clear()
            bindingClass.PaymentsAtYear.text.clear()
            bindingClass.AllPayments.text.clear()
            bindingClass.CleanPrice.text.clear()
            bindingClass.NKD.text.clear()

            bindingClass.DirtyPrice.text = "________"
            bindingClass.CouponIncome.text = "________"
            bindingClass.CurrentIncome.text = "________"
            bindingClass.SimpleIncome.text = "________"
            bindingClass.TaxIncome.text = "________"
        }
    }
}




