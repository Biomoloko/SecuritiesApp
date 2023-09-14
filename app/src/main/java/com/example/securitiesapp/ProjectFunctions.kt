package com.example.securitiesapp

import kotlin.math.roundToInt


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
        return IsNanReturning(cleanPrice + nkd)
    }

    fun CouponInkomeCalc(paymentsAtYear : Float, couponSize : Float, nominal : Float) : Float{
        return IsNanReturning(((paymentsAtYear*couponSize)/nominal) * 100)
    }

    fun CurrentIncomeCalc(paymentsAtYear : Float, couponSize : Float, cleanPrice: Float) : Float{
        return IsNanReturning(((paymentsAtYear*couponSize)/cleanPrice) * 100)

    }

    fun SimpleIncomeCalc(dirtyPrice : Float, daysUntil : Float, couponSize : Float ,nominal : Float, allPayments : Float) : Float{
        return IsNanReturning(((nominal - dirtyPrice + (couponSize * allPayments))/dirtyPrice) * (365/daysUntil) * 100)
    }

    fun TaxIncomeCalc(dirtyPrice : Float, daysUntil : Float, couponSize : Float, nominal : Float, allPayments : Float) : Float {
        return IsNanReturning(((((nominal - dirtyPrice + allPayments * couponSize) * 0.87f) / dirtyPrice) * (365 / daysUntil) * 100))
    }
    //конец блока формул


    fun MyCalendarDateFormat  (i : Int, i2 : Int , i3 : Int) : String{
        return (if (i3 < 10){"0$i3."} else "$i3.") + (if((i2+1) < 10){"0${i2+1}."} else "${i2+1}.") + "$i"    }

    fun IsNanOrInfin ( checkAnswer : String) : String{
        if(checkAnswer == "NaN" || checkAnswer == "Infinity" || checkAnswer == "-Infinity")
        {
            return "Ошибка!"
        } else {
            return checkAnswer
        }
    }

    //Nan value cannot be rounded so here we check - can we round it or not
    fun IsNanReturning(result : Float) : Float{
        if(result.isNaN() == true){
            return result
        } else {
            return ((result * 100.0).roundToInt() / 100.0).toFloat()
        }
    }
}
