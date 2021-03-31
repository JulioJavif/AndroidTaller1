package com.example.androidtaller1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String DB = "BDUsuarios";
    String table = "create table if not exists usuario(id integer primary key autoincrement, nombre text," +
            " apellido text, correo text, contrasena text, sexo text)";

    public DAOUsuario(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(DB, c.MODE_PRIVATE, null);
        sql.execSQL(table);
        u = new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if (Buscar(u.getCorreo())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("apellido",u.getApellido());
            cv.put("correo",u.getCorreo());
            cv.put("contrasena",u.getContrasena());
            cv.put("sexo",u.getSexo());
            return (sql.insert("usuario", null, cv)>0);
        }else {
            return false;
        }
    }

    public int Buscar(String u){
        int x = 0;
        lista = SelectUsuario();
        for (Usuario us: lista) {
            if (us.getCorreo().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> SelectUsuario(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr!=null&&cr.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellido(cr.getString(2));
                u.setCorreo(cr.getString(3));
                u.setContrasena(cr.getString(4));
                u.setSexo(cr.getString(5));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String user, String pass){
        int aux = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr!= null && cr.moveToFirst()){
            do {
                if (cr.getString(3).equals(user) && cr.getString(4).equals(pass)){
                    aux++;
                }
            }while(cr.moveToNext());
        }
        return aux;
    }

    public int verificarCorreo(String correo){
        int aux=0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if (cr!= null && cr.moveToFirst()){
            do {
                if (cr.getString(3).equals(correo)){
                    aux++;
                }
            }while(cr.moveToNext());
        }
        return aux;

    }

    public Usuario getUsuario(String user, String pass){
        lista = SelectUsuario();
        for (Usuario us: lista) {
            if (us.getCorreo().equals(user) && us.getContrasena().equals(pass)){
                return us;
            }
        }
        return null;
    }

    public Usuario UserByCorreo(String c){
        lista = SelectUsuario();
        for (Usuario us: lista){
            if (us.getCorreo().equalsIgnoreCase(c)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = SelectUsuario();
        for (Usuario us: lista) {
            if (us.getId() == id){
                return us;
            }
        }
        return null;
    }

    public void setContrasena(String ncontrasena, int id){
        sql.execSQL("UPDATE usuario " +
                "SET contrasena=" + ncontrasena +
                " WHERE id="+id);
    }
}
