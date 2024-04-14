package com.example.catsafe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; import android.os.Bundle;
import android.view.View; import android.widget.Button;
import android.widget.TextView;
public class Login extends AppCompatActivity { @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    TextView textViewCadastro = findViewById(R.id.textView_cadastro);
    Button button_continuar2 = findViewById(R.id.button_continuar2);

    // Clique aqui - cadastro
    textViewCadastro.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v) {
            Intent it=new Intent(Login.this, Cadastro.class);
            startActivity(it); }
    });

    // Ação para o botão "Continuar"
    button_continuar2.setOnClickListener(new View.OnClickListener()
    { @Override public void onClick(View v) {
        Intent it=new Intent(Login.this, Cadastro.class);
        startActivity(it); }
    });
}
}