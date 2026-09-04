package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.screens.ScreenOne
import com.example.ui.screens.ScreenTwo
import com.example.ui.theme.MyApplicationTheme

enum class PortalScreen {
  HOME,
  EXPLORE
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        PortalApp()
      }
    }
  }
}

@Composable
fun PortalApp() {
  val context = LocalContext.current
  var currentScreen by rememberSaveable { mutableStateOf(PortalScreen.HOME) }

  // Intercept back navigation when on second screen
  BackHandler(enabled = currentScreen == PortalScreen.EXPLORE) {
    currentScreen = PortalScreen.HOME
  }

  Surface(modifier = Modifier.fillMaxSize()) {
    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
          if (targetState == PortalScreen.EXPLORE) {
            (slideInHorizontally(
                animationSpec = tween(320),
                initialOffsetX = { fullWidth -> fullWidth }
            ) + fadeIn(animationSpec = tween(280))).togetherWith(
                slideOutHorizontally(
                    animationSpec = tween(320),
                    targetOffsetX = { fullWidth -> -fullWidth / 3 }
                ) + fadeOut(animationSpec = tween(220))
            )
          } else {
            (slideInHorizontally(
                animationSpec = tween(320),
                initialOffsetX = { fullWidth -> -fullWidth / 3 }
            ) + fadeIn(animationSpec = tween(280))).togetherWith(
                slideOutHorizontally(
                    animationSpec = tween(320),
                    targetOffsetX = { fullWidth -> fullWidth }
                ) + fadeOut(animationSpec = tween(220))
            )
          }
        },
        label = "screen_transition"
    ) { screen ->
      when (screen) {
        PortalScreen.HOME -> {
          ScreenOne(
              onOpenGoogle = {
                openExternalUri(context, "https://www.google.com")
              },
              onOpenYouTube = {
                openExternalUri(context, "https://www.youtube.com")
              },
              onNavigateToScreenTwo = {
                currentScreen = PortalScreen.EXPLORE
              }
          )
        }
        PortalScreen.EXPLORE -> {
          ScreenTwo(
              onOpenMaps = {
                openExternalUri(context, "https://maps.google.com")
              },
              onBackToHome = {
                currentScreen = PortalScreen.HOME
              }
          )
        }
      }
    }
  }
}

/**
 * Safely dispatches an external link.
 * Uses Android Custom Tabs if available, providing a built-in top close/back button ('X' / arrow)
 * so the user can easily exit the external site and return directly to the exact state of the app
 * without restarting or getting lost.
 * Gracefully falls back to standard ACTION_VIEW if Custom Tabs are unavailable.
 */
fun openExternalUri(context: Context, url: String) {
  try {
    val uri = Uri.parse(url)
    val customTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()

    customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    customTabsIntent.launchUrl(context, uri)
  } catch (e: Exception) {
    try {
      val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      context.startActivity(fallbackIntent)
    } catch (fallbackException: Exception) {
      Toast.makeText(context, "Não foi possível abrir o link solicitado.", Toast.LENGTH_SHORT).show()
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ScreenOnePreview() {
  MyApplicationTheme {
    ScreenOne(
        onOpenGoogle = {},
        onOpenYouTube = {},
        onNavigateToScreenTwo = {}
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
  MyApplicationTheme {
    ScreenTwo(
        onOpenMaps = {},
        onBackToHome = {}
    )
  }
}

