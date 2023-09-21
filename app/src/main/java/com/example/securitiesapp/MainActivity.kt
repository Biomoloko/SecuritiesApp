package com.example.securitiesapp

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.red
import com.example.securitiesapp.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private lateinit var datePickerDialog : DatePickerDialog


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val myError : String = "Ошибка!"

        val defDaM = 10
        val defY = 2020
        var startDate = "$defDaM.$defDaM.$defY"
        var endDate = "$defDaM.$defDaM.$defY"
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var parsedStartDate = LocalDate.parse(startDate, formatter)
        var parsedEndDate = LocalDate.parse(endDate, formatter)
        var daysBetween : Long = 0

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


    //Calendars invisibility
        bindingClass.StartCalendar.visibility = View.GONE
        bindingClass.EndCalendar.visibility = View.GONE


    //Start Date works----
        bindingClass.StartDateButton.setOnClickListener{

            if(bindingClass.StartCalendar.visibility == View.GONE) {
                bindingClass.StartCalendar.visibility = View.VISIBLE
                bindingClass.EndCalendar.visibility = View.GONE
            } else {
                bindingClass.StartCalendar.visibility = View.GONE
            }
        }
        
        bindingClass.StartCalendar.setOnDateChangeListener { calendarView, i, i2, i3 ->
            bindingClass.StartDateButton.text = ProjectFunctions.MyCalendarDateFormat(i, i2, i3)
            startDate = ProjectFunctions.MyCalendarDateFormat(i, i2, i3)

            parsedStartDate = LocalDate.parse(startDate, formatter)
            daysBetween = ChronoUnit.DAYS.between(parsedStartDate, parsedEndDate) + 1

            bindingClass.CalculatedSellTime.setText(
                daysBetween.toString()
            )
        }
    //end----

    //End Date works----
        bindingClass.EndDateButton.setOnClickListener{
            parsedEndDate = LocalDate.parse(endDate, formatter)

            if (bindingClass.EndCalendar.visibility == View.GONE) {
                bindingClass.EndCalendar.visibility = View.VISIBLE
                bindingClass.StartCalendar.visibility = View.GONE
            } else {
                bindingClass.EndCalendar.visibility = View.GONE
            }
        }

        bindingClass.EndCalendar.setOnDateChangeListener { calendarView, i, i2, i3 ->
            bindingClass.EndDateButton.text = ProjectFunctions.MyCalendarDateFormat(i, i2, i3)
            endDate = ProjectFunctions.MyCalendarDateFormat(i, i2, i3)

            parsedEndDate = LocalDate.parse(endDate, formatter)
            daysBetween = ChronoUnit.DAYS.between(parsedStartDate, parsedEndDate)

            bindingClass.CalculatedSellTime.setText(
                daysBetween.toString()
            )
        }
    //end----


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
            bindingClass.CleanPrice.setText(ProjectFunctions.TextInUpField(cleanPrice)+" %")



            val nKD = ProjectFunctions.GetParseFloat(bindingClass.NKD.text.toString(), myError)
            bindingClass.NKD.setText(nKD.toString())


            //установка значений в нижние поля
            bindingClass.DirtyPrice.text = ProjectFunctions.DirtyPriceCalc(cleanPrice, nKD).toString()

            bindingClass.CouponIncome.text = ProjectFunctions.CouponInkomeCalc(paymentsAtYear, couponSize, nominal).toString()

            bindingClass.CurrentIncome.text = ProjectFunctions.CurrentIncomeCalc(paymentsAtYear, couponSize, cleanPrice).toString()

            bindingClass.SimpleIncome.text = ProjectFunctions.SimpleIncomeCalc(ProjectFunctions.DirtyPriceCalc(cleanPrice, nKD)
                ,daysBetween.toFloat(),couponSize,nominal,allPayments).toString()

            bindingClass.TaxIncome.text = ProjectFunctions.TaxIncomeCalc(ProjectFunctions.DirtyPriceCalc(cleanPrice, nKD),
                        daysBetween.toFloat(),couponSize,nominal,allPayments).toString()


            bindingClass.DirtyPrice.text = ProjectFunctions.IsNanOrInfin(bindingClass.DirtyPrice.text.toString())
            bindingClass.CouponIncome.text = ProjectFunctions.IsNanOrInfin(bindingClass.CouponIncome.text.toString())
            bindingClass.CurrentIncome.text = ProjectFunctions.IsNanOrInfin(bindingClass.CurrentIncome.text.toString())
            bindingClass.SimpleIncome.text = ProjectFunctions.IsNanOrInfin(bindingClass.SimpleIncome.text.toString())
            bindingClass.TaxIncome.text = ProjectFunctions.IsNanOrInfin(bindingClass.TaxIncome.text.toString())
        }

        //Reset Button
        bindingClass.ResetButton.setOnClickListener(){
            var defaultDate = "ДД.ММ.ГГГГ"
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

            bindingClass.StartDateButton.text = defaultDate
            bindingClass.EndDateButton.text = defaultDate
        }
    }
}




