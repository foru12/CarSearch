package com.example.testpizza.ui.dialogs

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText


class TextWatcher(val edList: Array<EditText>, val v: Button) :
    TextWatcher {


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {
        for (editText in edList) {
            if (editText.text.toString().trim { it <= ' ' }.length <= 0) {
                v.isEnabled = false
                break
            } else v.isEnabled = true
        }


    }
}