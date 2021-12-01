package ru.tms.customviewtest

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class RevertTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {

    companion object {
        private const val DEFAULT_ROTATION = 180f
    }

    var isRevert = false
    set(revert) {
        field = revert
        resolveReversState()
    }

    private var isHeadUp = false
    private var defaultText: CharSequence? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.RevertTextView, 0, 0).apply {
            isRevert = getBoolean(R.styleable.RevertTextView_revert, false)
            isHeadUp = getBoolean(R.styleable.RevertTextView_headUp, false)
            defaultText = text
        }
        resolveReversState()
        resolveHeadUp()
    }

    private fun resolveReversState() {
        text = if (isRevert) {
            text.reversed()
        } else {
            defaultText
        }
        invalidate()
    }

    private fun resolveHeadUp() {
        if (isHeadUp) {
            rotation = DEFAULT_ROTATION
        }

    }
}