package com.example.catsafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Construtor
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true); // Ativa o suporte a Foreign Keys
    }

    // Nome do banco de dados e versão
    private static final String DATABASE_NAME = "CatSafe.db";
    private static final int DATABASE_VERSION = 1;

    // Tabelas
    private static final String TABELA_USUARIO = "usuarios";
    private static final String TABELA_PET = "pets";
    private static final String TABELA_HISTORICO = "historico_alimentacao";

    // Colunas para a tabela de usuários
    private static final String COLUNA_ID_USUARIO = "user_id";
    private static final String COLUNA_NOME_USUARIO = "name";
    private static final String COLUNA_EMAIL_USUARIO = "email";
    private static final String COLUNA_SENHA_USUARIO = "senha";

    // Colunas para a tabela de pets
    private static final String COLUNA_PET_ID = "pet_id";
    private static final String COLUNA_PET_NOME = "pet_name";
    private static final String COLUNA_PET_IDADE = "idade";
    private static final String COLUNA_USUARIO_DONO_ID = "owner_id"; // Relacionamento com a tabela users

    // Colunas para a tabela de histórico de alimentação
    private static final String COLUNA_HISTORICO_ID = "history_id";
    private static final String COLUNA_FEED_HORARIO = "feed_horario";
    private static final String COLUNA_FEED_QUANTIDADE = "feed_quantidade";
    private static final String COLUNA_PET_FED_ID = "pet_id"; // Relacionamento com a tabela pets

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        String createTabela_Usuario = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO + " (" +
                COLUNA_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_USUARIO + " TEXT, " +
                COLUNA_EMAIL_USUARIO + " TEXT, " +
                COLUNA_SENHA_USUARIO + " TEXT)";

        String createTable_Pet = "CREATE TABLE IF NOT EXISTS " + TABELA_PET + " (" +
                COLUNA_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_PET_NOME + " TEXT, " +
                COLUNA_PET_IDADE + " INTEGER, " +
                COLUNA_USUARIO_DONO_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUNA_USUARIO_DONO_ID + ") REFERENCES " + TABELA_USUARIO + "(" + COLUNA_ID_USUARIO + "))";

        String createTable_Historico_Alimentacao = "CREATE TABLE IF NOT EXISTS " + TABELA_HISTORICO + " (" +
                COLUNA_HISTORICO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_FEED_HORARIO + " TEXT, " +
                COLUNA_FEED_QUANTIDADE + " REAL, " +
                COLUNA_PET_FED_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUNA_PET_FED_ID + ") REFERENCES " + TABELA_PET + "(" + COLUNA_PET_ID + "))";

        // Executando as queries de criação
        db.execSQL(createTabela_Usuario);
        db.execSQL(createTable_Pet);
        db.execSQL(createTable_Historico_Alimentacao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso a estrutura do banco de dados seja atualizada no futuro
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PET);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_HISTORICO);
        onCreate(db);
    }

    // Método para adicionar um novo usuário
    public boolean addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_USUARIO, name);
        values.put(COLUNA_EMAIL_USUARIO, email);
        values.put(COLUNA_SENHA_USUARIO, password);

        long result = db.insert(TABELA_USUARIO, null, values);
        db.close();
        return result != -1;
    }

    // Método para adicionar um novo pet
    public boolean addPet(String petName, int age, int ownerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_PET_NOME, petName);
        values.put(COLUNA_PET_IDADE, age);
        values.put(COLUNA_USUARIO_DONO_ID, ownerId);

        long result = db.insert(TABELA_PET, null, values);
        db.close();
        return result != -1;
    }

    // Método para buscar o histórico de alimentação de um pet
    public Cursor getFeedHistory(int petId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA_HISTORICO + " WHERE " + COLUNA_PET_FED_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(petId)});
    }

    // Método para buscar um usuário por email
    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUNA_NOME_USUARIO + ", " + COLUNA_EMAIL_USUARIO + " FROM " + TABELA_USUARIO + " WHERE " + COLUNA_EMAIL_USUARIO + " = ?";
        return db.rawQuery(query, new String[]{email});
    }

    public boolean checkUser(String nome, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA_USUARIO + " WHERE " + COLUNA_NOME_USUARIO + " = ? AND " + COLUNA_SENHA_USUARIO + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nome, senha});

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }

    public String getUserEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ COLUNA_EMAIL_USUARIO + " FROM " + TABELA_USUARIO + " WHERE " + COLUNA_NOME_USUARIO + " = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            String email = cursor.getString(0);
            cursor.close();
            return email;
        }
        cursor.close();
        db.close();
        return null;
    }

}
