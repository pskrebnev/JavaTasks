package org.smalltasks

import kotlin.collections.component1
import kotlin.collections.component2

fun main() {
    val text =
        ("In today’s fast-evolving software landscape, delivering high-quality applications at scale is a constant challenge. Traditional API testing often involves writing complex scripts, maintaining boilerplate code, and dealing with brittle test suites. Enter Playwright MCP (Model Context Protocol) combined with Large Language Models (LLMs) — a revolutionary duo that simplifies API automation by enabling testers to write tests in plain English, reducing coding overhead, and enhancing test efficiency.\n"
                + "In this blog, we’ll explore how Playwright MCP, powered by LLMs like Claude, transforms API automation, with practical examples drawn from the ExecuteAutomation Playwright MCP repository.\n"
                + "\uD83D\uDC4CYou’ll be amazed at how smart the Playwright MCP server is.\n"
                + "For example, when making a PUT request, it doesn’t just update the data — it also shows you the before and after values, giving you full visibility into what changed. A great feature for debugging and validation!\n"
                + "Why Modern API Automation Needs a New Approach\n"
                + "API testing is critical for ensuring seamless communication between application components. However, traditional tools like Postman, RestAssured, or even Playwright’s native API testing capabilities come with challenges:\n"
                + "Code-Heavy Scripts: Writing and maintaining test scripts requires significant coding expertise.\n"
                + "Dynamic APIs: Modern APIs with complex payloads or authentication mechanisms (e.g., OAuth) are hard to test consistently.\n"
                + "Flaky Tests: Environmental issues or timing problems often lead to unreliable test results.\n"
                + "Accessibility: Non-technical team members, like QA analysts, struggle to contribute to test creation.\n"
                + "Playwright MCP, integrated with LLMs, addresses these pain points by enabling natural language-driven testing, where you describe test scenarios in plain text, and the AI translates them into executable API calls. This approach democratizes testing, boosts productivity, and aligns with the shift toward AI-driven development workflows.");
    val cleanedText = cleanText(text);
    val wordsCount = countWords(cleanedText);
    wordsCount.forEach { (word, num) ->
        println(
            "$word -> $num"
        )
    }
}

private fun countWords(str: String): Map<String, Long> {
    return str.split(" ")
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }
        .entries
        .sortedByDescending { it.value }
        .filter { it.value > 2 }
        .associate { it.key to it.value }
}

private fun cleanText(st: String): String {
    return st.filter { it.isLetter() || it == ' ' }
        .lowercase()
}

