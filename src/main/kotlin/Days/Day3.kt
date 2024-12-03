package org.gatlin.Days

import org.gatlin.Helpers

class Day3: Day() {
    override fun solve(){
        val input = Helpers.readFileFromResources("3.txt")
        //part 1
        val pattern = Regex("""mul\((\d+),(\d+)\)""")
        val statements = pattern.findAll(input).map { it.value }.toList()
        val pairs = statements.map { statement -> Pair(statement.substring(4).substringBefore(","), statement.substring(0,statement.length-1).substringAfter(",")) }
        val part1 = pairs.sumOf { (a,b) -> a.toInt()*b.toInt() }
        println(part1)

        //part 2
        val secondPattern = Regex("""(mul\((\d+,\d+)\)|(don't|do)\(\))""")
        val newStatements = secondPattern.findAll(input).map{it.value}.toList()
        var lastRelevantCommand = "do()"
        val relevantInstructions = mutableListOf<String>()
        newStatements.forEach{command ->
            when{
                command == "do()" || command == "don't()" -> {
                    lastRelevantCommand = command
                }
                command.startsWith("mul(") && lastRelevantCommand == "do()" -> {
                    relevantInstructions.add(command)
                }
            }
        }
            val part2 = relevantInstructions.map { statement -> Pair(statement.substring(4).substringBefore(","), statement.substring(0,statement.length-1).substringAfter(",")) }
            .sumOf { (a,b) -> a.toInt() * b.toInt() }
        println(part2)

    }
}