package com.example.barbershop.ui.files

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.barbershop.R
import java.lang.reflect.Field

fun changeCursorColor(editText: EditText, context: Context) {
    val colorBlanco = ContextCompat.getColor(context, R.color.white)

    try {
        val editorField: Field = TextView::class.java.getDeclaredField("mEditor")
        editorField.isAccessible = true
        val editor = editorField.get(editText)

        val cursorDrawableField: Field = editor.javaClass.getDeclaredField("mCursorDrawable")
        cursorDrawableField.isAccessible = true
        val cursorDrawables = arrayOfNulls<Drawable>(2)
        cursorDrawables[0] = ContextCompat.getDrawable(context, R.drawable.cursor_blanco)
        cursorDrawables[1] = ContextCompat.getDrawable(context, R.drawable.cursor_blanco)
        cursorDrawables[0]?.setColorFilter(colorBlanco, PorterDuff.Mode.SRC_IN)
        cursorDrawables[1]?.setColorFilter(colorBlanco, PorterDuff.Mode.SRC_IN)
        cursorDrawableField.set(editor, cursorDrawables)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
