package org.gatlin

class Day1{
    companion object {
        fun buildLists(): Pair<List<Int>, List<Int>>? {
            val list1: MutableList<Int> = mutableListOf()
            val list2: MutableList<Int> = mutableListOf()

            val input = Helpers.readFileFromResources("Day1Part1.txt")

            for(line in input.split("\n")){
                val firstVal = line.split(" ").first()
                val secondVal = line.split(" ").last()

                list1.add(firstVal.toInt())
                list2.add(secondVal.toInt())
            }
            list1.sort()
            list2.sort()
            return Pair(list1, list2)
        }

        fun buildDistances(list1: List<Int>, list2: List<Int>): List<Int>{
            val distances: MutableList<Int> = mutableListOf()
            if(list1.size != list2.size){
                throw IllegalArgumentException("list1 and list2 must be the same size")
            }
            for(i in 0..list1.size - 1){
                distances.add(Math.abs(list1.get(i) - list2.get(i)))
            }

            return distances
        }

        fun doDay1Part1(){
            val lists: Pair<List<Int>, List<Int>> = Day1.buildLists()!!
            val distances = Day1.buildDistances(lists.first, lists.second)
            println(distances.sum())
        }

        fun buildSimilarityScore(list1: List<Int>, list2: List<Int>){
            val occurrencesInList2 = HashMap<Int, Int>()

            for(num in list2){
                if(occurrencesInList2.containsKey(num)){
                    occurrencesInList2[num] = occurrencesInList2[num]!! + 1
                }else{
                    occurrencesInList2[num] = 1
                }
            }

            var similarityScore = 0

            for(num in list1){
                if(occurrencesInList2.containsKey(num)){
                    similarityScore += num*occurrencesInList2[num]!!
                }else{
                    println("$num not in list2")
                }
            }

            print(similarityScore)
        }

        fun doDay1Part2(){
            val lists: Pair<List<Int>, List<Int>> = Day1.buildLists()!!
            buildSimilarityScore(lists.first, lists.second)
        }
    }
}