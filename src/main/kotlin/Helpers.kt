package org.gatlin

import org.gatlin.Days.*

class Helpers{
    companion object{
        fun readFileFromResources(fileName: String): String {
            val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
                ?: throw IllegalArgumentException("File $fileName not found in resources")
            return inputStream.bufferedReader().use { it.readText() }
        }

        fun getSolveMethodForDay(dayNumber: Int): () -> Unit {
            val solutionDay = when (dayNumber) {
                1 -> Day1()
                2 -> Day2()
                3 -> Day3()
                4 -> Day4()
                5 -> Day5()
                6 -> Day6()
                7 -> Day7()
                8 -> Day8()
                9 -> Day9()
                10 -> Day10()
                11 -> Day11()
                12 -> Day12()
                13 -> Day13()
                14 -> Day14()
                15 -> Day15()
                16 -> Day16()
                17 -> Day17()
                18 -> Day18()
                19 -> Day19()
                20 -> Day20()
                21 -> Day21()
                22 -> Day22()
                23 -> Day23()
                24 -> Day24()
                25 -> Day25()
                else -> throw IllegalArgumentException("Invalid day number: $dayNumber")
            }
            return solutionDay::solve
        }
    }
}