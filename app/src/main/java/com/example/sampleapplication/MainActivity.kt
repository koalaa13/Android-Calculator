package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "MainActivity"
        val OPERATIONS = charArrayOf('+', '-', '*', '/')
        const val EXPRESSION_KEY = "EXPRESSION_KEY"
        const val OPERATION_INDEX_KEY = "OPERATION_INDEX_KEY"
    }

    private lateinit var incorrectExpressionMessage: String

    private var expression: String = ""
    private var operationIndex = -1

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")


        incorrectExpressionMessage = getString(R.string.incorrect_expression)

        result.isSelected = true
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        separatorButton.setOnClickListener(this)
        clearButton.setOnClickListener(this)
        addButton.setOnClickListener(this)
        subButton.setOnClickListener(this)
        mulButton.setOnClickListener(this)
        divButton.setOnClickListener(this)
        resButton.setOnClickListener(this)
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
            if (id == R.id.button0 || id == R.id.button1 || id == R.id.button2 || id == R.id.button3 || id == R.id.button4 ||
                id == R.id.button5 || id == R.id.button6 || id == R.id.button7 || id == R.id.button8 || id == R.id.button9

            ) {
                if (expression == incorrectExpressionMessage) {
                    expression = ""
                }
                if (isLeadingZero()) {
                    expression = expression.dropLast(1)
                }
                expression += v.text
            }
            if (id == R.id.separatorButton) {
                if (canPutSeparatorChar()) {
                    expression += v.text
                }
            }
            if (id == R.id.clearButton) {
                expression = ""
                operationIndex = -1
            }
            if (id == R.id.addButton || id == R.id.divButton || id == R.id.mulButton) {
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
            if (id == R.id.subButton) {
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
            if (id == R.id.resButton) {
                if (expression != "") {
                    evalExpression()
                    operationIndex = -1
                }
            }
            if (id == R.id.deleteButton) {
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