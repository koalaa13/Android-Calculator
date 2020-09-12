package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "MainActivity"
        val OPERATIONS = charArrayOf('+', '-', '*', '/')
        val CORRECT_EXPRESSION = """-?\d*\.?\d*[-+*/]-?\d*\.?\d*""".toRegex()
        const val EXPRESSION_KEY = "EXPRESSION_KEY"
        const val OPERATION_INDEX_KEY = "OPERATION_INDEX_KEY"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState.putString(EXPRESSION_KEY, expression)
        outState.putInt(OPERATION_INDEX_KEY, operationIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i(TAG, "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        expression = savedInstanceState.getString(EXPRESSION_KEY).toString()
        operationIndex = savedInstanceState.getInt(OPERATION_INDEX_KEY)
        if (expression != "") {
            updateResult()
        }
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

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    lateinit var divButton: Button
    lateinit var resButton: Button
    lateinit var deleteButton: Button

    private var expression: String = ""
    private var operationIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        incorrectExpressionMessage = getString(R.string.incorrect_expression)

        result = findViewById(R.id.result)
        button0 = findViewById(R.id.button_0)
        button0.setOnClickListener(this)

        button1 = findViewById(R.id.button_1)
        button1.setOnClickListener(this)

        button2 = findViewById(R.id.button_2)
        button2.setOnClickListener(this)

        button3 = findViewById(R.id.button_3)
        button3.setOnClickListener(this)

        button4 = findViewById(R.id.button_4)
        button4.setOnClickListener(this)

        button5 = findViewById(R.id.button_5)
        button5.setOnClickListener(this)

        button6 = findViewById(R.id.button_6)
        button6.setOnClickListener(this)

        button7 = findViewById(R.id.button_7)
        button7.setOnClickListener(this)

        button8 = findViewById(R.id.button_8)
        button8.setOnClickListener(this)

        button9 = findViewById(R.id.button_9)
        button9.setOnClickListener(this)

        separatorButton = findViewById(R.id.separator_button)
        separatorButton.setOnClickListener(this)

        clearButton = findViewById(R.id.button_c)
        clearButton.setOnClickListener(this)

        addButton = findViewById(R.id.button_plus)
        addButton.setOnClickListener(this)

        subButton = findViewById(R.id.button_minus)
        subButton.setOnClickListener(this)

        mulButton = findViewById(R.id.button_mul)
        mulButton.setOnClickListener(this)

        divButton = findViewById(R.id.button_div)
        divButton.setOnClickListener(this)

        resButton = findViewById(R.id.button_res)
        resButton.setOnClickListener(this)

        deleteButton = findViewById(R.id.delete_button)
        deleteButton.setOnClickListener(this)
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
        return expression == "0" || (expression.length > 1 && operationIndex == expression.length - 2 && expression[expression.length - 1] == '0')
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

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        val id = v.id
        if (v is Button) {
            if (id == R.id.button_0 || id == R.id.button_1 || id == R.id.button_2 || id == R.id.button_3 || id == R.id.button_4 ||
                id == R.id.button_5 || id == R.id.button_6 || id == R.id.button_7 || id == R.id.button_8 || id == R.id.button_9

            ) {
                if (expression == incorrectExpressionMessage) {
                    expression = ""
                }
                if (isLeadingZero()) {
                    expression = expression.dropLast(1)
                }
                expression += v.text
            }
            if (id == R.id.separator_button) {
                if (canPutSeparatorChar()) {
                    expression += v.text
                }
            }
            if (id == R.id.button_c) {
                expression = ""
                operationIndex = -1
            }
            if (id == R.id.button_plus || id == R.id.button_div || id == R.id.button_mul) {
                if (isLastSymbolDig() && operationIndex == -1) {
                    operationIndex = expression.length
                    expression += v.text
                } else {
                    if (isLastSymbolOperation()) {
                        expression = expression.dropLast(1)
                        expression += v.text
                    }
                }
            }
            if (id == R.id.button_minus) {
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
            }
            if (id == R.id.button_res) {
                if (expression != "") {
                    evalExpression()
                    operationIndex = -1
                }
            }
            if (id == R.id.delete_button) {
                if (expression.isNotEmpty()) {
                    if (expression == incorrectExpressionMessage) {
                        expression = ""
                    } else {
                        if (isLastSymbolOperation()) {
                            operationIndex = -1
                        }
                        expression = expression.dropLast(1)
                    }
                }
            }
        }
        updateResult()
    }
}