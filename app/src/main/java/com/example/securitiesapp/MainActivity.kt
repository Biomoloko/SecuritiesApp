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
            val nominal = bindingClass.NominalPrice.text.toString().toFloat()
            val couponSize = bindingClass.CouponSize.text.toString().toFloat()
            val paymentsAtYear = bindingClass.PaymentsAtYear.text.toString().toFloat()
            val allPayments = bindingClass.AllPayments.text.toString().toFloat()
            val cleanPrice = bindingClass.CleanPrice.text.toString().toFloat()
            val nKD = bindingClass.NKD.text.toString().toFloat()

            bindingClass.DirtyPrice.text = "${DirtyPriseCalc(cleanPrice, nKD)} р"
            bindingClass.CouponIncome.text = "${CouponInkomeCalc(paymentsAtYear, couponSize, nominal)} %"
            bindingClass.CurrentIncome.text = "${CurrentIncomeCalc(paymentsAtYear, couponSize, cleanPrice)} %"
        }
    }
}

fun TryIntValue(num : String) : Int{
    val defaultReturn = 0
    return try{
        num.toInt()
    } catch (e: NumberFormatException) {
        defaultReturn
    }
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