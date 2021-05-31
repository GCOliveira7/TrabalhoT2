package com.example.trabalhot2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.sql.Blob;
import java.util.ArrayList;

public class BDSQLiteContatos extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContatosDB";
    private static final String TABELA_CONTATOS = "contatos";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String OBSERVACAO = "observacao";
    private static final String[] COLUNAS = {NOME, TELEFONE, OBSERVACAO};

    public BDSQLiteContatos(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE contatos("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"nome TEXT,"
                +"telefone INTEGER"
                +"observacao TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contatos");
        this.onCreate(db);
    }

    public void addContato (Contato contato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (NOME, contato.getNome());
        values.put (TELEFONE, new Integer(contato.getTelefone()));
        values.put (OBSERVACAO, contato.getObservacao());
        db.insert (TABELA_CONTATOS,null,values);
        db.close();
    }

    public Contato getContato (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query (TABELA_CONTATOS, COLUNAS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor == null){
            return null;
        } else {
            cursor.moveToFirst();
            Contato contato = cursorToContato(cursor);
            return contato;
        }
    }

    private Contato cursorToContato(Cursor cursor){
        Contato contato = new Contato();
        contato.setId(Integer.parseInt(cursor.getString(0)));
        contato.setNome(cursor.getString(1));
        contato.setTelefone(Integer.parseInt(cursor.getString(2)));
        contato.setObservacao(cursor.getString(3));
        return contato;
    }

    public ArrayList<Contato> getAllContatos(){
        ArrayList<Contato> listaContatos = new ArrayList<Contato>();
        String query = "SELECT * FROM " + TABELA_CONTATOS + " ORDER BY " + NOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Contato contato = cursorToContato(cursor);
                listaContatos.add(contato);
            } while (cursor.moveToNext());
        }
        return listaContatos;
    }

    public int updateContato (Contato contato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, contato.getNome());
        values.put(TELEFONE, new Integer(contato.getTelefone()));
        values.put(OBSERVACAO, contato.getObservacao());
        int i = db.update(TABELA_CONTATOS, values, ID + " = ?", new String[]{String.valueOf(contato.getId())});
        db.close();
        return i;
    }

    public int deleteContato (Contato contato){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_CONTATOS, ID + " = ?", new String[]{String.valueOf(contato.getId())});
        db.close();
        return i;
    }
}
