package org.example

fun main() {
    val groupNumber = 2
    val timeStep = 1.0
    val totalTime = 10.0

    val people = listOf(
        Human("Ivanov I.I.", 25, 1.0),
        Human("Petrov P.P.", 30, 1.5)
    )

    println("SIMULATION DVIJENIA (Random Walk)")

    var currentTime = 0.0

    while (currentTime < totalTime) {
        println("\n[Time: ${currentTime.toInt() + 1} sec.]")

        people.forEach { person ->
            person.move(timeStep)
        }

        currentTime += timeStep
    }


    println("\nSIMULATION FINISHED ")
}