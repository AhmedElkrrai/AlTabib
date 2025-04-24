package com.example.altabib.featuers.dashboard.presentation.specialization.models

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.design_system.localization.getLocalizedString

enum class Specialization(
    val key: String,
    val nameResource: Int,
    val icon: Int
) {
    GENERAL_PRACTICE("general_practice", R.string.general_practice, R.drawable.general_practice),
    GASTROENTEROLOGY("gastroenterology", R.string.gastroenterology, R.drawable.gastroenterology),
    PEDIATRICS("pediatrics", R.string.pediatrics, R.drawable.pediatrics),
    GYNECOLOGY("gynecology", R.string.gynecology, R.drawable.gynecology),
    OTOLARYNGOLOGIES("otolaryngologies", R.string.otolaryngologies, R.drawable.otolaryngologies),
    OPHTHALMOLOGY("ophthalmology", R.string.ophthalmology, R.drawable.ophthalmology),
    DENTISTRY("dentistry", R.string.dentistry, R.drawable.dentistry),
    CARDIOLOGY("cardiology", R.string.cardiology, R.drawable.cardiology),
    DERMATOLOGY("dermatology", R.string.dermatology, R.drawable.dermatology),
    ORTHOPEDICS("orthopedics", R.string.orthopedics, R.drawable.orthopedics),
    PSYCHIATRY("psychiatry", R.string.psychiatry, R.drawable.psychiatry),
    ENDOCRINOLOGY("endocrinology", R.string.endocrinology, R.drawable.endocrinology),
    UROLOGY("urology", R.string.urology, R.drawable.urology),
    ONCOLOGY("oncology", R.string.oncology, R.drawable.oncology),
    RADIOLOGY("radiology", R.string.radiology, R.drawable.radiology);

    companion object {
        @Composable
        fun displayName(key: String?): String {
            val specialization = entries.find { it.key == key } ?: GENERAL_PRACTICE
            return getLocalizedString(specialization.nameResource)
        }
    }
}
