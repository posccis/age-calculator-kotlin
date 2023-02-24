package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener{
            clickDatePicker()        }
    }
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Year was $selectedYear, month was ${selectedMonth +1} and day was $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse("$selectedDayOfMonth/${selectedMonth +1}/$selectedYear")
            tvSelectedDate?.text = date.toString()
            val selectedDateInMinutes = date.time / 60000
            val currentDateInMinutes = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvAgeInMinutes?.text = differenceInMinutes.toString()
        },
            year, month, day
        ).show()

    }
}