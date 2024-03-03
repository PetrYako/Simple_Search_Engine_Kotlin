package search

class SearchApp(private val data: Iterable<String>) {
    private val engine = SearchEngine(data)

    tailrec fun menu() {
        println(
            """
            === Menu ===
            1. Find a person
            2. Print all people
            0. Exit
        """.trimIndent()
        )
        when (readln()) {
            "1" -> {
                findPerson()
                println()
                menu()
            }

            "2" -> {
                listOfPeople()
                println()
                menu()
            }

            "0" -> {
                println("\nBye!\n")
                return
            }

            else -> {
                println("\nIncorrect option! Try again.")
                println()
                menu()
            }
        }
    }

    private fun listOfPeople() {
        println("\n=== List of people ===")
        println(data.joinToString("\n"))
    }

    private fun findPerson() {
        println("\nSelect a matching strategy: ALL, ANY, NONE")
        val strategy = readln()
        println("\nEnter a name or email to search all suitable people.")
        val query = readln()
        val result = engine.search(query, strategy)
        if (result.isEmpty()) {
            println("No matching people found.")
        } else {
            println("\n${result.size} persons found:")
            println(result.joinToString("\n"))
        }
    }
}