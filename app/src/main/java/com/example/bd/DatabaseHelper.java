package com.example.bd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Construtor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true); // Ativa o suporte a Foreign Keys
    }

    // Nome do banco de dados e versão
    public static final String DATABASE_NAME = "CatSafe.db";
    public static final int DATABASE_VERSION = 2;

    // Tabela usuario
    public static final String TABELA_USUARIO = "Usuarios";
    // Colunas para a tabela de usuários
    public static final String COLUNA_ID_USUARIO = "id_usuario";
    public static final String COLUNA_NOME_USUARIO = "nome";
    public static final String COLUNA_EMAIL_USUARIO = "email";
    public static final String COLUNA_SENHA_USUARIO = "senha";

    //Tabela HistoricoAlimentação
    public static final String TABELA_HISTORICO = "Historico_alimentacao";
    // Colunas para a tabela de histórico de alimentação
    public static final String COLUNA_HISTORICO_ID = "id_historico";
    public static final String COLUNA_HORARIO_ALIMENTACAO = "horario_da_alimentacao";
    public static final String COLUNA_QUANTIDADE_ALIMENTACAO = "quantidade_alimentacao";
    public static final String COLUNA_FED_ID = "alimentacao_id";
    public static final String COLUNA_PORCAO = "porcao";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        String criarTabela_Usuarios = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO +
                " (" + COLUNA_ID_USUARIO + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_USUARIO + " TEXT NOT NULL UNIQUE, " +
                COLUNA_EMAIL_USUARIO + " TEXT NOT NULL, " +
                COLUNA_SENHA_USUARIO + " TEXT NOT NULL)";
        db.execSQL(criarTabela_Usuarios);

        String criarTabela_HistoricoAlimentacao = "CREATE TABLE IF NOT EXISTS " + TABELA_HISTORICO + " (" +
                COLUNA_HISTORICO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_HORARIO_ALIMENTACAO + " TIME, " +
                COLUNA_PORCAO + " TEXT, " +
                COLUNA_QUANTIDADE_ALIMENTACAO + " REAL, " +
                COLUNA_FED_ID + " INTEGER)";
        db.execSQL(criarTabela_HistoricoAlimentacao);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        //atualizar
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_HISTORICO);
        onCreate(db);
    }

    //adicionar um novo usuário
    public boolean adicionarUsuario(String nome, String email, String senha) {
        if (usuarioExiste(nome)) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_NOME_USUARIO, nome);
        cv.put(COLUNA_EMAIL_USUARIO, email);
        cv.put(COLUNA_SENHA_USUARIO, senha);

        long resultado = -1;

        try {
            resultado = db.insert(TABELA_USUARIO, null, cv);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return resultado != -1;
    }

    public boolean usuarioExiste(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABELA_USUARIO + " WHERE " + COLUNA_NOME_USUARIO + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nome});
        boolean existe = (cursor.getCount() > 0);
        cursor.close();
        return existe;
    }

    // Método na DatabaseHelper para salvar ou atualizar o horário
    public void salvarOuAtualizarHorario(String horarioDaAlimentacao, String porcao ) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verifica se o registro com o ID já existe
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABELA_HISTORICO + " WHERE " + COLUNA_HORARIO_ALIMENTACAO + " = ?", new String[]{horarioDaAlimentacao});

        ContentValues values = new ContentValues();
        values.put(COLUNA_HORARIO_ALIMENTACAO, horarioDaAlimentacao);
        values.put(COLUNA_PORCAO, porcao);

        if (cursor.moveToFirst()) {
            // Registro existe: atualiza o horário
            db.update(TABELA_HISTORICO, values, COLUNA_HORARIO_ALIMENTACAO + " = ?",
                    new String[]{horarioDaAlimentacao});
        } else {
            // Registro não existe: insere um novo horário
            db.insert(TABELA_HISTORICO, null, values);
        }

        cursor.close();
        db.close();
    }

    public boolean checarUsuario(String nome, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String SQL = "SELECT COUNT(*) FROM " + TABELA_USUARIO + " WHERE " + COLUNA_NOME_USUARIO + " = ? AND " + COLUNA_SENHA_USUARIO + " = ?";
            cursor = db.rawQuery(SQL, new String[]{nome, senha});
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e("DatabaseError", "Erro ao checar usuário", e);
            return false; // Retorna false em caso de erro
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public String getUsuario(String nomeUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + COLUNA_NOME_USUARIO + " FROM " + TABELA_USUARIO + " WHERE " + COLUNA_NOME_USUARIO + " = ?", new String[]{nomeUsuario});
            if (cursor.moveToFirst()) {
                return cursor.getString(0);
            }
            return null; // Retorna null se não encontrar o usuário
        } catch (Exception e) {
            Log.e("DatabaseError", "Erro ao obter email do usuário", e);
            return null; // Retorna null em caso de erro
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public Cursor getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA_USUARIO + " WHERE " + COLUNA_ID_USUARIO + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(userId)});
    }


    // Método para verificar se o nome de usuário já existe
    public boolean usernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_USUARIO, null, COLUNA_NOME_USUARIO + " = ?",
                new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Método para adicionar horário
    public void addHorario(String horario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_HORARIO_ALIMENTACAO, horario);
        db.insert(TABELA_HISTORICO, null, values);
        db.close();
    }

    // Método para recuperar todos os horários
    public Cursor getAllHorarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABELA_HISTORICO, null);
    }

    public boolean atualizarUsuario(int id, String username, String email, @Nullable String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME_USUARIO, username);
        values.put(COLUNA_EMAIL_USUARIO, email);
        if (password != null) {
            values.put(COLUNA_SENHA_USUARIO, password);
        }
        int rowsAffected = db.update(TABELA_USUARIO, values, COLUNA_ID_USUARIO + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    public boolean checkUserPassword(int userId, String currentPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query para buscar o usuário pelo ID e pegar a senha armazenada
        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_SENHA_USUARIO},
                COLUNA_ID_USUARIO + " = ?", new String[]{String.valueOf(userId)},
                null, null, null);

        // Se o cursor encontrar o usuário
        if (cursor != null && cursor.moveToFirst()) {
            // Obtém a senha armazenada no banco
            @SuppressLint("Range") String storedPassword = cursor.getString(cursor.getColumnIndex(COLUNA_SENHA_USUARIO));

            // Fecha o cursor para liberar os recursos
            cursor.close();

            // Compara a senha fornecida com a senha armazenada
            return storedPassword.equals(currentPassword);  // Retorna true se as senhas coincidirem
        }

        // Caso não encontre o usuário ou algum outro erro, retorna false
        return false;
    }


}