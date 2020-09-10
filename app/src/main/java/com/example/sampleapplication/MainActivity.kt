package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"

    }

    val OPERATIONS = charArrayOf(
        getString(R.string.plus_symbol)[0],
        getString(R.string.minus_symbol)[0],
        getString(R.string.mul_symbol)[0],
        getString(R.string.div_symbol)[0]
    )
    val incorrectExpression = getString(R.string.incorrect_expression)
    lateinit var result: EditText
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var separatorButton: Button
    lateinit var clearButton: Button
    lateinit var addButton: Button
    lateinit var subButton: Button
    lateinit var mulButton: Button
    lateinit var divButton: Button
    lateinit var resButton: Button
    lateinit var deleteButton: Button

    var expression: String = ""
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate");

        result = findViewById(R.id.result)
        button0 = findViewById(R.id.button_0)
        button1 = findViewById(R.id.button_1)
        button2 = findViewById(R.id.button_2)
        button3 = findViewById(R.id.button_3)
        button4 = findViewById(R.id.button_4)
        button5 = findViewById(R.id.button_5)
        button6 = findViewById(R.id.button_6)
        button7 = findViewById(R.id.button_7)
        button8 = findViewById(R.id.button_8)
        button9 = findViewById(R.id.button_9)
        separatorButton = findViewById(R.id.separator_button)
        clearButton = findViewById(R.id.button_c)
        addButton = findViewById(R.id.button_plus)
        subButton = findViewById(R.id.button_minus)
        mulButton = findViewById(R.id.button_mul)
        divButton = findViewById(R.id.button_div)
        resButton = findViewById(R.id.button_res)
        deleteButton = findViewById(R.id.delete_button)

//        button0.setOnClickListener {
//            if (expression == incorrectExpression || ) {
//                expression = "";
//            }
//            expression += '0';
//        }
    }

    private fun updateResult() {
        result.setText(expression)
    }

    private fun evalExpression() {
        if (expression.filter { ch -> OPERATIONS.contains(ch) }.count() != 1) {
            expression = incorrectExpression
            return
        }
        val operationIndex = expression.indexOfAny(OPERATIONS)
        if (operationIndex >= 0) {
            val firstNumber: BigDecimal? =
                (expression.substring(0, operationIndex)).toBigDecimalOrNull()
            val secondNumber: BigDecimal? =
                (expression.substring(operationIndex + 1)).toBigDecimalOrNull()
            if (firstNumber == null || secondNumber == null) {
                expression = incorrectExpression
                return
            }
            when (expression[operationIndex]) {
                '+' -> expression = firstNumber.add(secondNumber).toString()
                '-' -> expression = firstNumber.subtract(secondNumber).toString()
                '*' -> expression = firstNumber.multiply(secondNumber).toString()
                '/' -> expression = firstNumber.divide(secondNumber).toString()
            }
        } else {
            expression = incorrectExpression
        }
    }

}