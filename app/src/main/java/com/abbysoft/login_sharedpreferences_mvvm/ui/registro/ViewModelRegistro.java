package com.abbysoft.login_sharedpreferences_mvvm.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abbysoft.login_sharedpreferences_mvvm.model.Usuario;
import com.abbysoft.login_sharedpreferences_mvvm.request.ApiClient;
import com.abbysoft.login_sharedpreferences_mvvm.ui.login.MainActivity;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> mUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }


    // Crear un nuevo usuario y guardarlo en SharedPreferences
    public void crear(long dni, String apellido, String nombre, String mail, String password) {
        Usuario usuario = new Usuario(dni, apellido, nombre, mail, password);
        ApiClient.guardar(context, usuario); // Guardar el usuario
        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    // Obtener el usuario guardado
    public void getUsuario(String origen) {
        if (origen.equals("login")){
            Usuario usuario = ApiClient.leer(context);
            if (usuario != null && !usuario.getMail().equals("-1")) {
                mUsuario.setValue(usuario); // Si hay un usuario registrado, lo mostramos
            }
        }
    }
}



