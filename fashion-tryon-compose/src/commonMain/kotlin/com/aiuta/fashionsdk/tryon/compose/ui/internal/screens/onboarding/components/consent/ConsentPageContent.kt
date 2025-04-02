package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.consent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalRippleConfiguration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.updateMandatoryAgreementState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.updateSupplementAgreementState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.CenterAlignmentLine
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.buildAnnotatedStringFromHtml
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.createCenterAlignmentLine
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

@Composable
internal fun ConsentPageContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val theme = LocalTheme.current

    val consentStandaloneFeature = strictProvideFeature<AiutaConsentStandaloneOnboardingPage>()
    val supplementaryPoints =
        remember {
            consentStandaloneFeature.strings.optionalConsentsHtml.take(5)
        }

    sendPageEvent(pageId = AiutaAnalyticPageId.CONSENT)

    LazyColumn(
        modifier =
        modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 8.dp),
    ) {
        item(
            key = "CONSENT_HEADER",
            contentType = { "CONSENT_HEADER_TYPE" },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = consentStandaloneFeature.strings.consentTitle,
                style = theme.typography.titleL,
                color = theme.colors.primary,
                textAlign = TextAlign.Start,
            )

            Spacer(Modifier.height(18.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text =
                buildAnnotatedStringFromHtml(
                    consentStandaloneFeature.strings.consentDescriptionHtml,
                ),
                style = theme.typography.regular,
                color = theme.colors.primary,
                textAlign = TextAlign.Start,
            )

            Spacer(Modifier.height(28.dp))
        }

        item(
            key = "CONSENT_MANDATORY_POINT",
            contentType = { "CONSENT_MANDATORY_POINT_TYPE" },
        ) {
            AgreePoint(
                modifier = Modifier.fillMaxWidth(),
                text = consentStandaloneFeature.strings.mandatoryConsentHtml,
                isAgreementChecked = onboardingController.isMandatoryAgreementChecked.value,
                onAgreementCheckedChange = onboardingController::updateMandatoryAgreementState,
            )
        }

        consentStandaloneFeature.dataProvider?.let {
            itemsIndexed(
                items = supplementaryPoints,
                key = { _, point -> point },
                contentType = { index, point -> "CONSENT_SUPPLEMENTARY_POINT_TYPE_$index-$point" },
            ) { index, point ->
                if (index == 0) {
                    Spacer(Modifier.height(28.dp))
                }

                AgreePoint(
                    modifier = Modifier.fillMaxWidth(),
                    text = point,
                    isAgreementChecked = onboardingController.supplementPointsMap[point] ?: false,
                    onAgreementCheckedChange = { newState ->
                        onboardingController.updateSupplementAgreementState(
                            point = point,
                            newState = newState,
                        )
                    },
                )

                if (index != supplementaryPoints.lastIndex) {
                    Spacer(Modifier.height(40.dp))
                }
            }
        }

        consentStandaloneFeature.strings.consentFooterHtml?.let { footerText ->
            item(
                key = "CONSENT_FOOTER",
                contentType = { "CONSENT_FOOTER" },
            ) {
                Spacer(Modifier.height(28.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedStringFromHtml(footerText),
                    style = theme.typography.regular,
                    color = theme.colors.primary,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AgreePoint(
    modifier: Modifier = Modifier,
    text: String,
    isAgreementChecked: Boolean,
    onAgreementCheckedChange: (Boolean) -> Unit,
) {
    val theme = LocalTheme.current

    val lineIndexToCenter = 0
    var topTextPosition by remember { mutableFloatStateOf(0f) }
    var bottomTextPosition by remember { mutableFloatStateOf(0f) }

    Row(
        modifier = modifier,
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            Checkbox(
                modifier =
                Modifier
                    .size(20.dp)
                    .alignBy(CenterAlignmentLine)
                    .createCenterAlignmentLine(),
                checked = isAgreementChecked,
                onCheckedChange = onAgreementCheckedChange,
                colors =
                CheckboxDefaults.colors(
                    checkedColor = theme.colors.brand,
                    uncheckedColor = theme.colors.neutral,
                    checkmarkColor = theme.colors.onDark,
                ),
            )
        }

        Spacer(Modifier.width(16.dp))

        Text(
            modifier =
            Modifier
                .weight(1f)
                .clickableUnindicated {
                    onAgreementCheckedChange(!isAgreementChecked)
                }
                .alignBy(CenterAlignmentLine)
                .createCenterAlignmentLine(topTextPosition, bottomTextPosition),
            text = text,
            style = theme.typography.regular,
            color = theme.colors.primary,
            textAlign = TextAlign.Start,
            onTextLayout = { textLayout: TextLayoutResult ->
                topTextPosition = textLayout.getLineTop(lineIndexToCenter)
                bottomTextPosition = textLayout.getLineBottom(lineIndexToCenter)
            },
        )
    }
}
