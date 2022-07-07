package com.udacity.shoestore.models

import com.udacity.shoestore.R
import timber.log.Timber
import kotlin.random.Random

object ImagesResources {

    val generator = Random(System.nanoTime())

    private val images = listOf(
        R.drawable.shoe1,
        R.drawable.shoe2,
        R.drawable.shoe3,
        R.drawable.shoe4,
        R.drawable.shoe5,
        R.drawable.shoe6,
        R.drawable.shoe7,
        R.drawable.shoe8
    )

    fun getRandomImageResource(): Int {
        Timber.d("Generating a random number...")
        return images[generator.nextInt(0, images.size)]
    }
}