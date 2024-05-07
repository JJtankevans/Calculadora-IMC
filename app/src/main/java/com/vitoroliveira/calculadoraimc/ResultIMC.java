package com.vitoroliveira.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultIMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_imc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentUserData = getIntent();

        if(intentUserData.hasExtra("userData")) {
            Imc data = intentUserData.getParcelableExtra("userData", Imc.class);

            // Faz o caclulo
            String diagnostico = calculaImc(data.getPeso(), data.getAltura());

            // Inicializa as variáveis
            TextView infoPeso = findViewById(R.id.text_info_peso);
            TextView infoAlture = findViewById(R.id.text_info_altura);
            TextView infoResult = findViewById(R.id.text_result);

            //Seta os valores dos textos
            infoPeso.setText(String.format("Peso informado %.1f KG", data.getPeso()));
            infoAlture.setText(String.format("Peso informado %.2f M", data.getAltura()));
            infoResult.setText(diagnostico);
        }

    }

    private String calculaImc(double peso, double altura) {
        double calc = peso / (altura * altura);
//        Log.i("calc_data", "resultado: " + String.format("%.2f", calc));
        if(calc < 18.5) {
            return "Baixo";
        } if(calc > 18.5 && calc < 24.9) {
            return "Normal";
        } else if (calc >= 25.0 && calc <= 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }
}