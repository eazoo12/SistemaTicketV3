package com.example.sistematicketv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




public class Fragment01 extends Fragment {



    private EditText txtId;
    private EditText txtContacto; //= (EditText) getActivity().findViewById(R.id.txtContacto);
    private EditText txtnombreIndicente; //= (EditText) getActivity().findViewById(R.id.txtNombreInd);
    private  EditText txtServicioAfectado; //= (EditText) getActivity().findViewById(R.id.txtServicio);
    private EditText txtdescripcionIndi; //= (EditText) getActivity().findViewById(R.id.txtDescrip);
    private Button btnGrabar;
    private Spinner spinner1, spinner2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment01,container,false);

        txtId = (EditText) view.findViewById(R.id.txtIncidente);
        txtContacto = (EditText) view.findViewById(R.id.txtContacto3);
        txtnombreIndicente = (EditText) view.findViewById(R.id.txtNombreInd);
        txtServicioAfectado = (EditText) view.findViewById(R.id.txtServicio);
        txtdescripcionIndi = (EditText) view.findViewById(R.id.txtDescrip);
        btnGrabar = (Button) view.findViewById(R.id.button2);

        spinner2 = (Spinner) view.findViewById(R.id.sp2);

        final Bundle bundle = getArguments();

        if(bundle != null)
        {
            int id =  bundle.getInt("id");
            String contacto =  bundle.getString("contacto");
            String nombreIncidente =  bundle.getString("objetoData");


            String servicioAfectado =  bundle.getString("servicioAfectado");
            String descripcion =  bundle.getString("descripcion");


            txtId.setText(String.valueOf(id));
            txtContacto.setText(contacto);
            txtnombreIndicente.setText(nombreIncidente);
            txtServicioAfectado.setText(servicioAfectado);
            txtdescripcionIndi.setText(descripcion);
        }






        addItemsOnSpinner2(spinner2);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Contacto = txtContacto.getText().toString();
                String nombreIndicente =txtnombreIndicente.getText().toString();
                String ServicioAfectado =txtServicioAfectado.getText().toString();
                String descripcionIndi = txtdescripcionIndi.getText().toString();
                String fechaCrea =Calendar.getInstance().getTime().toString();
                int estado = 1;
                int idUsuari=1;

                if(bundle != null)
                {
                    update(Contacto,nombreIndicente,ServicioAfectado,String.valueOf(spinner2.getSelectedItem()),descripcionIndi,fechaCrea);

                }else{
                grabar(Contacto,nombreIndicente,ServicioAfectado,String.valueOf(spinner2.getSelectedItem()),descripcionIndi,fechaCrea);

                grabarServicio(Contacto,nombreIndicente,ServicioAfectado,String.valueOf(spinner2.getSelectedItem()),descripcionIndi,fechaCrea);
                }
                txtContacto.setText("");
                txtnombreIndicente.setText("");
                txtServicioAfectado.setText("");
                txtdescripcionIndi.setText("");
            }
        });




        return view;
    }


    public void grabar(String Contacto,String nombreIndicente,String ServicioAfectado,String urgencia,
                       String descripcionIndi,String fechaCrea) {


        //EditText Contacto = (EditText) getActivity().findViewById(R.id.txtContacto);
        //EditText nombreIndicente = (EditText) getActivity().findViewById(R.id.txtNombreInd);
        // EditText ServicioAfectado = (EditText) getActivity().findViewById(R.id.txtServicio);

        // EditText descripcionIndi = (EditText) getActivity().findViewById(R.id.txtDescrip);




        TicketDAO dao = new TicketDAO(getActivity().getBaseContext());
        try {
            //dao.eliminarTodos();

            dao.insertar(Contacto,nombreIndicente, ServicioAfectado,urgencia,
                    descripcionIndi,"Prueba",fechaCrea,1,1,"INCIDENTE");

            Toast toast= Toast.makeText(getActivity().getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


            //Imagen.setText("");




        } catch (DAOException e) {
            Log.i("Fragment01", "====> " + e.getMessage());
        }
    }


    public void update(String Contacto,String nombreIndicente,String ServicioAfectado,String urgencia,
                       String descripcionIndi,String fechaCrea) {


        //EditText Contacto = (EditText) getActivity().findViewById(R.id.txtContacto);
        //EditText nombreIndicente = (EditText) getActivity().findViewById(R.id.txtNombreInd);
        // EditText ServicioAfectado = (EditText) getActivity().findViewById(R.id.txtServicio);

        // EditText descripcionIndi = (EditText) getActivity().findViewById(R.id.txtDescrip);




        TicketDAO dao = new TicketDAO(getActivity().getBaseContext());
        try {
            //dao.eliminarTodos();

            dao.update(Integer.valueOf(txtId.getText().toString()),Contacto,nombreIndicente, ServicioAfectado,urgencia,
                    descripcionIndi,"Prueba",fechaCrea,1,1,"INCIDENTE");

            Toast toast= Toast.makeText(getActivity().getApplicationContext(), "Se Actualizo correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


            //Imagen.setText("");




        } catch (DAOException e) {
            Log.i("Fragment01", "====> " + e.getMessage());
        }
    }



    public void grabarServicio(String Contacto,String nombreIndicente,String ServicioAfectado,String urgencia,
                               String descripcionIndi,String fechaCrea){



        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addFormDataPart("idTicket", "0")
                .addFormDataPart("contacto", Contacto)
                .addFormDataPart("nombreIndicente", nombreIndicente)
                .addFormDataPart("servicioAfectado", ServicioAfectado)
                .addFormDataPart("urgencia", urgencia)
                .addFormDataPart("descripcionIndi", descripcionIndi)
                .addFormDataPart("imagen", "Prueba")
                .addFormDataPart("idEstado", "1")
                .addFormDataPart("usuarioID", "eazoo15@gmail.com")
                .addFormDataPart("tipoTicket", "INCIDENTE")
                .build();

        Request request = new Request.Builder()
                .url("http://sistematickets.atwebpages.com/rest/index.php/inserticket")
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




    public void addItemsOnSpinner2(Spinner sp1) {



        List<String> list = new ArrayList<String>();
        list.add("Bajo");
        list.add("Medio");
        list.add("Alto");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(dataAdapter);
    }




}
