package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.browser.customtabs.CustomTabsIntent;

/**
 * Segunda tela do aplicativo (Implementação Java):
 * - Apresenta dois botões centralizados e arredondados: Google Maps e Voltar para o início.
 * - Não possui nenhum botão, seta ou barra no topo: o botão "Voltar para o início" é o único elemento de retorno.
 */
public class SecondActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnMaps = findViewById(R.id.btn_maps);
        Button btnBackHome = findViewById(R.id.btn_back_home);

        // Abre o Google Maps com retorno seguro
        btnMaps.setOnClickListener(v -> openExternalUrl("https://maps.google.com"));

        // Único botão de retorno na interface: finaliza esta Activity e retorna à MainActivity
        btnBackHome.setOnClickListener(v -> finish());
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
