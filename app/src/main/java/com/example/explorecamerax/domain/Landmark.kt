package com.example.explorecamerax.domain

data class Landmark(val name: String, val precision: Float) {
    companion object {
        val EMPTY = Landmark(name = "", precision = 0f)
    }
}
