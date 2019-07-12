package com.example.sistematicketv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class TicketDAO {

    private DbHelper _dbHelper;

    public TicketDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }



    public void insertar(String contacto, String nombreIndicente, String servicioAfectado,String urgencia, String descripcionIndi, String Imagen
                        , String fechacreacion , int estado, int usuarioID, String TipoTicket) throws DAOException {
        Log.i("TicketDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{contacto, nombreIndicente,servicioAfectado,urgencia,descripcionIndi,Imagen, fechacreacion
                    ,String.valueOf(estado) ,String.valueOf(usuarioID),TipoTicket};
            db.execSQL("INSERT INTO Ticket(contacto,nombreIndicente,ServicioAfectado,Urgencia,descripcionIndi,Imagen,fechacreacion," +
                    "estado,usuarioID,TipoTicket) VALUES(?,?,?,?,?,?,?,?,?,?)", args);
            Log.i("TicketDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("TicketDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Ticket> buscar(String criterio1, String Criterio2) throws DAOException {
        Log.i("TicketDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Ticket> lista = new ArrayList<Ticket>();
        try {
            Cursor c = db.rawQuery("select idTicket,Contacto,ServicioAfectado, nombreIndicente, Urgencia, descripcionIndi,estado, TipoTicket from Ticket " +
                    "where (idTicket like  '%"+criterio1+"%' ) and (Urgencia like '%"+Criterio2+"%')", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("idTicket"));
                    String contacto = c.getString(c.getColumnIndex("Contacto"));
                    String ServicioAfectado = c.getString(c.getColumnIndex("ServicioAfectado"));

                    String nombreIndicente = c.getString(c.getColumnIndex("nombreIndicente"));
                    String Urgencia = c.getString(c.getColumnIndex("Urgencia"));
                    String descripcionIndi = c.getString(c.getColumnIndex("descripcionIndi"));
                    int estado = c.getInt(c.getColumnIndex("estado"));
                    String TipoTicket = c.getString(c.getColumnIndex("TipoTicket"));

                    Ticket modelo = new Ticket();
                    modelo.setIdTicket(id);
                    modelo.setContacto(contacto);
                    modelo.setServicioAfectado(ServicioAfectado);

                    modelo.setNombreIndicente(nombreIndicente);
                    modelo.setUrgencia(Urgencia);
                    modelo.setDescripcionIndi(descripcionIndi);
                    modelo.setEstado(estado);
                    modelo.setTipoTicket(TipoTicket);

                    //Log.i("Fragment01  Dato Contacto", contacto );
                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("TicketDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }



    public void update(int id,String contacto, String nombreIndicente, String servicioAfectado,String urgencia, String descripcionIndi, String Imagen
            , String fechaupdate , int estado, int usuarioID, String TipoTicket) throws DAOException {
        Log.i("GeneroMusicalDAO", "update()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{contacto, nombreIndicente,servicioAfectado,urgencia,descripcionIndi,Imagen, fechaupdate
                    ,String.valueOf(estado) ,String.valueOf(usuarioID),TipoTicket};
            db.execSQL("UPDATE Ticket SET contacto=? ,nombreIndicente=? ,ServicioAfectado=? ,Urgencia=? ,descripcionIndi=? ,Imagen=?,fechaUpdate=?," +
                    "estado=?,usuarioID=?,TipoTicket=? WHERE idTicket="+id, args);

            Log.i("GeneroMusicalDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("GeneroMusicalDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void eliminar(int id) throws DAOException {
        Log.i("GeneroMusicalDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(id)};
            db.execSQL("DELETE FROM Ticket WHERE idTicket=?", args);
        } catch (Exception e) {
            throw new DAOException("GeneroMusicalDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }













}
