package org.gatlin.Days

import org.gatlin.Helpers
import kotlin.math.pow

class Day7: Day() {
    override fun solve(){
        val input = Helpers.readFileFromResources("7.txt")
        val eqLines = input.split("\n")

        // Parse the input into a list of pairs: (targetValue, numbers)
        val equations = eqLines.map { eq ->
            val target = eq.substringBefore(":").toLong()
            val numbers = eq.substringAfter(": ").split(" ").map { it.toLong() }
            target to numbers
        }

        // Function to evaluate all operator combinations
        fun evaluateOperators(target: Long, numbers: List<Long>): Boolean {
            // If there's only one number, it can't match the target
            if (numbers.size < 2 && numbers.get(0) != target) return false

            // Total possible combinations of '+' and '*' operators
            val operatorsCount = numbers.size - 1
            val totalCombinations = 1.shl(operatorsCount) // 2^operatorsCount

            // Generate all combinations
            for (i in 0 until totalCombinations) {
                var result = numbers[0]
                var combination = i

                // Apply the operators left-to-right
                for (j in 0 until operatorsCount) {
                    val operator = if (combination and 1 == 0) '+' else '*'
                    result = if (operator == '+') result + numbers[j + 1] else result * numbers[j + 1]
                    combination = combination shr 1
                }

                // Check if the result matches the target
                if (result == target) return true
            }

            return false
        }

        // Part 2
        fun evaluateOperatorsWithConcat(target: Long, numbers: List<Long>): Boolean {
            // If there's only one number, it can't match the target
            if (numbers.size < 2) return false

            // Total possible combinations of '+', '*', and '||'
            val operatorsCount = numbers.size - 1
            val totalCombinations = 3.toDouble().pow(operatorsCount).toInt() // 3^operatorsCount

            // Generate all combinations
            for (i in 0 until totalCombinations) {
                var result = numbers[0]
                var combination = i

                // Apply the operators left-to-right
                for (j in 0 until operatorsCount) {
                    val operator = when (combination % 3) {
                        0 -> '+' // Addition
                        1 -> '*' // Multiplication
                        2 -> '|' // Concatenation
                        else -> throw IllegalStateException("Invalid operator index")
                    }
                    combination /= 3

                    result = when (operator) {
                        '+' -> result + numbers[j + 1]
                        '*' -> result * numbers[j + 1]
                        '|' -> (result.toString() + numbers[j + 1].toString()).toLong()
                        else -> throw IllegalStateException("Unexpected operator")
                    }
                }

                // Check if the result matches the target
                if (result == target) return true
            }

            return false
        }

        // Compute the total calibration result
        val totalCalibrationResult = equations
            .filter { (target, numbers) -> evaluateOperators(target, numbers) }
            .sumOf { it.first }
        val totalCalibrationResultWithConcat = equations
            .filter { (target, numbers) -> evaluateOperatorsWithConcat(target, numbers) }
            .sumOf { it.first }


        println("Part 1: $totalCalibrationResult")
        println("Part 2: $totalCalibrationResultWithConcat")
    }

}