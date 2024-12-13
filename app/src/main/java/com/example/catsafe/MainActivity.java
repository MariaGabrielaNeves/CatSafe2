package com.example.catsafe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.ListView.Adapter2;
import com.example.ListView.Historico2Adpt;
import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    private Context context;
    TextView statusTextView;
    private ArrayList<String> historicoHorarios;
    private ArrayAdapter<String> adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o DrawerLayout e o NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        statusTextView = findViewById(R.id.statusTextView);
        android.widget.ListView listViewHistorico = findViewById(R.id.listViewHistorico); // Corrigido


        // Configura o adapter para o ListView
        listViewHistorico.setAdapter(adapter);

        db = new DatabaseHelper(this);
        historicoHorarios = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historicoHorarios);
        listViewHistorico.setAdapter(adapter);

        carregarHorariosDoBanco();

//        // Inicializa o histórico
//        historico2Adpts = inicializarHistorico();
//        context = this;

//        // Configura o Adapter2 com o ArrayList de histórico
//        adapter2 = new Adapter2(historico2Adpts, this);
//        ListView listView = findViewById(R.id.listViewHistorico);
//        listView.setAdapter(adapter2);

        // Configura o ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Abrir/fechar o menu lateral
        ImageButton imageButton2_menu = findViewById(R.id.imageButton2_menu);
        imageButton2_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navView.setNavigationItemSelectedListener(this);

        // Chama a classe Conectar e passa o TextView para ela
        new Conectar(statusTextView).execute();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home1) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (itemId == R.id.nav_editarInformacao) {
            startActivity(new Intent(this, DadosCadastradosActivity.class));
        } else if (itemId == R.id.nav_despejarRacao) {
            startActivity(new Intent(this, FormaDeDespejoActivity.class));
        } else if (itemId == R.id.nav_alterarQuantidade) {
            startActivity(new Intent(this, CameraActivity.class));
        } else if (itemId == R.id.nav_logout) {
            //startActivity(new Intent(MainActivity.this, ConfirmacaoDialog.class));
            drawerLayout.closeDrawer(GravityCompat.START);
            logout();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle != null && toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Método atualizado para retornar ArrayList de Historico2Adpt
    private ArrayList<Historico2Adpt> inicializarHistorico() {
        ArrayList<Historico2Adpt> listaDeProdutos = new ArrayList<>();
        listaDeProdutos.add(new Historico2Adpt("Tom tá com fome!"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        listaDeProdutos.add(new Historico2Adpt("Teste"));
        return listaDeProdutos;
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Verifica se "Lembrar de mim" está marcado
        if (!sharedPreferences.getBoolean("save_login", false)) {
            editor.remove("username");  // Remove o nome de usuário
            editor.remove("password");  // Remove a senha
        }

        // Sempre define "save_login" como false ao fazer logout
        editor.putBoolean("save_login", false);
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finaliza a MainActivity após o logout
    }

    // Método para atualizar o TextView na thread principal
    private void updateStatus(final String status) {
        // Utiliza Handler ou runOnUiThread para atualizar o TextView na thread principal
        statusTextView.post(new Runnable() {
            @Override
            public void run() {
                statusTextView.setText(status);  // Atualiza o texto do TextView
            }
        });
    }

    private void carregarHorariosDoBanco() {
        Cursor cursor = db.getAllHorarios();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String horario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUNA_HORARIO_ALIMENTACAO));
                historicoHorarios.add("Clique em: " + horario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
    class Conectar implements Runnable {
    private final WeakReference<TextView> statusTextViewRef;

    public Conectar(TextView textView) {
        this.statusTextViewRef = new WeakReference<>(textView);
    }

    @Override
    public void run() {
        try {
            URL url = new URL("http://10.100.51.50");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setReadTimeout(5000);
            conexao.setConnectTimeout(5000);
            conexao.setRequestMethod("GET");
            conexao.setDoInput(true);
            conexao.setDoOutput(false);
            conexao.connect();

            int responseCode = conexao.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = conexao.getInputStream();
                // Processar a resposta
            } else {
                // Tratar erro
                System.out.println("Erro ao conectar: " + responseCode);
            }

            if (statusTextViewRef.get() != null) {
                // Atualiza a UI com o status
                updateStatus("Conectado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (statusTextViewRef.get() != null) {
                updateStatus("Conectado");
            }
        }
    }

    public void execute() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(this);
    }
}
}