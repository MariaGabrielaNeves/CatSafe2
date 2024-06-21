package com.example.catsafe;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.catsafe2.R;


public class AlterarInformacao extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_informacao);


        Button button_salvarAlteracoes = findViewById(R.id.button_salvarAlteracoes);


        button_salvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomAlertDialog();
            }
        });
    }


    private void showCustomAlertDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.activity_alteracao_salva, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);


        AlertDialog dialog = builder.create();


        Button buttonexcluir = dialogView.findViewById(R.id.buttonexcluir);
        buttonexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }
}
