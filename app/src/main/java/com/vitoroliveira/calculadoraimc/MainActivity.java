package com.vitoroliveira.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //incializando variáveis
        Button btnCalcular = findViewById(R.id.btn_calcular);
        EditText editAltura = findViewById(R.id.edit_altura);
        EditText editPeso = findViewById(R.id.edit_peso);

        btnCalcular.setOnClickListener(v -> {
            boolean validaCampos = dataIsEmpty(editPeso.getText().toString(), editAltura.getText().toString());

            if(validaCampos) {
                Intent i = new Intent(this, ResultIMC.class);

                double convertedPeso = Double.parseDouble(editPeso.getText().toString());
                double covertedAltura = Double.parseDouble(editAltura.getText().toString());


                Imc userImcData = new Imc(convertedPeso, covertedAltura);
                i.putExtra("userData", userImcData);
                startActivity(i);
            }
        });
    }

    protected Boolean dataIsEmpty(String peso, String altura) {
        TextInputLayout inputLayoutPeso = findViewById(R.id.input_layout_peso);
        TextInputLayout inputLayoutAltura = findViewById(R.id.input_layout_altura);

        inputLayoutPeso.setError(null);
        inputLayoutAltura.setError(null);

        if(peso.isEmpty()) {
            inputLayoutPeso.setError("Digite um valor para o peso da pessoa");
            return false;
        } else if(altura.isEmpty()) {
            inputLayoutAltura.setError("Digite um valor para a altura da pessoa");
            return false;
        } else {
            return true;
        }
    }
}