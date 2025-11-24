package org.example

fun main() {
    val timeStep = 1.0
    val totalTime = 5.0

    val people = listOf(
        Human("Ivanov I.I.", 25, 1.0),
        Human("Petrov P.P.", 30, 1.5),
        Human("Sidorov A.V.", 22, 0.8),
        Driver("Kozlov D.M.", 40, 5.0)
    )

    println("--- SIMULATION OF MOVEMENT (Inheritance and Threads) ---")

    var currentTime = 0.0

    while (currentTime < totalTime) {
        println("\n[Time: ${currentTime.toInt() + 1} sec.]")

        val threads = people.map { person ->
            Thread {
                person.move(timeStep)
            }
        }

        threads.forEach { it.start() }

        threads.forEach { it.join() }

        currentTime += timeStep
    }

    println("\n--- SIMULATION FINISHED ---")
}