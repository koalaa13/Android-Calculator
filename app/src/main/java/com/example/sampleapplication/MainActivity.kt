package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        val OPERATIONS = charArrayOf('+', '-', '*', '/')
        val CORRECT_EXPRESSION = """-?\d*\.?\d*[-+*/]-?\d*\.?\d*""".toRegex()
    }

    private lateinit var incorrectExpressionMessage: String
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
    private var operationIndex = -1

//    private class DigitsListener : View.OnClickListener {
//        override fun onClick(button: View?) {
//            if (expression == incorrectExpression) {
//                expression = ""
//            }
//            if (isLeadingZero()) {
//                expression = expression.dropLast(1)
//            }
//            expression += '1'
//            updateResult()
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        incorrectExpressionMessage = getString(R.string.incorrect_expression)

        result = findViewById(R.id.result)
        button0 = findViewById(R.id.button_0)

        button0.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '0'
            updateResult()
        }

        button1 = findViewById(R.id.button_1)

        button1.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '1'
            updateResult()
        }

        button2 = findViewById(R.id.button_2)

        button2.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '2'
            updateResult()
        }

        button3 = findViewById(R.id.button_3)

        button3.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '3'
            updateResult()
        }

        button4 = findViewById(R.id.button_4)

        button4.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '4'
            updateResult()
        }

        button5 = findViewById(R.id.button_5)

        button5.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '5'
            updateResult()
        }

        button6 = findViewById(R.id.button_6)

        button6.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '6'
            updateResult()
        }

        button7 = findViewById(R.id.button_7)

        button7.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '7'
            updateResult()
        }

        button8 = findViewById(R.id.button_8)

        button8.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '8'
            updateResult()
        }

        button9 = findViewById(R.id.button_9)

        button9.setOnClickListener {
            if (expression == incorrectExpressionMessage) {
                expression = ""
            }
            if (isLeadingZero()) {
                expression = expression.dropLast(1)
            }
            expression += '9'
            updateResult()
        }

        separatorButton = findViewById(R.id.separator_button)

        separatorButton.setOnClickListener {
            if (canPutSeparatorChar()) {
                expression += '.'
                updateResult()
            }
        }

        clearButton = findViewById(R.id.button_c)

        clearButton.setOnClickListener {
            expression = ""
            operationIndex = -1
            updateResult()
        }

        addButton = findViewById(R.id.button_plus)
        addButton.setOnClickListener {
            if (isLastSymbolDig() && operationIndex == -1) {
                operationIndex = expression.length
                expression += '+'
            } else {
                if (isLastSymbolOperation()) {
                    expression = expression.dropLast(1)
                    expression += '+'
                }
            }
            updateResult()
        }

        subButton = findViewById(R.id.button_minus)
        subButton.setOnClickListener {
            if (operationIndex == -1) {
                if (expression.isEmpty()) {
                    expression += '-'
                }
                if (isLastSymbolDig()) {
                    operationIndex = expression.length
                    expression += '-'
                }
            } else {
                if (isLastSymbolOperation()) {
                    if (OPERATIONS.contains(expression[expression.length - 1])) {
                        if (expression[expression.length - 1] in charArrayOf('+', '-')) {
                            expression = expression.dropLast(1)
                        }
                        expression += '-'
                    }
                }
            }
            updateResult()
        }

        mulButton = findViewById(R.id.button_mul)
        mulButton.setOnClickListener {
            if (isLastSymbolDig() && operationIndex == -1) {
                operationIndex = expression.length
                expression += '*'
            } else {
                if (isLastSymbolOperation()) {
                    expression = expression.dropLast(1)
                    expression += '*'
                }
            }
            updateResult()
        }
        divButton = findViewById(R.id.button_div)
        divButton.setOnClickListener {
            if (isLastSymbolDig() && operationIndex == -1) {
                operationIndex = expression.length
                expression += '/'
            } else {
                if (isLastSymbolOperation()) {
                    expression = expression.dropLast(1)
                    expression += '/'
                }
            }
            updateResult()
        }
        resButton = findViewById(R.id.button_res)

        resButton.setOnClickListener {
            if (expression != "") {
                evalExpression()
                operationIndex = -1
                updateResult()
            }
        }

        deleteButton = findViewById(R.id.delete_button)

        deleteButton.setOnClickListener {
            if (expression.isNotEmpty()) {
                if (expression == incorrectExpressionMessage) {
                    expression = ""
                } else {
                    if (isLastSymbolOperation()) {
                        operationIndex = -1
                    }
                    expression = expression.dropLast(1)
                }
                updateResult()
            }
        }

    }

    private fun canPutOperation(): Boolean {
        return expression.toDoubleOrNull() != null
    }

    private fun canPutSeparatorChar(): Boolean {
        if (expression.isEmpty() || isLastSymbolOperation()) {
            return false
        }
        for (i in expression.indices.reversed()) {
            if (OPERATIONS.contains(expression[i])) {
                return true
            }
            if (expression[i] == '.') {
                return false
            }
        }
        return true
    }

    private fun isLeadingZero(): Boolean {
        return expression == "0" || (expression.length > 1 && OPERATIONS.contains(expression[expression.length - 2]) && expression[expression.length - 1] == '0')
    }

    private fun updateResult() {
        result.text = expression
    }

    private fun isLastSymbolDig(): Boolean {
        return expression.isNotEmpty() && "[0-9]".toRegex()
            .matches(expression[expression.length - 1].toString())
    }

    private fun isLastSymbolOperation(): Boolean {
        return operationIndex != -1 && operationIndex == expression.length - 1
    }

    private fun evalExpression() {
        if (expression.toDoubleOrNull() != null) {
            return
        }
        if (!expression.matches(CORRECT_EXPRESSION)) {
            expression = incorrectExpressionMessage
            return
        }
        if (operationIndex >= 0) {
            val firstNumber: Double? =
                (expression.substring(0, operationIndex)).toDoubleOrNull()
            val secondNumber: Double? =
                (expression.substring(operationIndex + 1)).toDoubleOrNull()
            if (firstNumber == null || secondNumber == null) {
                expression = incorrectExpressionMessage
                return
            }
            when (expression[operationIndex]) {
                '+' -> expression = (firstNumber + secondNumber).toString()
                '-' -> expression = (firstNumber - secondNumber).toString()
                '*' -> expression = (firstNumber * secondNumber).toString()
                '/' -> expression = if (secondNumber == 0.0) {
                    incorrectExpressionMessage
                } else {
                    (firstNumber / secondNumber).toString()
                }
            }
        } else {
            expression = incorrectExpressionMessage
        }
    }

}