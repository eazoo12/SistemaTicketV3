package com.example.sistematicketv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Login extends AppCompatActivity {
    private String usuarioSer;
    private String Pass ;
    private String tipoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IngresarLogin (View v){

        final EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        final EditText pass = (EditText) findViewById(R.id.txtPass);



        if(usuario.getText().toString().equals("") && pass.getText().toString().equals(""))
        {
            Toast toast= Toast.makeText(getApplicationContext(), " Escriba Usuario o Password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        }else {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://sistematickets.atwebpages.com/rest/index.php/login/"+usuario.getText().toString()+"/"+pass.getText().toString())
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        String cadenaJson = response.body().string();
                        Log.i("====>", cadenaJson);

                        Gson gson = new Gson();
                        Type stringStringMap = new TypeToken<ArrayList<Map<String, Object>>>() {
                        }.getType();

                        final ArrayList<Map<String, Object>> retorno = gson.fromJson(cadenaJson, stringStringMap);

                        final String[] matriz = new String[retorno.size()];
                        int i = 0;

                        for (Map<String, Object> x : retorno) {
                            usuarioSer = (String) x.get("CodUsuario");
                            Pass = (String) x.get("Pass");
                            tipoUsuario = (String) x.get("IdTipoUsuario");

                            // matriz[i++] = (String) (x.get("CodUsuario") + " - S/. " + x.get("Pass")+ x.get("IdTipoUsuario"));
                        }

                        //final ListView lstProductos = (ListView)findViewById(R.id.listaProductos);
                        Log.i("====>IF22", usuario + " " + usuarioSer + " " + pass + " " + Pass);
                        if (usuario.getText().toString().equals(usuarioSer) && pass.getText().toString().equals(Pass)) {
                            Log.i("====>IF", usuarioSer + " " + Pass);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Bienvenido" + usuarioSer, Toast.LENGTH_SHORT);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            });


                        } else {

                            Log.i("====>Else", usuarioSer + " " + Pass);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Usuario o Password INcorrecto", Toast.LENGTH_SHORT);
                                    Toast toast= Toast.makeText(getApplicationContext(), " Usuario o Password Incorrrecto", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();

                                }
                            });


                        }


                    }
                }
            });

        }

    }

    public void RegistrarUsuario (View v){

        startActivity(new Intent(this,Registrar.class));
    }



    public void buscar(View v){


    }


}
