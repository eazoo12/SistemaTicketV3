package com.example.sistematicketv2;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Fragment05 extends Fragment {

    private EditText txtUsuario;
    private EditText txtpass;
    private ImageButton btnGrabar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment05,container,false);


        txtUsuario = (EditText) view.findViewById(R.id.txtUsuariS);

        txtpass = (EditText) view.findViewById(R.id.editText3);

        btnGrabar = (ImageButton) view.findViewById(R.id.imageButton3);


        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = txtUsuario.getText().toString();
                String pass = txtpass.getText().toString();

                grabarServicio(pass,usuario);

            }
        });

        return view;
    }



    public void grabarServicio(String pass,String usuario){



        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addFormDataPart("idTicket", "0")
                .addFormDataPart("pass", pass)
                .addFormDataPart("usuarioID", usuario)

                .build();

        Request request = new Request.Builder()
                .url("http://sistematickets.atwebpages.com/rest/index.php/updateUsu/"+pass+"/"+usuario)

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
                    Log.i("====>22", cadenaJson);

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast toast= Toast.makeText(getActivity().getApplicationContext(), "Se insertó correctamente El servicio", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    });

                }
            }
        });



    }


}
