package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.components.PortalButton
import com.example.ui.components.ScreenTwoBackground
import com.example.ui.theme.AccentBlue
import com.example.ui.theme.MapsGreen

/**
 * Segunda tela do aplicativo:
 * - Dois botões centrais: Google Maps e Retornar à Tela Inicial
 * - Background exclusivo temático e minimalista com tons esmeralda/oceânicos
 */
@Composable
fun ScreenTwo(
    onOpenMaps: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_two")
    ) {
        ScreenTwoBackground(isDark = isDark)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 440.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Minimalist Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isDark) MapsGreen.copy(alpha = 0.16f) else MapsGreen.copy(alpha = 0.10f),
                    border = BorderStroke(1.dp, MapsGreen.copy(alpha = 0.35f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(6.dp),
                            shape = CircleShape,
                            color = MapsGreen
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.screen_two_badge),
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MapsGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title & Subtitle
                Text(
                    text = stringResource(R.string.screen_two_title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.screen_two_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Botão 1: Google Maps
                PortalButton(
                    title = stringResource(R.string.btn_maps_title),
                    subtitle = stringResource(R.string.btn_maps_desc),
                    icon = Icons.Default.LocationOn,
                    accentColor = MapsGreen,
                    trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                    testTag = "button_maps",
                    onClick = onOpenMaps,
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Botão 2: Retornar à Tela Inicial
                PortalButton(
                    title = stringResource(R.string.btn_back_home_title),
                    subtitle = stringResource(R.string.btn_back_home_desc),
                    icon = Icons.Default.Home,
                    accentColor = AccentBlue,
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    testTag = "button_back_home",
                    onClick = onBackToHome,
                    isDark = isDark
                )
            }
        }
    }
}
