package search

import java.io.File

object DataGenerator {
    fun readFromFile(fileName: String): List<String> {
        return File(fileName).readLines()
    }

    fun generatePeople(): List<String> {
        println("Enter the number of people:")
        val nPeople = readln().toInt()
        println("Enter all people:")
        return List(nPeople) { readln() }
    }
}