package org.example

import kotlin.random.Random
import kotlin.math.*

open class Human(
    protected val fullName: String,
    val age: Int,
    currentSpeed: Double,
    protected var x: Double = 0.0,
    protected var y: Double = 0.0
) : Movable {

    protected var currentSpeed: Double = currentSpeed
        set(value) {
            if (value >= 0) {
                field = value
            }
        }

    override fun move(timeStep: Double) {
        val randomAngle = Random.nextDouble(0.0, 360.0)
        val distance = this.currentSpeed * timeStep
        val radians = Math.toRadians(randomAngle)
        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)
        x += dx
        y += dy

        println("${fullName} made a random step.")
        println(" -> New coordinates: ($x, $y)")
    }
}