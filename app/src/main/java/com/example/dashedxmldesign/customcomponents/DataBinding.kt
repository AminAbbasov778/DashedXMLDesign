package com.example.dashedxmldesign.customcomponents

import android.widget.TextView
import androidx.databinding.BindingAdapter
import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.core.content.res.ResourcesCompat
import com.example.dashedxmldesign.R

object DataBinding {

    @BindingAdapter(value = ["mixedText", "semiboldParts"], requireAll = false)
    @JvmStatic
    fun setMixedFontText(
        textView: TextView,
        fullText: String?,
        semiboldParts: String?
    ) {
        if (fullText == null) return

        val spannable = SpannableStringBuilder(fullText)

        val bold = ResourcesCompat.getFont(textView.context, R.font.inter_bold)
        val semibold = ResourcesCompat.getFont(textView.context, R.font.inter_medium)

        spannable.setSpan(
            CustomTypefaceSpan(semibold),
            0,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        semiboldParts?.split("|")?.forEach { part ->
            val trimmedPart = part.trim()
            val startIndex = fullText.indexOf(trimmedPart)
            if (startIndex != -1) {
                spannable.setSpan(
                    CustomTypefaceSpan(bold),
                    startIndex,
                    startIndex + trimmedPart.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        textView.text = spannable
    }
}
