package com.example.pokeapi.base

import android.content.Context
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import javax.inject.Inject

    fun nameToId(name: String, defType: String,c: Context): Int {
        val colorId = c.resources.getIdentifier(
            name,
            defType,
            c.packageName
        )
        return colorId
    }
    fun Bitmap.convertToByteArray(): ByteArray = ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, 50, this)
    }.toByteArray()
