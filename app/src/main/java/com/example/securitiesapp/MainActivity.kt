package com.example.securitiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.securitiesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.CalculationsButton.setOnClickListener {
            val nominal = ConvertToFloat(bindingClass.NominalPrice.text.toString())
            bindingClass.NominalPrice.setText(nominal.toString())
            val couponSize = ConvertToFloat(bindingClass.CouponSize.text.toString())
            bindingClass.CouponSize.setText(couponSize.toString())
            val paymentsAtYear = ConvertToFloat(bindingClass.PaymentsAtYear.text.toString())
            val allPayments = ConvertToFloat(bindingClass.AllPayments.text.toString())
            val cleanPrice = ConvertToFloat(bindingClass.CleanPrice.text.toString())
            val nKD = ConvertToFloat(bindingClass.NKD.text.toString())

            bindingClass.DirtyPrice.text = "${DirtyPriseCalc(cleanPrice, nKD)} р"
            bindingClass.CouponIncome.text = "${CouponInkomeCalc(paymentsAtYear, couponSize, nominal)} %"
            bindingClass.CurrentIncome.text = "${CurrentIncomeCalc(paymentsAtYear, couponSize, cleanPrice)} %"
        }
    }
}
fun ConvertToFloat(stringDigit : String) : Float{
    if(stringDigit.toFloatOrNull() == null){
        return 0f
    }
    else return stringDigit.toString().toFloat()
}

fun DirtyPriseCalc(cleanPrice : Float, nkd : Float) : Float{
    return cleanPrice + nkd
}

fun CouponInkomeCalc(paymentsAtYear : Float, couponSize : Float, nominal : Float) : Float{
    return ((paymentsAtYear*couponSize)/nominal) * 100
}

fun CurrentIncomeCalc(paymentsAtYear : Float, couponSize : Float, cleanPrice: Float) : Float{
    return ((paymentsAtYear*couponSize)/cleanPrice) * 100
}

fun SimpleIncomeCalc(dirtyPrice : Float, daysUntil : Float, couponSize : Float ,nominal : Float, allPayments : Float) : Float{
    return ((nominal - dirtyPrice + (couponSize * allPayments))/dirtyPrice) * (365/daysUntil) * 100
}

fun TaxIncomeCalc(dirtyPrice : Float, daysUntil : Float, couponSize : Float, nominal : Float, allPayments : Float) : Float {
    return (((nominal - dirtyPrice + allPayments * couponSize) * 0.87f) / dirtyPrice) * (365 / daysUntil) * 100
}