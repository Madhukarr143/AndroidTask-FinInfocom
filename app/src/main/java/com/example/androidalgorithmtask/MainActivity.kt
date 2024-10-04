package com.example.androidalgorithmtask


import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidalgorithmtask.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val numberText = mutableListOf<TextView>()
    private val allNumbers = (1..100).toList()
    private val fibonacciNumber = generateFibonacciNumbers(100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.primeBtn.setBackgroundColor(Color.YELLOW)
        binding.evenBtn.setBackgroundColor(Color.RED)
        binding.oddBtn.setBackgroundColor(Color.CYAN)
        binding.fibonacciBtn.setBackgroundColor(Color.MAGENTA)

        createNumbersButton()
        setUpButtonListeners()
    }

    private fun createNumbersButton() {
        binding.gridLayout.removeAllViews()
        numberText.clear()

        allNumbers.forEach { number ->
            val text = TextView(this).apply {
                text = number.toString()
                setBackgroundColor(Color.TRANSPARENT)
                gravity = Gravity.CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    setMargins(8, 8, 8, 8)
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
            }
            numberText.add(text)
            binding.gridLayout.addView(text)

        }
    }

    private fun setUpButtonListeners() {
        binding.primeBtn.setOnClickListener { highlightNumbers(this::isPrime, Color.YELLOW) }
        binding.evenBtn.setOnClickListener { highlightNumbers(this::isEven, Color.RED) }
        binding.oddBtn.setOnClickListener { highlightNumbers(this::isOdd, Color.CYAN) }
        binding.fibonacciBtn.setOnClickListener { highlightNumbers(this::isFibonacci, Color.MAGENTA) }
    }

    private fun highlightNumbers(predicate: (Int) -> Boolean, color: Int) {
        numberText.forEach { button ->
            val number = button.text.toString().toInt()
            if (predicate(number)) {
                button.setBackgroundColor(color)
            } else
                button.setBackgroundColor(Color.LTGRAY)

        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        if (number <= 3) return true
        if (number % 2 == 0 || number % 3 == 0) return false
        var i = 5
        while (i * i <= number) {
            if (number % i == 0 || number % (i + 2) == 0) return false
            i += 6
        }
        return true
    }

    private fun isEven(num: Int) = num % 2 == 0
    private fun isOdd(num: Int) = num % 2 != 0
    private fun isFibonacci(num: Int) = num in fibonacciNumber

    private fun generateFibonacciNumbers(limit: Int): Set<Int> {
        val fibonacciNumber = mutableSetOf<Int>()
        var a = 0
        var b = 1
        while (a <= limit) {
            fibonacciNumber.add(a)
            val temp = a
            a = b
            b = temp + a
        }
        return fibonacciNumber
    }
        /*setContent {
            AndroidAlgorithmTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AndroidAlgorithmTaskTheme {
            Greeting("Android")
        }
    }*/
}