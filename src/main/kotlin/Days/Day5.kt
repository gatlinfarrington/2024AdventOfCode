package org.gatlin.Days

import org.gatlin.Helpers
import java.util.*

class Day5: Day() {
    override fun solve(){
        val input = Helpers.readFileFromResources("5.txt")

        part1(input)
        part2(input)
    }

    fun part1(input: String){
        val firstSection= mutableListOf<Pair<Int,Int>>()
        val secondSection = mutableListOf<List<Int>>()

        input.split("\n").forEach{line -> if(line.contains("|")){
            firstSection.add(Pair(line.split("|").first().toInt(), line.split("|").last().toInt()))
        }else{
            if(line != ""){
                secondSection.add(line.split(",").map { it -> it.toInt() })
            }
        } }

        val goodInstructions = mutableListOf<List<Int>>()
        secondSection.forEach { list ->
            var prevVals = Stack<Int>()
            var isGood = true
            list.forEach { num ->
                firstSection.forEach { rule ->
                    if (rule.first == num) {
                        if (prevVals.contains(rule.second)) {
                            //BAD
                            isGood = false
                        }
                    }
                }
                prevVals.add(num)
            }
            if (isGood) {
                goodInstructions.add(list)
            }
        }
        val total = goodInstructions.map{it -> it[it.size/2]}.sum()

        println("part1: $total")
    }

    fun part2(input: String){
        val firstSection= mutableListOf<Pair<Int,Int>>()
        val secondSection = mutableListOf<List<Int>>()

        input.split("\n").forEach{line -> if(line.contains("|")){
            firstSection.add(Pair(line.split("|").first().toInt(), line.split("|").last().toInt()))
        }else{
            if(line != ""){
                secondSection.add(line.split(",").map { it -> it.toInt() })
            }
        } }

        val goodInstructions = mutableListOf<List<Int>>()
        secondSection.forEach { list ->
            var prevVals = Stack<Int>()
            var isGood = true
            list.forEach { num ->
                firstSection.forEach { rule ->
                    if (rule.first == num) {
                        if (prevVals.contains(rule.second)) {
                            //BAD
                            isGood = false
                        }
                    }
                }
                prevVals.add(num)
            }
            if (isGood) {
                goodInstructions.add(list)
            }
        }

        val badInstructions = secondSection.filter{it !in goodInstructions}

        fun reorderUpdate(update: List<Int>): List<Int> {
            val orderingGraph = mutableMapOf<Int, MutableList<Int>>()
            val inDegree = mutableMapOf<Int, Int>()

            firstSection.forEach { (a, b) ->
                if (a in update && b in update) {
                    orderingGraph.computeIfAbsent(a) { mutableListOf() }.add(b)
                    inDegree[b] = inDegree.getOrDefault(b, 0) + 1
                    inDegree.putIfAbsent(a, 0)
                }
            }

            val sortedOrder = mutableListOf<Int>()
            val zeroInDegree = ArrayDeque<Int>()
            inDegree.forEach { (node, degree) ->
                if (degree == 0) zeroInDegree.add(node)
            }

            while (zeroInDegree.isNotEmpty()) {
                val node = zeroInDegree.removeFirst()
                sortedOrder.add(node)
                orderingGraph[node]?.forEach { neighbor ->
                    inDegree[neighbor] = inDegree[neighbor]!! - 1
                    if (inDegree[neighbor] == 0) zeroInDegree.add(neighbor)
                }
            }

            return sortedOrder
        }

        val middleSum = badInstructions.map { reorderUpdate(it) }
            .map { it[it.size / 2] }
            .sum()

        println(middleSum)
    }
}