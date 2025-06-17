package org.smalltasks

class FindPalindromes {
    fun findAllPalindromes(st: String, minLength: Int = 3): Set<String> {
        val palindromes = mutableSetOf<String>();
        val cleanText = st.lowercase().replace(
            Regex("[^a-z0-9]"), ""
        )

        // Find all substrings and check if they're palindromes
        for (i in cleanText.indices) {
            for (j in i + minLength - 1 until cleanText.length) {
                val substring = cleanText.substring(i, j + 1)
                if (isPalindrome(substring)) {
                    palindromes.add(substring)
                }
            }
        }

        return palindromes;
    }

    fun isPalindrome(s: String): Boolean {
        return s == s.reversed();
    }
}

fun main() {
    val finder = FindPalindromes()
    val longString = "A man, a plan, a canal: Panama! Racecar and madam went to see Hannah. Was it a car or a cat I saw?"
    println("=== Basic Palindrome Search ===")
    val palindromes = finder.findAllPalindromes(longString)
    palindromes.sorted().forEach { println("'$it'") }
}


