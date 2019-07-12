package com.example.sistematicketv2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment04 extends Fragment {

    private  EditText txtId;
    private EditText txtUrgencia;
    private Button btnBuscar;
    private Button btnEliminar2;
    private Spinner spinner1, spinner2;
    private ListView listaResultados;
    private ArrayList<Ticket> resultados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment04,container,false);

        txtId = (EditText) view.findViewById(R.id.txtId);
        txtUrgencia = (EditText) view.findViewById(R.id.txtId);
        btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        btnEliminar2 = (Button) view.findViewById(R.id.btnEliminar2);
        spinner2 = (Spinner) view.findViewById(R.id.sp2);

        listaResultados = (ListView) view.findViewById(R.id.listaResultados);

        addItemsOnSpinner2(spinner2);









        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                String Urgencia = String.valueOf(spinner2.getSelectedItem());


                buscar(id,Urgencia,listaResultados);



            }
        });

        btnEliminar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(Integer.valueOf(txtId.getText().toString()));

                buscar("","",listaResultados);

                txtId.setText("");
            }
        });


        listaResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Ticket p = resultados.get(position);
                Toast toast= Toast.makeText(getActivity().getApplicationContext(),"id:  :" + p.getIdTicket()+" *Nombre Incidente : "+ p.getNombreIndicente(),Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();




                Bundle bundle = new Bundle();
                bundle.putInt("id",p.getIdTicket());
                bundle.putString("contacto",p.getContacto());
                bundle.putString("objetoData",p.getNombreIndicente());
                bundle.putString("servicioAfectado",p.getServicioAfectado());
                bundle.putString("descripcion",p.getDescripcionIndi());
                //txtId.setText(p.getTipoTicket());

                if(p.getTipoTicket().equals("INCIDENTE"))
                {
                    Fragment01 myFrag = new Fragment01();
                    myFrag.setArguments(bundle);

                    FragmentManager manager= getFragmentManager();
                    manager.beginTransaction().replace(R.id.contenedor,myFrag).commit();

                }else if(p.getTipoTicket().equals("REQUERIMIENTO"))
                {
                    Fragment02 myFrag = new Fragment02();
                    myFrag.setArguments(bundle);

                    FragmentManager manager= getFragmentManager();
                    manager.beginTransaction().replace(R.id.contenedor,myFrag).commit();

                }else if(p.getTipoTicket().equals("CONSULTA"))
                {

                    Fragment03 myFrag = new Fragment03();
                    myFrag.setArguments(bundle);

                    FragmentManager manager= getFragmentManager();
                    manager.beginTransaction().replace(R.id.contenedor,myFrag).commit();

                }






            }
        });






        // Inflate the layout for this fragment
        return view;
    }



    public void buscar(String criterio1, String Criterio2,ListView listaResultados2) {
        //Editable criterio = (Editable) findViewById(R.id.criterio);

        TicketDAO dao = new TicketDAO(getActivity().getBaseContext());
        try {
            resultados = dao.buscar(criterio1,Criterio2);



            String[] encontrados = new String[resultados.size()];
            int i = 0;
            for (Ticket gm : resultados){
                encontrados[i++] = "*Id: "+gm.getIdTicket() + gm.getContacto() +gm.getServicioAfectado() +" *Urgencia :"+gm.getUrgencia() + " *Nombre Incidente : "+
                        gm.getNombreIndicente()+ " *Descrip : "+gm.getDescripcionIndi() + " *Tipo TIcket : "+ gm.getTipoTicket();
            }

            Log.i("Ingreso", "====> " );
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    encontrados);

            ListView listaResultados = listaResultados2;
            listaResultados.setAdapter(adaptador);


        } catch (DAOException e) {
            Log.i("TicketDao", "====> " + e.getMessage());
        }
    }


    public void addItemsOnSpinner2(Spinner sp1) {



        List<String> list = new ArrayList<String>();
        list.add("");
        list.add("Bajo");
        list.add("Medio");
        list.add("Alto");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(dataAdapter);
    }


    public void eliminar(int id) {


        //EditText Contacto = (EditText) getActivity().findViewById(R.id.txtContacto);
        //EditText nombreIndicente = (EditText) getActivity().findViewById(R.id.txtNombreInd);
        // EditText ServicioAfectado = (EditText) getActivity().findViewById(R.id.txtServicio);

        // EditText descripcionIndi = (EditText) getActivity().findViewById(R.id.txtDescrip);




        TicketDAO dao = new TicketDAO(getActivity().getBaseContext());
        try {
            //dao.eliminarTodos();

            dao.eliminar(id);

            Toast toast= Toast.makeText(getActivity().getApplicationContext(), "Se Elimino correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


            //Imagen.setText("");




        } catch (DAOException e) {
            Log.i("Fragment02", "====> " + e.getMessage());
        }
    }







}