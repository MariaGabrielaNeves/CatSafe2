package com.example.catsafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;

import com.example.catsafe2.R;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o DrawerLayout e o NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configura o ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configura o ImageButton para abrir/fechar o menu lateral
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

        // Configuração do BottomNavigationView para abrir Activities
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent = null;
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_cad_pet) {
                intent = new Intent(MainActivity.this, CadastrarPetActivity.class);
            } else if (itemId == R.id.navigation_despejo) {
                intent = new Intent(MainActivity.this, FormaDeDespejoActivity.class);
            } else if (itemId == R.id.navigation_camera) {
                intent = new Intent(MainActivity.this, FormaDeDespejoActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });

        // Configura o NavigationView
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home1) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (itemId == R.id.nav_cadastrar_pet) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            intent = new Intent(this, CadastrarPetActivity.class);
        } else if (itemId == R.id.nav_editarInformacao) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            intent = new Intent(this, DadosCadastradosActivity.class);
        }if (itemId == R.id.nav_verificarQuantidade) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            intent = new Intent(this, HistoricoActivity.class);
        } else if (itemId == R.id.nav_despejarRacao) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            intent = new Intent(this, FormaDeDespejoActivity.class);
        } else if (itemId == R.id.nav_alterarQuantidade) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            intent = new Intent(this, FormaDeDespejoActivity.class);
        }else if (itemId == R.id.nav_logout) {
            Log.d("NAVIGATION", "Abrindo MainActivity");
            logout();
        }

        if (intent != null) {
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
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

    private void logout() {
        // Limpar os dados de login do SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.putBoolean("save_login", false);
        editor.apply();

        // Redirecionar para a LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpa a pilha de atividades
        startActivity(intent);
        finish(); // Finaliza a MainActivity
    }
}