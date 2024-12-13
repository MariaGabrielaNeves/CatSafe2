package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Notificação.NotificationReceiver;
import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;

import java.util.Calendar;

public class ProgramarHorarioActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SCHEDULE_EXACT_ALARM = 100;

    Dialog dialog;
    ImageButton voltar;
    Button button2_continuar;
    EditText textView_horarioEscolhido;
    Spinner spinnerPorcoes;
    String porcoes;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programar_horario);

        voltar = findViewById(R.id.imageButton2_voltar);
        button2_continuar = findViewById(R.id.button2_continuar);
        spinnerPorcoes = findViewById(R.id.spinner_qtdRacao);
        textView_horarioEscolhido = findViewById(R.id.textView_horarioEscolhido);

        dialog = new Dialog(this);
        db = new DatabaseHelper(this);

        checkExactAlarmPermission();

        voltar.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        button2_continuar.setOnClickListener(v -> {
            String horario = textView_horarioEscolhido.getText().toString();

            if (horario.isEmpty()) {
                Toast.makeText(this, "Insira um horário válido.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (porcoes == null || porcoes.isEmpty()) {
                Toast.makeText(this, "Selecione uma porção antes de continuar.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (horario.matches("\\d{2}:\\d{2}")) {
                int hora = Integer.parseInt(horario.split(":")[0]);
                int minuto = Integer.parseInt(horario.split(":")[1]);

                if (hora >= 0 && hora < 24 && minuto >= 0 && minuto < 60) {
                    db.salvarOuAtualizarHorario(horario, porcoes);
                    setAlarm(hora, minuto);
                } else {
                    Toast.makeText(this, "Hora ou minuto inválido!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Formato inválido! Use HH:mm", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.porcoes, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPorcoes.setAdapter(adapter);

        spinnerPorcoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                porcoes = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                porcoes = null;
            }
        });
    }

    private void checkExactAlarmPermission() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                        Uri.fromParts("package", getPackageName(), null));
                startActivityForResult(intent, REQUEST_CODE_SCHEDULE_EXACT_ALARM);
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm(int hora, int minuto) {
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.set(Calendar.HOUR_OF_DAY, hora);
            calendario.set(Calendar.MINUTE, minuto);
            calendario.set(Calendar.SECOND, 0);

            if (calendario.getTimeInMillis() <= System.currentTimeMillis()) {
                calendario.add(Calendar.DAY_OF_YEAR, 1);
            }

            // Gera um código único para o PendingIntent
            int requestCode = hora * 100 + minuto;

            Intent it = new Intent(this, NotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this, 0, it, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), pendingIntent);
                Toast.makeText(this, "Alarme configurado para " + hora + ":" + String.format("%02d", minuto), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao acessar o serviço de alarme.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao definir o alarme.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCHEDULE_EXACT_ALARM) {
            checkExactAlarmPermission();
        }
    }
}
