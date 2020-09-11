package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var operations: CharArray
    private lateinit var incorrectExpression: String
    lateinit var result: TextView
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

    private var expression: String = ""
    private var isLastButtonRes = false
    private var wasOperation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        operations = charArrayOf(
            getString(R.string.plus_symbol)[0],
            getString(R.string.minus_symbol)[0],
            getString(R.string.mul_symbol)[0],
            getString(R.string.div_symbol)[0]
        )
        incorrectExpression = getString(R.string.incorrect_expression)

        result = findViewById(R.id.result)
        button0 = findViewById(R.id.button_0)

        button0.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '0'
            updateResult()
        }

        button1 = findViewById(R.id.button_1)

        button1.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '1'
            updateResult()
        }

        button2 = findViewById(R.id.button_2)

        button2.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '2'
            updateResult()
        }

        button3 = findViewById(R.id.button_3)

        button3.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '3'
            updateResult()
        }

        button4 = findViewById(R.id.button_4)

        button4.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '4'
            updateResult()
        }

        button5 = findViewById(R.id.button_5)

        button5.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '5'
            updateResult()
        }

        button6 = findViewById(R.id.button_6)

        button6.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '6'
            updateResult()
        }

        button7 = findViewById(R.id.button_7)

        button7.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '7'
            updateResult()
        }

        button8 = findViewById(R.id.button_8)

        button8.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '8'
            updateResult()
        }

        button9 = findViewById(R.id.button_9)

        button9.setOnClickListener {
            if (expression == incorrectExpression || isLastButtonRes) {
                expression = ""
                if (isLastButtonRes) {
                    isLastButtonRes = false
                }
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '9'
            updateResult()
        }

        separatorButton = findViewById(R.id.separator_button)
        clearButton = findViewById(R.id.button_c)

        clearButton.setOnClickListener {
            expression = ""
            isLastButtonRes = false
            wasOperation = false
            updateResult()
        }

        addButton = findViewById(R.id.button_plus)
        addButton.setOnClickListener {
            if (isLastSymbolDig() && !wasOperation) {
                expression += '+'
                isLastButtonRes = false
                wasOperation = true
                updateResult()
            }
        }

        subButton = findViewById(R.id.button_minus)
        subButton.setOnClickListener {
            if (expression.isEmpty() || (isLastSymbolDig() && !wasOperation)) {
                wasOperation = expression.isNotEmpty()
                expression += '-'
                isLastButtonRes = false
                updateResult()
            }
        }

        mulButton = findViewById(R.id.button_mul)
        mulButton.setOnClickListener {
            if (isLastSymbolDig() && !wasOperation) {
                expression += '*'
                isLastButtonRes = false
                wasOperation = true
                updateResult()
            }
        }
        divButton = findViewById(R.id.button_div)
        divButton.setOnClickListener {
            if (isLastSymbolDig() && !wasOperation) {
                expression += '/'
                isLastButtonRes = false
                wasOperation = true
                updateResult()
            }
        }
        resButton = findViewById(R.id.button_res)

        resButton.setOnClickListener {
            if (expression != "" && !isLastButtonRes) {
                evalExpression()
                wasOperation = false
                isLastButtonRes = true
                updateResult()
            }
        }

        deleteButton = findViewById(R.id.delete_button)

        deleteButton.setOnClickListener {
            if (expression.isNotEmpty()) {
                if (expression == incorrectExpression) {
                    expression = ""
                } else {
                    if (operations.contains(expression[expression.length - 1])) {
                        wasOperation = false
                    }
                    expression = expression.dropLast(1)
                }
                updateResult()
            }
        }

    }

    private fun isLeadingZero(): Boolean {
        return expression == "0" || (expression.length > 1 && operations.contains(expression[expression.length - 2]) && expression[expression.length - 1] == '0')
    }

    private fun updateResult() {
        result.text = expression
    }

    private fun isLastSymbolDig(): Boolean {
        return expression.isNotEmpty() && "[0-9]".toRegex()
            .matches(expression[expression.length - 1].toString())
    }

    private fun evalExpression() {
        var needOperationsCount: Int = 1
        if (expression.isNotEmpty() && expression[0] == '-') {
            needOperationsCount++
        }
        if (expression.filter { ch -> operations.contains(ch) }.count() != needOperationsCount) {
            expression = incorrectExpression
            return
        }
        val operationIndex = expression.lastIndexOfAny(operations)
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
                operations[0] -> expression = firstNumber.add(secondNumber).toString()
                operations[1] -> expression = firstNumber.subtract(secondNumber).toString()
                operations[2] -> expression = firstNumber.multiply(secondNumber).toString()
                operations[3] -> expression = firstNumber.divide(secondNumber).toString()
            }
        } else {
            expression = incorrectExpression
        }
    }

}