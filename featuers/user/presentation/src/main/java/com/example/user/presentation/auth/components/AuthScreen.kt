package com.example.user.presentation.auth.components

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.altabib.design.R
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.FormatCompose
import com.example.signin.GoogleSignInHelper
import com.example.user.domain.entities.User
import com.example.user.presentation.auth.AuthAction
import com.example.user.presentation.auth.AuthState

@Composable
fun AuthScreen(
    state: AuthState,
    user: User,
    onAction: (AuthAction) -> Unit
) {
    val errorMsg = getLocalizedString(R.string.error_request_timeout)

    val context = LocalContext.current
    val helper = remember { GoogleSignInHelper(context) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        try {
            val idToken = helper.getToken(result.data)
            if (idToken != null) {
                onAction(AuthAction.OnGoogleSignIn(user, idToken))
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                errorMsg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.appointment))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    FormatCompose {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LottieAnimation(composition = composition, progress = { progress })
                }

                Text(
                    text = getLocalizedString(R.string.intro_msg),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            // Google Sign-In Button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Button(
                    onClick = {
                        launcher.launch(helper.getSignInIntent())
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = getLocalizedString(R.string.google_sign_in),
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            // Loading overlay
            if (state.isLoading) {
                Loading()
            }
        }
    }
}
