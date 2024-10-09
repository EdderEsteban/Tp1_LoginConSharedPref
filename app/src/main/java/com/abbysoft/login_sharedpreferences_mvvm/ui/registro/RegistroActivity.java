package com.abbysoft.login_sharedpreferences_mvvm.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.abbysoft.login_sharedpreferences_mvvm.R;
import com.abbysoft.login_sharedpreferences_mvvm.databinding.ActivityMainBinding;
import com.abbysoft.login_sharedpreferences_mvvm.databinding.ActivityRegistroBinding;
import com.abbysoft.login_sharedpreferences_mvvm.model.Usuario;
import com.abbysoft.login_sharedpreferences_mvvm.ui.login.MainActivity;
import com.abbysoft.login_sharedpreferences_mvvm.ui.login.MainActivityViewModel;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private ViewModelRegistro vmR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        vmR = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        setContentView(binding.getRoot());

        vmR.mUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(String.valueOf(usuario.getDni()));
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getMail());
                binding.etPass.setText(usuario.getPassword());
            }
        });
        Intent intent = getIntent();
        String origen = intent.getStringExtra("origen");
        vmR.getUsuario(origen);

        binding.btnSaveAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long dni = Long.parseLong(binding.etDni.getText().toString());
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String mail = binding.etMail.getText().toString();
                String password = binding.etPass.getText().toString();

                vmR.crear(dni,apellido,nombre,mail,password);
            }
        });
    }
}