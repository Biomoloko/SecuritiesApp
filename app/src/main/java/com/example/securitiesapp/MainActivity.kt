package com.example.securitiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.securitiesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myError : String = "Ошибка!"

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.CalculationsButton.setOnClickListener {
            var nominal : String = GetNormalString(bindingClass.NominalPrice.text.toString(), myError)
            bindingClass.NominalPrice.setText(nominal)

            val couponSize = GetNormalString(bindingClass.CouponSize.text.toString(),myError)
            bindingClass.CouponSize.setText(couponSize)

            val paymentsAtYear = GetNormalString(bindingClass.PaymentsAtYear.text.toString(), myError)
            bindingClass.PaymentsAtYear.setText(paymentsAtYear)

            val allPayments = GetNormalString(bindingClass.AllPayments.text.toString(), myError)
            bindingClass.AllPayments.setText(allPayments)

            val cleanPrice = GetNormalString(bindingClass.CleanPrice.text.toString(), myError)
            bindingClass.CleanPrice.setText(cleanPrice)

            val nKD = GetNormalString(bindingClass.NKD.text.toString(), myError)
            bindingClass.NKD.setText(nKD)

            //установка значений в нижие поля
            bindingClass.DirtyPrice.text = PasteMeaning((dir))

            bindingClass.CouponIncome.text = PasteMeaning(nominal, CouponInkomeCalc(paymentsAtYear.toFloat(), couponSize.toFloat(), nominal.toFloat()), myError)

            bindingClass.CurrentIncome.text = PasteMeaning(couponSize, CurrentIncomeCalc(paymentsAtYear.toFloat(), couponSize.toFloat(), cleanPrice.toFloat()), myError)

        }
    }
}

fun PasteMeaning (tryToFloatString : String, result : Float, errorTxt : String) : String{
    if (tryToFloatString.toFloatOrNull() != null){
        return result.toString()
    } else {
        return errorTxt
    }
}

fun GetNormalString(neededString: String, errorTxt : String ) : String{
    if(neededString.toFloatOrNull() == null){
        return errorTxt
    } else {
        return neededString
    }
}

fun DirtyPriseCalc(cleanPrice : Float, nkd : Float) : Float{
    return cleanPrice + nkd
}

fun CouponInkomeCalc(paymentsAtYear : Float, couponSize : Float, nominal : Float) : Float{
    if (!nominal.isNaN()){
        return ((paymentsAtYear*couponSize)/nominal) * 100
    } else {
        return 0f
    }
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