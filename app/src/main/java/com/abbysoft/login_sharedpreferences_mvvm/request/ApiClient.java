package com.abbysoft.login_sharedpreferences_mvvm.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.abbysoft.login_sharedpreferences_mvvm.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context context){
        if (sp == null){
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail", usuario.getMail());
        editor.putString("password", usuario.getPassword());
        editor.apply();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String mail = sp.getString("mail", "-1");
        String password = sp.getString("password", "-1");

        Usuario usuario = new Usuario(dni, apellido, nombre, mail, password);

        return usuario;
    }

    public static boolean login(Context context, String mail, String password){
        boolean usuario = false;
        SharedPreferences sp = conectar(context);

        String logmail = sp.getString("mail", "-1");
        String logpassword = sp.getString("password", "-1");

        if(mail.equals(logmail) && password.equals(logpassword)) {

            usuario = true;
            return usuario;
        }

           return usuario;
    }
}
