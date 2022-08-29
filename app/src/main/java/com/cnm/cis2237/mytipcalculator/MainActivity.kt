package com.cnm.cis2237.mytipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

const val HUNDRED_PERCENT = 100.0
const val TIP_INCREMENT_PERCENT = 5
const val PEOPLE_INCREMENT_VALUE = 1
const val MIN_TIP = 0
const val MIN_PEOPLE = 2
const val MAX_TIP = 95
const val MAX_PEOPLE = 20


class MainActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {

    //Form controls
    private lateinit var expensePerPersonTextView: TextView
    private lateinit var billEditText: EditText

    private lateinit var addTipButton: ImageButton
    private lateinit var tipTextView: TextView
    private lateinit var subtractTipButton: ImageButton

    private lateinit var addPeopleButton: ImageButton
    private lateinit var numberOfPeopleTextView: TextView
    private lateinit var subtractPeopleButton: ImageButton

    private var numberOfPeople = 4
    private var tipPercent   = 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        //connect views
        expensePerPersonTextView = findViewById(R.id.expensePerPersonTextView)
        billEditText = findViewById(R.id.billEdittext)

        addTipButton = findViewById(R.id.addTipButton)
        tipTextView = findViewById(R.id.tipTextView)
        subtractTipButton = findViewById(R.id.subtractTipButton)

        addPeopleButton = findViewById(R.id.addPeopleButton)
        numberOfPeopleTextView = findViewById(R.id.numberOfPeopleTextView)
        subtractPeopleButton = findViewById(R.id.subtractPeopleButton)

       // bind buttons
        addTipButton.setOnClickListener (this)
        subtractTipButton.setOnClickListener(this)
        addPeopleButton.setOnClickListener(this)
        subtractPeopleButton.setOnClickListener(this)

        billEditText.addTextChangedListener(this)

    }

    override fun afterTextChanged(s: Editable?) {
        if(!billEditText.text.isEmpty()){
            calculate()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if(!billEditText.text.isEmpty()){
            calculate()
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(!billEditText.text.isEmpty()){
            calculate()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addTipButton -> incrementTip()
            R.id.subtractTipButton -> subtractTip()
            R.id.addPeopleButton -> incrementPeople()
            R.id.subtractPeopleButton -> subtractPeople()
        }

        calculate()
    }

    private fun calculate() {
        val totalBill = billEditText.text.toString().toDouble()
        val totalExpense = ((HUNDRED_PERCENT + tipPercent)/ HUNDRED_PERCENT * totalBill)
        val individualExpense =  totalExpense/numberOfPeople
        expensePerPersonTextView.text = String.format("$%.2f", individualExpense)
    }

    private fun subtractPeople() {
        if(numberOfPeople !=  MIN_PEOPLE){
            numberOfPeople -= PEOPLE_INCREMENT_VALUE
            numberOfPeopleTextView.text = numberOfPeople.toString()
        }
    }

    private fun incrementPeople() {
        if(numberOfPeople !=  MAX_PEOPLE){
            numberOfPeople += PEOPLE_INCREMENT_VALUE
            numberOfPeopleTextView.text = numberOfPeople.toString()
        }
    }

    private fun subtractTip() {
        if(tipPercent != MIN_TIP){
            tipPercent -= TIP_INCREMENT_PERCENT
            tipTextView.text = String.format("%d%%", tipPercent)
        }
    }

    private fun incrementTip() {
        if(tipPercent != MAX_TIP){
            tipPercent += TIP_INCREMENT_PERCENT
            tipTextView.text = String.format("%d%%", tipPercent)
        }
    }
}