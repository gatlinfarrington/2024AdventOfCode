package org.gatlin.Days

import org.gatlin.Helpers

class Day4: Day() {
    override fun solve() {
        val input = Helpers.readFileFromResources("4.txt")
        val grid = input.lines().map { it.toCharArray() }
        val word = "XMAS"
        val directions = listOf(
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1),
            Pair(-1, 0),
            Pair(-1, -1),
            Pair(-1, 1),
            Pair(1, -1),
            Pair(1, 1)
        )

        val wordLength = word.length
        val rows = grid.size
        val result = mutableListOf<Pair<Int, Int>>()

        fun isValid(x: Int, y: Int) = x in 0 until rows && y in 0 until grid[x].size

        fun checkDirection(x: Int, y: Int, dx: Int, dy: Int): Boolean {
            for (i in 0 until wordLength) {
                val newX = x + i * dx
                val newY = y + i * dy
                if (!isValid(newX, newY) || grid[newX][newY].uppercaseChar() != word[i]) {
                    return false
                }
            }
            return true
        }

        for (x in grid.indices) {
            for (y in grid[x].indices) {
                if (grid[x][y] == word[0]) {
                    for ((dx, dy) in directions) {
                        if (checkDirection(x, y, dx, dy)) {
                            result.add(Pair(x, y))
                        }
                    }
                }
            }
        }

        println("part1 ${result.size}")

        part2(input)
    }

    fun part2(input: String){
        val grid = input.lines().map { it.toCharArray() }
        val rows = grid.size
        var count = 0

        fun isValid(x: Int, y: Int): Boolean {
            return x in 0 until rows && y in 0 until grid[x].size
        }

        fun isOpposite(c: Char, has: Char): Boolean {
            return (has == 'M' && c == 'S') || (has == 'S' && c == 'M')
        }

        for (x in 1 until rows - 1) {
            for (y in 1 until grid[x].size - 1) {
                if (grid[x][y] == 'A') {
                    val upLeft = if (isValid(x - 1, y - 1)) grid[x - 1][y - 1] else null
                    val upRight = if (isValid(x - 1, y + 1)) grid[x - 1][y + 1] else null
                    val downLeft = if (isValid(x + 1, y - 1)) grid[x + 1][y - 1] else null
                    val downRight = if (isValid(x + 1, y + 1)) grid[x + 1][y + 1] else null

                    if (upLeft != null && upRight != null && downLeft != null && downRight != null &&
                        isOpposite(downRight, upLeft) && isOpposite(downLeft, upRight)
                    ) {
                        count++
                    }
                }
            }
        }

        println(count)
    }
}