package com.example.urbanstep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbanstep.services.ApiService;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    // Declarar os campos do layout
    private EditText etUsername, etNome, etEmail, etMorada, etNContribuinte, etPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar os campos com base no layout
        etUsername = findViewById(R.id.etUsername);
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etMorada = findViewById(R.id.etMorada);
        etNContribuinte = findViewById(R.id.etNContribuinte);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        ApiService apiService = new ApiService(this);

        // Configurar o botão de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("username", etUsername.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("password", etPassword.getText().toString());
                params.put("nome", etNome.getText().toString());
                params.put("morada", etMorada.getText().toString());
                params.put("nContribuinte", etNContribuinte.getText().toString());

                apiService.postRequest("signup", params, new ApiService.ApiCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(RegisterActivity.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();

                        // Redirecionar para a página de login
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(RegisterActivity.this, "Erro: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
