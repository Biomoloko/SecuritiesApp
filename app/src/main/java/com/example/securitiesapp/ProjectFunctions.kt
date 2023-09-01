package com.example.securitiesapp

import android.app.DatePickerDialog
import android.graphics.Color
import android.widget.DatePicker
import com.example.securitiesapp.databinding.ActivityMainBinding

object ProjectFunctions {
    //Для установки введенного значения в верхнее поле, если не число - ошибка
    fun TextInUpField(number : Float) : String{
        if(number == 0f){
            return "Ошибка!"
        }
        else(return number.toString())
        }

    //проверка на число, возвращаем число или 0
    fun GetParseFloat(neededString: String, errorTxt : String ) : Float{
        if(neededString.toFloatOrNull() == null){
            return 0f
        } else {
            return neededString.toFloat()
        }
    }

    //блок формул рассчета
    fun DirtyPriceCalc(cleanPrice : Float, nkd : Float) : Float{
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

}
