package search

internal class SearchEngine(private val data: Iterable<String>) {
    private var invertedIndexes: Map<String, List<Int>> = createInvertedIndexes()

    fun search(query: String, strategy: String): Set<String> {
        val words = query.split(" ")
        var searchResults = mutableSetOf<String>()
        when (strategy) {
            SearchStrategy.ALL.name -> {
                words.forEach { word ->
                    val wordMatch = search(word)
                    if (wordMatch.isEmpty()) {
                        return emptySet()
                    }
                    if (searchResults.isNotEmpty()) {
                        searchResults = searchResults.intersect(wordMatch.toSet()).toMutableSet()
                    } else {
                        searchResults.addAll(wordMatch)
                    }
                }
                return searchResults
            }

            SearchStrategy.ANY.name -> {
                words.forEach { word -> searchResults.addAll(search(word)) }
                return searchResults
            }

            SearchStrategy.NONE.name -> {
                words.forEach { word -> searchResults.addAll(search(word)) }
                return data.toSet().subtract(searchResults)
            }

            else -> return emptySet()
        }
    }

    private fun search(word: String): List<String> {
        val indexes = invertedIndexes[word.lowercase()]
        return if (indexes.isNullOrEmpty()) {
            emptyList()
        } else {
            indexes.map { data.elementAt(it) }
        }
    }

    private fun createInvertedIndexes(): Map<String, List<Int>> {
        val indexes = mutableMapOf<String, List<Int>>()
        data.forEach {
            val words = it.lowercase().split(" ")
            words.forEach { word ->
                if (indexes.containsKey(word)) {
                    indexes[word] = indexes[word]!! + listOf(data.indexOf(it))
                } else {
                    indexes[word] = listOf(data.indexOf(it))
                }
            }
        }
        return indexes
    }
}