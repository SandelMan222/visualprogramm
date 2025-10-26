package org.example

fun main() {
    val timeStep = 1.0 // Wagremja simuljacii (1 sekunda)
    val totalTime = 5.0 // Obshee vremja simuljacii (5 sekund, для краткости)

    // 1. Sozdanie jekzempljarov Human i Driver (3 Human i 1 Driver)
    val people = listOf(
        Human("Ivanov I.I.", 25, 1.0),
        Human("Petrov P.P.", 30, 1.5),
        Human("Sidorov A.V.", 22, 0.8),
        Driver("Kozlov D.M.", 40, 5.0)  // Klass-naslednik Driver
    )

    println("--- SIMULATION OF MOVEMENT (Inheritance and Threads) ---")

    var currentTime = 0.0

    // 2. Osnovnoj cikl "vremeni"
    while (currentTime < totalTime) {
        println("\n[Time: ${currentTime.toInt() + 1} sec.]")

        // --- PARALLEL EXECUTION (Threads) ---

        // Sozdaem potoki (threads) dlja kazhdogo ob'ekta
        val threads = people.map { person ->
            // Thread - prostejshaja realizacija potoka dlja parallelnogo vypolnenija
            Thread {
                person.move(timeStep)
            }
        }

        // Zapuskaem vse potoki
        threads.forEach { it.start() }

        // Zhdem, poka vse potoki zavershat rabotu pered perehodom k sledujushemu shaguvremeni
        threads.forEach { it.join() }

        // --- KONEC PARALLELNOGO VYPOLNENIJA ---

        currentTime += timeStep
    }

    println("\n--- SIMULATION FINISHED ---")
}