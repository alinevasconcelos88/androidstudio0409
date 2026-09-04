package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Custom background for Screen 1 (Hub Principal).
 * Features a minimalist deep obsidian/indigo canvas with subtle luminous radial ambient
 * accents and delicate orbital contour geometry.
 */
@Composable
fun ScreenOneBackground(
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    val topColor = if (isDark) Color(0xFF080C15) else Color(0xFFF1F5F9)
    val bottomColor = if (isDark) Color(0xFF0D1527) else Color(0xFFE2E8F0)
    val auraBlue = if (isDark) Color(0xFF3B82F6).copy(alpha = 0.15f) else Color(0xFF3B82F6).copy(alpha = 0.08f)
    val auraIndigo = if (isDark) Color(0xFF6366F1).copy(alpha = 0.12f) else Color(0xFF6366F1).copy(alpha = 0.06f)
    val ringColor = if (isDark) Color(0xFF38BDF8).copy(alpha = 0.07f) else Color(0xFF0284C7).copy(alpha = 0.05f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(topColor, bottomColor)
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Top-right ambient glowing aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(auraBlue, Color.Transparent),
                    center = Offset(width * 0.85f, height * 0.15f),
                    radius = width * 0.75f
                ),
                radius = width * 0.75f,
                center = Offset(width * 0.85f, height * 0.15f)
            )

            // Center-left ambient indigo glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(auraIndigo, Color.Transparent),
                    center = Offset(width * 0.15f, height * 0.65f),
                    radius = width * 0.85f
                ),
                radius = width * 0.85f,
                center = Offset(width * 0.15f, height * 0.65f)
            )

            // Elegant geometric orbital lines (minimalist accent)
            drawCircle(
                color = ringColor,
                radius = width * 0.45f,
                center = Offset(width * 0.85f, height * 0.15f),
                style = Stroke(width = 1.5f)
            )
            drawCircle(
                color = ringColor,
                radius = width * 0.70f,
                center = Offset(width * 0.85f, height * 0.15f),
                style = Stroke(width = 1.2f)
            )

            // Subtle node points on orbit
            drawCircle(
                color = if (isDark) Color(0xFF60A5FA).copy(alpha = 0.25f) else Color(0xFF2563EB).copy(alpha = 0.15f),
                radius = 3f,
                center = Offset(width * 0.40f, height * 0.15f)
            )
            drawCircle(
                color = if (isDark) Color(0xFF818CF8).copy(alpha = 0.25f) else Color(0xFF4F46E5).copy(alpha = 0.15f),
                radius = 4f,
                center = Offset(width * 0.85f, height * 0.85f)
            )
        }
    }
}

/**
 * Custom background for Screen 2 (Navegação & Exploração).
 * Features a distinct deep oceanic slate and emerald gradient canvas with subtle
 * radar rings and navigation vectors.
 */
@Composable
fun ScreenTwoBackground(
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    val topColor = if (isDark) Color(0xFF051119) else Color(0xFFF0FDF4)
    val bottomColor = if (isDark) Color(0xFF091C24) else Color(0xFFE2E8F0)
    val auraEmerald = if (isDark) Color(0xFF10B981).copy(alpha = 0.16f) else Color(0xFF10B981).copy(alpha = 0.08f)
    val auraCyan = if (isDark) Color(0xFF06B6D4).copy(alpha = 0.12f) else Color(0xFF06B6D4).copy(alpha = 0.06f)
    val radarRingColor = if (isDark) Color(0xFF34D399).copy(alpha = 0.07f) else Color(0xFF059669).copy(alpha = 0.05f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(topColor, bottomColor)
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Top-center exploration aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(auraEmerald, Color.Transparent),
                    center = Offset(width * 0.5f, height * 0.20f),
                    radius = width * 0.8f
                ),
                radius = width * 0.8f,
                center = Offset(width * 0.5f, height * 0.20f)
            )

            // Bottom-right cyan aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(auraCyan, Color.Transparent),
                    center = Offset(width * 0.9f, height * 0.8f),
                    radius = width * 0.7f
                ),
                radius = width * 0.7f,
                center = Offset(width * 0.9f, height * 0.8f)
            )

            // Minimalist radar/contour circles
            val radarCenter = Offset(width * 0.5f, height * 0.20f)
            drawCircle(
                color = radarRingColor,
                radius = width * 0.30f,
                center = radarCenter,
                style = Stroke(width = 1.2f)
            )
            drawCircle(
                color = radarRingColor,
                radius = width * 0.55f,
                center = radarCenter,
                style = Stroke(width = 1.2f)
            )
            drawCircle(
                color = radarRingColor,
                radius = width * 0.80f,
                center = radarCenter,
                style = Stroke(width = 1.0f)
            )

            // Subtle crosshair tick marks for navigation feel
            val tickColor = if (isDark) Color(0xFF34D399).copy(alpha = 0.15f) else Color(0xFF059669).copy(alpha = 0.12f)
            drawLine(
                color = tickColor,
                start = Offset(radarCenter.x - 16f, radarCenter.y),
                end = Offset(radarCenter.x + 16f, radarCenter.y),
                strokeWidth = 1.5f
            )
            drawLine(
                color = tickColor,
                start = Offset(radarCenter.x, radarCenter.y - 16f),
                end = Offset(radarCenter.x, radarCenter.y + 16f),
                strokeWidth = 1.5f
            )
        }
    }
}
