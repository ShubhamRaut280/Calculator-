package com.shubham.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.compose.material3.Button
import com.shubham.unitconverter.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastDigit = false
    var stateError = false
    var lastdot = false
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
    fun onEqual()
    {
        if (lastDigit && !stateError)
        {
            var txt = binding.input.text.toString()
            var expression = ExpressionBuilder(txt).build()
            try {
                var res = expression.evaluate()
                binding.output.text = res.toString()

            }catch (e : ArithmeticException)
            {
                binding.output.text = "Error"
                stateError = true
                lastDigit = false
            }
        }
    }

    fun onOperatorclick(view: View) {
        if(!stateError && lastDigit)
        {
            binding.input.append((view as Button).text)
            lastdot = false
            lastDigit = false
            onEqual()
        }
    }

    fun onClearclick(view: View) {
        binding.input.text = ""
        lastdot = false
        lastDigit = false
        stateError = false
    }

    fun onEqualclick(view: View) {
        onEqual()
    }

    fun onDigitclick(view: View) {
        if (stateError)
        {
           binding.input.text = (view as Button).text
            stateError = false
        }
        else
        {
            binding.input.append((view as Button).text.toString())
        }
        lastDigit = true

        onEqual()
    }

    fun onACclick(view: View) {
        binding.input.text = ""
        binding.output.text = ""
        stateError = false
        lastDigit = false
        lastdot = false

    }
}