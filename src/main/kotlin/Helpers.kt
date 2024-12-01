package org.gatlin

class Helpers{
    companion object{
        fun readFileFromResources(fileName: String): String {
            val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
                ?: throw IllegalArgumentException("File $fileName not found in resources")
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}