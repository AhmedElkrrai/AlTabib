package com.example.altabib.featuers.dashboard.domain.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.altabib.R
import com.example.altabib.utils.getLocalizedString

enum class Specialization(
    val key: String,
    val nameResource: Int,
    val icon: ImageVector
) {
    GENERAL_PRACTICE("general_practice", R.string.general_practice, Icons.Filled.Person),
    PEDIATRICS("pediatrics", R.string.pediatrics, Icons.Filled.MedicalServices),
    CARDIOLOGY("cardiology", R.string.cardiology, Icons.Filled.Favorite),
    DERMATOLOGY("dermatology", R.string.dermatology, Icons.Filled.HealthAndSafety),
    ORTHOPEDICS("orthopedics", R.string.orthopedics, Icons.Filled.FitnessCenter),
    GYNECOLOGY("gynecology", R.string.gynecology, Icons.Filled.LocalHospital),
    OPHTHALMOLOGY("ophthalmology", R.string.ophthalmology, Icons.Filled.Visibility),
    PSYCHIATRY("psychiatry", R.string.psychiatry, Icons.Filled.Psychology),
    GASTROENTEROLOGY("gastroenterology", R.string.gastroenterology, Icons.Filled.Palette),
    NEUROLOGY("neurology", R.string.neurology, Icons.Filled.Science),
    ENDOCRINOLOGY("endocrinology", R.string.endocrinology, Icons.Filled.Palette),
    UROLOGY("urology", R.string.urology, Icons.Filled.LocalHospital),
    ONCOLOGY("oncology", R.string.oncology, Icons.Filled.MedicalServices),
    OTOLARYNGOLOGIES("otolaryngologies", R.string.otolaryngologies, Icons.Filled.MedicalServices),
    DENTISTRY("dentistry", R.string.dentistry, Icons.Filled.MedicalServices),
    RADIOLOGY("radiology", R.string.radiology, Icons.Filled.Science);

    companion object {
        fun fromKey(key: String): Specialization =
            entries.find { it.key == key } ?: GENERAL_PRACTICE
    }
}

@Composable
fun Specialization.getDisplayName(): String {
   return getLocalizedString(nameResource)
}
