package dev.vespertine.satoshilaw

import android.net.ParseException
import android.text.Spanned
import android.text.InputFilter
import java.util.regex.Pattern
import android.text.Selection.getSelectionStart
import android.text.Editable
import android.widget.EditText
import android.text.TextWatcher
import java.io.Serializable
import java.text.DecimalFormat
import java.time.Duration


data class Project(
    val id: Int,
    val projectName: String,
    val clientName: String,
    val rate : Double,
    var duration: Int,
    val startTime: Int,
    val endTime: Int,
    val tasksList : MutableList<Task> = mutableListOf()
) : Serializable {
    val startString = if (startTime <= 12) "$startTime:00 AM" else "${startTime -12}:00 AM"
    val endString = if (endTime <= 12) "$endTime:00 AM" else "${endTime -12}:00 AM"
}

data class Task(
    val taskName : String,
    val taskDuration: Int
) : Serializable

class InputFilterMinMax : InputFilter {

    private var min: Int = 0
    private var max: Int = 0

    constructor(min: Int, max: Int) {
        this.min = min
        this.max = max
    }

    constructor(min: String, max: String) {
        this.min = Integer.parseInt(min)
        this.max = Integer.parseInt(max)
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = Integer.parseInt(dest.toString() + source.toString())
            if (isInRange(min, max, input))
                return null
        } catch (nfe: NumberFormatException) {
        }

        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}


class NumberTextWatcher(private val et: EditText, pattern: String) : TextWatcher {

    private val df: DecimalFormat
    private val dfnd: DecimalFormat
    private var hasFractionalPart: Boolean = false
    private var trailingZeroCount: Int = 0

    init {
        df = DecimalFormat(pattern)
        df.isDecimalSeparatorAlwaysShown = true
        dfnd = DecimalFormat("#,###.00")
        hasFractionalPart = false
    }

    override fun afterTextChanged(s: Editable?) {
        et.removeTextChangedListener(this)

        if (s != null && !s.toString().isEmpty()) {
            try {
                val inilen: Int
                val endlen: Int
                inilen = et.text.length
                val v = s.toString().replace(df.decimalFormatSymbols.groupingSeparator.toString(), "").replace("$", "")
                val n = df.parse(v)
                val cp = et.selectionStart
                if (hasFractionalPart) {
                    val trailingZeros = StringBuilder()
                    while (trailingZeroCount-- > 0)
                        trailingZeros.append('0')
                    et.setText(df.format(n) + trailingZeros.toString())
                } else {
                    et.setText(dfnd.format(n))
                }
                et.setText("$" + et.text.toString())
                endlen = et.text.length
                val sel = cp + (endlen - inilen)
                if (sel > 0 && sel < et.text.length) {
                    et.setSelection(sel)
                } else if (trailingZeroCount > -1) {
                    et.setSelection(et.text.length - 3)
                } else {
                    et.setSelection(et.text.length)
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }

        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var index = s.toString().indexOf(df.decimalFormatSymbols.decimalSeparator.toString())
        trailingZeroCount = 0
        if (index > -1) {
            index++
            while (index < s.length) {
                if (s[index] == '0')
                    trailingZeroCount++
                else {
                    trailingZeroCount = 0
                }
                index++
            }
            hasFractionalPart = true
        } else {
            hasFractionalPart = false
        }
    }
}

