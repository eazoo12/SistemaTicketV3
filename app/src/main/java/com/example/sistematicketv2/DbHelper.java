package com.example.sistematicketv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        // null porque se va a usar el SQLiteCursor
        super(context, "Tickets.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS Ticket " +
                "(idTicket INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Contacto TEXT NOT NULL," +
                "nombreIndicente TEXT NOT NULL," +
                "ServicioAfectado TEXT NOT NULL," +
                "Urgencia TEXT NOT NULL," +
                "descripcionIndi TEXT NOT NULL," +
                "Imagen TEXT NOT NULL," +
                "fechacreacion TEXT NOT NULL," +
                "fechaUpdate TEXT," +
                "estado INTEGER not null," +
                "usuarioID INTEGER," +
                "TipoTicket TEXT not null)" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Ticket");
        onCreate(db);
    }
}
