package com.banchero_dam3.jm.ej_tallermecanico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnReserva;
    private EditText etNombre,etTelf,etMatricula;
    private Spinner sMarcas,sModelos;
    //private String [] Marcas = {"Audi","Peugot","Renault"};
    //private String [] Modelos = {"A2","A3","A4"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       btnReserva = (Button)findViewById(R.id.buttonConfirmarReserva);

        etNombre = (EditText) findViewById(R.id.editTextNombre);
        etTelf =(EditText) findViewById(R.id.editTexttelefono);
        etMatricula =(EditText) findViewById(R.id.editTextMatricula);

        sMarcas = (Spinner) findViewById(R.id.spinnerMarca);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marcas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sMarcas.setAdapter(adapter);
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);


        sMarcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                int select = parent.getSelectedItemPosition();
                switch (select) {
                    case 0:
                        modelos();
                        break;
                    case 1:
                        modelosAudi();
                        break;
                    case 2:
                        modelosCitroen();
                        break;
                    case 3:
                        modelosPeugot();
                        break;
                    case 4:
                        modelosRenault();
                        break;

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing, just another required interface callback
            }

        });

    }

    //metodos para llenar cada spinner de Modelos
    public void modelos(){
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);
        ArrayAdapter adapterA = ArrayAdapter.createFromResource(this, R.array.modelos, android.R.layout.simple_spinner_item);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sModelos.setAdapter(adapterA);
    }

    public void modelosAudi(){
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);
        ArrayAdapter adapterA = ArrayAdapter.createFromResource(this, R.array.mAudi, android.R.layout.simple_spinner_item);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sModelos.setAdapter(adapterA);
    }
    public void modelosCitroen(){
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);
        ArrayAdapter adapterA = ArrayAdapter.createFromResource(this, R.array.mCitroen, android.R.layout.simple_spinner_item);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sModelos.setAdapter(adapterA);
    }
    public void modelosPeugot(){
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);
        ArrayAdapter adapterA = ArrayAdapter.createFromResource(this, R.array.mPeugot, android.R.layout.simple_spinner_item);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sModelos.setAdapter(adapterA);
    }
    public void modelosRenault(){
        sModelos = (Spinner) findViewById(R.id.spinnerModelo);
        ArrayAdapter adapterA = ArrayAdapter.createFromResource(this, R.array.mRenault, android.R.layout.simple_spinner_item);
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sModelos.setAdapter(adapterA);
    }

    public void reservarOk (View v){

        if (etNombre.getText().toString().trim().equals("")){
            Toast.makeText(MainActivity.this, "Debe ingresar su nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etTelf.getText().toString().trim().equals("")){
            Toast.makeText(MainActivity.this, "Debe ingresar su telefóno", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etMatricula.getText().toString().trim().equals("")){
            Toast.makeText(MainActivity.this, "Ingrese su matrícula, por favor", Toast.LENGTH_SHORT).show();
            return;
        }



            if (  (!etNombre.getText().toString().trim().equals(""))
                && ( !etTelf.getText().toString().trim().equals(""))
                && ( !etMatricula.getText().toString().trim().equals("") ) )
        {

            Intent intent = new Intent(MainActivity.this, Reserva.class);
            Bundle bundle = new Bundle();
            String marca = sMarcas.getSelectedItem().toString();
            String modelo = sModelos.getSelectedItem().toString();


            if ("Marca".equals(marca)){
                 textoVacio();
                return;
            }
            //capturar datos
            bundle.putString("nombre", etNombre.getText().toString());
            bundle.putString("telefono", etTelf.getText().toString());
            bundle.putString("matricula", etMatricula.getText().toString());
            bundle.putString("coche", marca + " " + modelo);

            //enviar al intent
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }


    //para verificar si el campo está vacío
    public void textoVacio(){

        CharSequence mensaje = getResources().getString(R.string.alertaMarca);
        AlertDialog.Builder alertaMarca = new AlertDialog.Builder(this);
        alertaMarca.setMessage(mensaje);
        alertaMarca.setPositiveButton("entendido", null);
        alertaMarca.show();

    }


}
/*
* //ArrayAdapter<CharSequence> adapterAudi = ArrayAdapter.createFromResource( this.getBaseContext(),
    //        R.array.mAudi, android.R.layout.simple_spinner_item);
      //adapter = ArrayAdapter.createFromResource(this, R.array.Games,android.R.layout.simple_spinner_item);


                        // Retrieves an array
                        //TypedArray arrayAudi = getResources().obtainTypedArray(R.array.mAudi);
                        //CharSequence[] modelosAudi = arrayAudi.getTextArray(pos);
                        //arrayAudi.recycle();

                        // Create an ArrayAdapter using the string array and a default
                        // spinner layout
                        // ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, modelosAudi );
                        //ArrayAdapter<String> adapterAseguradoras = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, R.array.mAudi);
                        // Specify the layout to use when the list of choices appears
                        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Apply the adapter to the spinner
                        //this.spLocalidades.setAdapter(adapter);

          etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                enableSubmitIfReady();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

           void watcher(final EditText message_body,final Button Send)
{
    final TextView txt = (TextView) findViewById(R.id.txtCounter);
    message_body.addTextChangedListener(new TextWatcher()
    {
        public void afterTextChanged(Editable s)
        {
            txt.setText(message_body.length() + " / 160"); //This is my textwatcher to update character left in my EditText
            if(message_body.length() == 0)
                Send.setEnabled(false); //disable send button if no text entered
            else
                Send.setEnabled(true);  //otherwise enable

        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }
        public void onTextChanged(CharSequence s, int start, int before, int count){
        }
    });
    if(message_body.length() == 0) Send.setEnabled(false);//disable at app start
}

         public void enableSubmitIfReady(){

        boolean isReady =etNombre.getText().toString().length()>1;
        btnReserva.setEnabled(isReady);
    }
                        */


