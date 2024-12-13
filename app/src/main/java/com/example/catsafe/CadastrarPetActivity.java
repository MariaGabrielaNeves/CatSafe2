//package com.example.catsafe;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.bd.DatabaseHelper;
//import com.example.catsafe2.R;
//
//import java.util.Calendar;
//
//public class CadastrarPetActivity extends AppCompatActivity {
//    ImageButton voltar;
//    Dialog dialog;
//    Button salvarInformacoes;
//    DatabaseHelper db;
//    EditText petNomeEditText, petRacaEditText, petDataNascimentoEditText, petIdadeEditText;
//    Spinner sexoSpinner;
//    String sexo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cadastrar_pet);
//
//        db = new DatabaseHelper(getApplicationContext());
//
//        voltar = findViewById(R.id.imageButton2_voltar);
//        salvarInformacoes = findViewById(R.id.salvarInformacoes);
//        petNomeEditText = findViewById(R.id.editText_petNome);
//        petRacaEditText = findViewById(R.id.editText_petRaca);
//        petDataNascimentoEditText = findViewById(R.id.editText_petDataNascimento);
//        sexoSpinner = findViewById(R.id.spinner_sexoInformado);
//
//        voltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
//
////        salvarInformacoes.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String petNome = petNomeEditText.getText().toString();
////                String raca = petRacaEditText.getText().toString();
////                String dataNascimento = petDataNascimentoEditText.getText().toString();
////                Calendar calendar = Calendar.getInstance();
////                int anoAtual = 0;
////                calendar.set(Calendar.YEAR, anoAtual);
////                int idade =
////
////                // Aqui, você precisa obter o ID do dono do pet.
////                // Vamos supor que ele já esteja disponível no seu código como uma variável:
////                //int donoPetId = /* ID do dono (usuário logado ou selecionado) */;
////
////                boolean isInserted = db.adicionarPet(petNome, raca, dataNascimento,idade,donoPetId);
////                if (isInserted) {
////                    Toast.makeText(CadastrarPetActivity.this, "Pet cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
////                    openDialog();
////                } else {
////                    Toast.makeText(CadastrarPetActivity.this, "Erro ao cadastrar o pet!", Toast.LENGTH_LONG).show();
////                }
////            }
////        });
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.sexo,
//                android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sexoSpinner.setAdapter(adapter);
//
//        sexoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sexo = parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do nothing
//            }
//        });
//
//        dialog = new Dialog(this);
//    }
//
//    private void openDialog() {
//        dialog.setContentView(R.layout.activity_alert_dialog);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        ImageButton imageButton_fechar = dialog.findViewById(R.id.imageButton_fechar);
//
//        // Fechar o dialog ao clicar no X
//        imageButton_fechar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                finish();
//            }
//        });
//        dialog.show();
//    }
//}
