package com.example.sistematicketv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registrar extends AppCompatActivity {

    private EditText txtUsuarioID; //= (EditText) getActivity().findViewById(R.id.txtContacto);
    private EditText txtNombre; //= (EditText) getActivity().findViewById(R.id.txtNombreInd);
    private  EditText txtApellido; //= (EditText) getActivity().findViewById(R.id.txtServicio);
    private EditText txtdescripcionIndi; //= (EditText) getActivity().findViewById(R.id.txtDescrip);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }


    public void RegistrarUsuario (View v)
    {

        txtUsuarioID = (EditText) findViewById(R.id.txtUsuario);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);

        String UsuarioID = txtUsuarioID.getText().toString();
        String Nombre = txtNombre.getText().toString();
        String Apellido = txtApellido.getText().toString();

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addFormDataPart("idTicket", "0")
                .addFormDataPart("codUsuario", UsuarioID)
                .addFormDataPart("pass", UsuarioID)
                .addFormDataPart("nombre", Nombre)
                .addFormDataPart("apellido", Apellido)
                .addFormDataPart("idTipoUsuario", "1")
                .addFormDataPart("idArea", "1")
                .addFormDataPart("estado", "1")

                .build();

        Request request = new Request.Builder()
                .url("http://sistematickets.atwebpages.com/rest/index.php/inserUsuario")
                .post(requestBody)
                .build();
        Log.i("Fragment01",requestBody.toString());
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

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast toast= Toast.makeText(getApplicationContext(), "Se insertó correctamente El servicio", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    });

                }
            }
        });






    }



}
