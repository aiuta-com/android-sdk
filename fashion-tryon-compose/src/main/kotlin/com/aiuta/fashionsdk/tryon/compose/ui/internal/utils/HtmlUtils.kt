package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat

internal fun buildAnnotatedStringFromHtml(
    input: String,
    isClickable: Boolean = true,
): AnnotatedString {
    val spannableString = SpannableStringBuilder(input).toString()
    val spanned = HtmlCompat.fromHtml(spannableString, HtmlCompat.FROM_HTML_MODE_COMPACT)

    return spanned.toAnnotatedString(isClickable)
}

internal fun Spanned.toAnnotatedString(isClickable: Boolean = true): AnnotatedString =
    buildAnnotatedString {
        val spanned = this@toAnnotatedString
        append(spanned.toString())
        getSpans(0, spanned.length, Any::class.java).forEach { span ->
            val start = getSpanStart(span)
            val end = getSpanEnd(span)
            when (span) {
                is URLSpan -> {
                    if (isClickable) {
                        addLink(
                            url = LinkAnnotation.Url(url = span.url),
                            start = start,
                            end = end,
                        )
                    }
                }

                is StyleSpan -> {
                    when (span.style) {
                        Typeface.BOLD ->
                            addStyle(
                                SpanStyle(fontWeight = FontWeight.Bold),
                                start,
                                end,
                            )

                        Typeface.ITALIC ->
                            addStyle(
                                SpanStyle(fontStyle = FontStyle.Italic),
                                start,
                                end,
                            )

                        Typeface.BOLD_ITALIC ->
                            addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                ),
                                start,
                                end,
                            )
                    }
                }

                is UnderlineSpan ->
                    addStyle(
                        SpanStyle(textDecoration = TextDecoration.Underline),
                        start,
                        end,
                    )

                is ForegroundColorSpan ->
                    addStyle(
                        SpanStyle(color = Color(span.foregroundColor)),
                        start,
                        end,
                    )
            }
        }
    }
