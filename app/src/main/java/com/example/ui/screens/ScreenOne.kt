package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.components.PortalButton
import com.example.ui.components.ScreenOneBackground
import com.example.ui.theme.AccentBlue
import com.example.ui.theme.AccentIndigo
import com.example.ui.theme.GoogleBlue
import com.example.ui.theme.YouTubeRed

/**
 * Primeira tela do aplicativo:
 * - Dois botões centrais: Google e YouTube
 * - Opção de navegação para a segunda tela
 * - Background exclusivo elegante e minimalista
 */
@Composable
fun ScreenOne(
    onOpenGoogle: () -> Unit,
    onOpenYouTube: () -> Unit,
    onNavigateToScreenTwo: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_one")
    ) {
        ScreenOneBackground(isDark = isDark)

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
                // Minimalist Pill Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isDark) AccentBlue.copy(alpha = 0.16f) else AccentBlue.copy(alpha = 0.10f),
                    border = BorderStroke(1.dp, AccentBlue.copy(alpha = 0.35f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(6.dp),
                            shape = CircleShape,
                            color = AccentBlue
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.screen_one_badge),
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = AccentBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title & Description
                Text(
                    text = stringResource(R.string.screen_one_title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.screen_one_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Botão 1: Google
                PortalButton(
                    title = stringResource(R.string.btn_google_title),
                    subtitle = stringResource(R.string.btn_google_desc),
                    icon = Icons.Default.Search,
                    accentColor = GoogleBlue,
                    trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                    testTag = "button_google",
                    onClick = onOpenGoogle,
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Botão 2: YouTube
                PortalButton(
                    title = stringResource(R.string.btn_youtube_title),
                    subtitle = stringResource(R.string.btn_youtube_desc),
                    icon = Icons.Default.PlayArrow,
                    accentColor = YouTubeRed,
                    trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                    testTag = "button_youtube",
                    onClick = onOpenYouTube,
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Opção de Navegação para a Segunda Tela
                val navInteractionSource = remember { MutableInteractionSource() }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 440.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .testTag("button_nav_second_screen")
                        .clickable(
                            interactionSource = navInteractionSource,
                            indication = androidx.compose.material3.ripple(color = AccentIndigo),
                            role = Role.Button,
                            onClick = onNavigateToScreenTwo
                        ),
                    shape = RoundedCornerShape(20.dp),
                    color = if (isDark) Color(0xFF161F33).copy(alpha = 0.70f) else Color(0xFFF1F5F9).copy(alpha = 0.85f),
                    border = BorderStroke(
                        1.dp,
                        if (isDark) AccentIndigo.copy(alpha = 0.35f) else AccentIndigo.copy(alpha = 0.25f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(R.string.nav_to_screen_two),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.nav_to_screen_two_desc),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Surface(
                            modifier = Modifier.size(32.dp),
                            shape = CircleShape,
                            color = AccentIndigo.copy(alpha = 0.15f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = stringResource(R.string.nav_to_screen_two),
                                    tint = AccentIndigo,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
