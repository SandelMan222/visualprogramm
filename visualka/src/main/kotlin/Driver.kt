package org.example

class Driver(
    fullName: String,
    age: Int,
    currentSpeed: Double,
    x: Double = 0.0,
    y: Double = 0.0
) : Human(fullName, age, currentSpeed, x, y) {

    private val fixedAngle = 0.0

    override fun move(timeStep: Double) {

        val distance = currentSpeed * timeStep

        val radians = Math.toRadians(fixedAngle)

        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        x += dx
        y += dy

        println("Driver ${fullName} made a straight step.")
        println(" -> New coordinates: ($x, $y)")
    }
}