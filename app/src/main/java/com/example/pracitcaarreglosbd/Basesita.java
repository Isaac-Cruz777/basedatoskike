package com.example.pracitcaarreglosbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Basesita extends SQLiteOpenHelper {

    public Basesita(Context contexto) {
        super(contexto, "empresa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE empleados (" +
                "clave INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "departamento TEXT," +
                "sueldo INTEGER)");
    }

    public void agregar(String nombre, String departamento, int sueldo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("departamento", departamento);
        valores.put("sueldo", sueldo);

        db.insert("empleados", null, valores);

        db.close();
    }

    public Cursor consultar() {

        SQLiteDatabase db = this.getReadableDatabase();

        android.database.Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM empleados", null);

        return cursor;
    }

    public void actualizar(int clave, int sueldo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("sueldo", sueldo);

        db.update(
                "empleados",
                valores,
                "clave=?",
                new String[]{String.valueOf(clave)}
        );

        db.close();
    }

    public void eliminar(int clave) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                "empleados",
                "clave=?",
                new String[]{String.valueOf(clave)}
        );

        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}
