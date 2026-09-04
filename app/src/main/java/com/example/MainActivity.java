package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.browser.customtabs.CustomTabsIntent;

/**
 * Tela Inicial do aplicativo (Implementação Java):
 * - Apresenta dois botões principais centralizados e arredondados: Google e YouTube.
 * - Inclui opção clara para navegar até a Segunda Tela.
 * - Garante retorno seguro aos serviços externos sem encerrar ou reiniciar o app.
 */
public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoogle = findViewById(R.id.btn_google);
        Button btnYoutube = findViewById(R.id.btn_youtube);
        Button btnToSecondScreen = findViewById(R.id.btn_to_second_screen);

        // Abre o Google
        btnGoogle.setOnClickListener(v -> openExternalUrl("https://www.google.com"));

        // Abre o YouTube
        btnYoutube.setOnClickListener(v -> openExternalUrl("https://www.youtube.com"));

        // Navega para a Segunda Tela
        btnToSecondScreen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    private void openExternalUrl(String url) {
        try {
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                    .setShowTitle(true)
                    .build();
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            customTabsIntent.launchUrl(this, Uri.parse(url));
        } catch (Exception e) {
            try {
                Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                fallbackIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(fallbackIntent);
            } catch (Exception ex) {
                Toast.makeText(this, "Não foi possível abrir o link solicitado.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
