package org.gatlin.Days

import org.gatlin.Helpers
import java.util.Dictionary

class Day6: Day() {
    override fun solve(){
        val input = Helpers.readFileFromResources("6.txt")

        var map = input.lines().map { row -> row.map {
            when (it) {
                '#' -> 1
                '^' -> 2
                else -> 0
            }
        } }.map{it.toMutableList()}.toMutableList()

        // Find the starting location
        var startingLocation = Pair(0, 0)
        for (row in 0 until map.size) {
            for (item in 0 until map[row].size) {
                if (map[row][item] == 2) {
                    startingLocation = Pair(row, item)
                }
            }
        }

        val directions = mutableMapOf(
            0 to Pair(-1, 0),  // Up
            1 to Pair(0, 1),   // Right
            2 to Pair(1, 0),   // Down
            3 to Pair(0, -1)   // Left
        )

        val arrows = mutableMapOf(
            0 to "^",
            1 to ">",
            2 to "v",
            3 to "<"
        )

        var curDirection = 0
        var movements = 0
        var currentLocation = startingLocation
        val visited = mutableSetOf(currentLocation)

//        println("Starting location: $startingLocation")
        while (true) {
            val nextLocation = addPairs(currentLocation, directions[curDirection]!!)

            // Check if the next location is out of bounds
            if (nextLocation.first !in 0 until map.size || nextLocation.second !in 0 until map[0].size) {
                break
            }

            // If the next location is an obstacle, turn right
            if (map[nextLocation.first][nextLocation.second] == 1) {
                curDirection = (curDirection + 1) % 4
            } else {
                // Move forward
                currentLocation = nextLocation
                visited.add(currentLocation)
                movements++
            }
        }

        println("Distinct positions visited: ${visited.size}")

        //part 2, brute force each possibility
        val locationsAndDirections = mutableSetOf<Pair<Pair<Int, Int>, Int>>()
        var possibilities = 0

        // Brute force through every possible empty space
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == 0) { // Empty space
                    // Temporarily add an obstacle
                    map[row][column] = 1

                    var currentLocation = startingLocation
                    var curDirection = 0
                    val locationsAndDirections = mutableSetOf<Pair<Pair<Int, Int>, Int>>()

                    var isLoop = false
                    while (true) {
                        // Add current position and direction
                        if (!locationsAndDirections.add(Pair(currentLocation, curDirection))) {
                            // Loop detected
                            isLoop = true
                            break
                        }

                        // Determine next location
                        val nextLocation = addPairs(currentLocation, directions[curDirection]!!)

                        // If the guard goes out of bounds, break
                        if (nextLocation.first !in map.indices || nextLocation.second !in map[0].indices) break

                        // If the next position is an obstacle, turn
                        if (map[nextLocation.first][nextLocation.second] == 1) {
                            curDirection = (curDirection + 1) % 4
                        } else {
                            // Move to the next location
                            currentLocation = nextLocation
                        }
                    }

                    // Count this position if it creates a loop
                    if (isLoop) {
                        possibilities++
                    }

                    // Restore the map
                    map[row][column] = 0
                }
            }
        }

        println("There are $possibilities options for us to create a loop!")
    }

    fun addPairs(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(p1.first + p2.first, p1.second + p2.second)
    }
}