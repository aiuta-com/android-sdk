package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

@Composable
internal fun buildAnnotatedStringFromHtml(
    input: String,
    isClickable: Boolean = true,
): AnnotatedString =
    buildAnnotatedString {
        // TODO
        append(input)
    }

// {
//    val spannableString = SpannableStringBuilder(input).toString()
//    val spanned = HtmlCompat.fromHtml(spannableString, HtmlCompat.FROM_HTML_MODE_COMPACT)
//
//    return spanned.toAnnotatedString(isClickable)
// }

// internal fun Spanned.toAnnotatedString(isClickable: Boolean = true): AnnotatedString =
//    buildAnnotatedString {
//        val spanned = this@toAnnotatedString
//        append(spanned.toString())
//        getSpans(0, spanned.length, Any::class.java).forEach { span ->
//            val start = getSpanStart(span)
//            val end = getSpanEnd(span)
//            when (span) {
//                is URLSpan -> {
//                    if (isClickable) {
//                        addLink(
//                            url = LinkAnnotation.Url(url = span.url),
//                            start = start,
//                            end = end,
//                        )
//                    }
//                }
//
//                is StyleSpan -> {
//                    when (span.style) {
//                        Typeface.BOLD ->
//                            addStyle(
//                                SpanStyle(fontWeight = FontWeight.Bold),
//                                start,
//                                end,
//                            )
//
//                        Typeface.ITALIC ->
//                            addStyle(
//                                SpanStyle(fontStyle = FontStyle.Italic),
//                                start,
//                                end,
//                            )
//
//                        Typeface.BOLD_ITALIC ->
//                            addStyle(
//                                SpanStyle(
//                                    fontWeight = FontWeight.Bold,
//                                    fontStyle = FontStyle.Italic,
//                                ),
//                                start,
//                                end,
//                            )
//                    }
//                }
//
//                is UnderlineSpan ->
//                    addStyle(
//                        SpanStyle(textDecoration = TextDecoration.Underline),
//                        start,
//                        end,
//                    )
//
//                is ForegroundColorSpan ->
//                    addStyle(
//                        SpanStyle(color = Color(span.foregroundColor)),
//                        start,
//                        end,
//                    )
//            }
//        }
//    }
