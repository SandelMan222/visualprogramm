package org.example

import kotlin.random.Random

open class Human(
    protected val fullName: String,
    private val age: Int,
    protected var currentSpeed: Double,
    protected var x: Double = 0.0,
    protected var y: Double = 0.0
) {

    fun getFullName(): String = fullName

    fun getAge(): Int = age

    fun getCurrentSpeed(): Double = currentSpeed

    fun getX(): Double = x

    fun getY(): Double = y

    fun setCurrentSpeed(newSpeed: Double) {
        currentSpeed = newSpeed
    }

    open fun move(timeStep: Double) {
        val randomAngle = Random.nextDouble(0.0, 360.0)

        val distance = currentSpeed * timeStep

        val radians = Math.toRadians(randomAngle)

        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        x += dx
        y += dy

        println("${fullName} made a random step.")
        println(" -> New coordinates: ($x, $y)")
    }
}