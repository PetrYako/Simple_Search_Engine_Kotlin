package search

import java.nio.file.Paths

fun main(args: Array<String>) {
    val fileName = args[1]

    // For local debugging
    //val projectDirectory = Paths.get("").toAbsolutePath().fileName
    //val data = DataGenerator.readFromFile("./$projectDirectory/task/src/search/$fileName")

    val data = DataGenerator.readFromFile(fileName)

    val app = SearchApp(data)
    app.menu()
}
