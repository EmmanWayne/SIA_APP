package com.example.sia.principal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;

public class login extends AppCompatActivity {

    Button continuar;
    private boolean esVisible;
    TextView checkContrasena;
    EditText txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_sia_login);

        checkContrasena = findViewById(R.id.checkPass);
        txtContraseña = findViewById(R.id.txtContrasena);

        continuar = (Button) findViewById(R.id.btnContinuar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(login.this, menu.class);
                startActivity(intent2);
                finish();
            }
        });

        checkContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOcultarContrasena();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("¿Deseas salir de SIA?")
                    .setMessage("¿Estás seguro?")
                    .setNegativeButton("NO", null)// sin listener
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            login.this.finish();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void mostrarOcultarContrasena(){
        checkContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!esVisible) {
                    txtContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    esVisible = true;
                    checkContrasena.setText("Mostrar");
                    ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
                }
                else {
                    txtContraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    esVisible = false;
                    checkContrasena.setText("Ocultar");
                    ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
                }
            }
        });
    }

}
