package com.example.pmdm_315;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonOCel;
    private RadioButton radioButtonOKel;
    private RadioButton radioButtonOFar;

    private RadioButton radioButtonDCel;
    private RadioButton radioButtonDKel;
    private RadioButton radioButtonDFar;

    private Button buttonConvertir;

    private EditText editTextIntroduzcaTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButtonOCel = findViewById(R.id.radioButtonOCel);
        radioButtonOKel = findViewById(R.id.radioButtonOKel);
        radioButtonOFar = findViewById(R.id.radioButtonOFar);

        radioButtonDCel = findViewById(R.id.radioButtonDCel);
        radioButtonDKel = findViewById(R.id.radioButtonDKel);
        radioButtonDFar = findViewById(R.id.radioButtonDFar);

        editTextIntroduzcaTemperatura = findViewById(R.id.editTextIntroduzcaTemperatura);
        buttonConvertir = findViewById(R.id.buttonConvertir);

        buttonConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirTemperatura();
            }
        });
    }

    private void convertirTemperatura() {
        String temperaturaStr = editTextIntroduzcaTemperatura.getText().toString();

        if (temperaturaStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Ingresa una temperatura", Toast.LENGTH_SHORT).show();
            return;
        }

        double temperatura = Double.parseDouble(temperaturaStr);

        String escalaOrigen = obtenerEscalaSeleccionadaOrigen(radioButtonOCel, radioButtonOFar, radioButtonOKel);
        String escalaDestino = obtenerEscalaSeleccionadaDestino(radioButtonDCel, radioButtonDFar, radioButtonDKel);

        double temperaturaConvertida = convertir(temperatura, escalaOrigen, escalaDestino);

        String resultado = String.format("%.2f %s = %.2f %s", temperatura, escalaOrigen, temperaturaConvertida, escalaDestino);
        Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_SHORT).show();
    }

    private String obtenerEscalaSeleccionadaOrigen(RadioButton radioButtonOCel, RadioButton radioButtonOFar, RadioButton radioButtonOKel) {
        if (radioButtonOCel.isChecked()) {
            return "Celsius";
        } else if (radioButtonOFar.isChecked()) {
            return "Fahrenheit";
        } else if (radioButtonOKel.isChecked()) {
            return "Kelvin";
        }
        return "No has seleccionado ninguna temperatura de origen.";
    }

    private String obtenerEscalaSeleccionadaDestino(RadioButton radioButtonDCel, RadioButton radioButtonDFar, RadioButton radioButtonDKel) {
        if (radioButtonDCel.isChecked()) {
            return "Celsius";
        } else if (radioButtonDFar.isChecked()) {
            return "Fahrenheit";
        } else if (radioButtonDKel.isChecked()) {
            return "Kelvin";
        }
        return "No has seleccionado ninguna temperatura de destino.";
    }

    private double convertir(double temperatura, String escalaOrigen, String escalaDestino) {
        switch (escalaOrigen) {
            case "Celsius":
                return convertirDesdeCelsius(temperatura, escalaDestino);
            case "Fahrenheit":
                return convertirDesdeFahrenheit(temperatura, escalaDestino);
            case "Kelvin":
                return convertirDesdeKelvin(temperatura, escalaDestino);
            default:
                return 0.0;
        }
    }

    private double convertirDesdeCelsius(double temperatura, String escalaDestino) {
        switch (escalaDestino) {
            case "Celsius":
                return temperatura;
            case "Fahrenheit":
                return (temperatura * 9/5) + 32;
            case "Kelvin":
                return temperatura + 273.15;
            default:
                return 0.0;
        }
    }

    private double convertirDesdeFahrenheit(double temperatura, String escalaDestino) {
        switch (escalaDestino) {
            case "Celsius":
                return (temperatura - 32) * 5/9;
            case "Fahrenheit":
                return temperatura;
            case "Kelvin":
                return (temperatura - 32) * 5/9 + 273.15;
            default:
                return 0.0;
        }
    }

    private double convertirDesdeKelvin(double temperatura, String escalaDestino) {
        switch (escalaDestino) {
            case "Celsius":
                return temperatura - 273.15;
            case "Fahrenheit":
                return (temperatura - 273.15) * 9/5 + 32;
            case "Kelvin":
                return temperatura;
            default:
                return 0.0;
        }
    }

}
