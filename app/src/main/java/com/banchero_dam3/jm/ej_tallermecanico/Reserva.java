package com.banchero_dam3.jm.ej_tallermecanico;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

public class Reserva extends AppCompatActivity {
    private Spinner spEncargo, spAseguradoras;
    private EditText etOtros;
    private CheckBox chkAseguradora;
    private Button btnConfirmar;
    private String [] tipoEncargos ={"seleccionar" ,"cambio de aceite", "revisión general", "chapa y pintura", "reparación de siniestro", "otro"};
    private String [] listaAseguradoras ={"Axa","Catalana Occidente", "Regal", "Mafre", "Línea Directa","Verti"};
   // private String matriculaReserva,nombreReserva,otrosReserva,telefonoReserva,coche,encargoReserva,aseguradoraReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        etOtros = (EditText) findViewById(R.id.editTextOtroEncargo);
        //etOtros.setEnabled(false);

        spEncargo= (Spinner) findViewById(R.id.spinnerTipoEncargo);
ArrayAdapter<String> adapterEncargo =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipoEncargos);
       spEncargo.setAdapter(adapterEncargo);

        spAseguradoras= (Spinner) findViewById(R.id.spinnerAseguradoras);
ArrayAdapter<String> adapterAseguradoras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaAseguradoras);
       spAseguradoras.setAdapter(adapterAseguradoras);

        chkAseguradora = (CheckBox)findViewById(R.id.checkBoxPendiente);
        chkAseguradora.setEnabled(false);

        btnConfirmar = (Button) findViewById(R.id.buttonConfirmarReserva);
        btnConfirmar.setEnabled(false);

        spEncargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                //  String place= parent.getItemAtPosition(pos).toString();
                String valor = parent.getSelectedItem().toString();
                int select = parent.getSelectedItemPosition();

                //Toast.makeText(MainActivity.this, "seleccionado " + valor, Toast.LENGTH_SHORT).show();
                //View v = findViewById(R.id.editTextOtroEncargo);
                if (valor.equals("otro")) {
                    //View v = findViewById(R.id.editTextOtroEncargo);
                    //v.setVisibility(v.VISIBLE);
                    etOtros.setEnabled(true);
                    etOtros.setVisibility(View.VISIBLE);
                    //etOtros.setText("indicar por favor");
                    //etOtros.setText(getResources().getString(R.string.lblotroSlect));

                } else {
                    //v.setVisibility(v.INVISIBLE);
                    etOtros.setEnabled(false);
                    etOtros.setVisibility(View.INVISIBLE);
                }


                switch (select) {

                    case 0:
                        btnConfirmar.setEnabled(false);
                        etOtros.setText("");
                        chkAseguradora.setChecked(false);
                        chkAseguradora.setEnabled(false);
                        chkAseguradora.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        spAseguradoras.setEnabled(false);
                        spAseguradoras.setVisibility(View.INVISIBLE);

                        break;
                    case 1:
                    case 2:
                    case 5:
                        btnConfirmar.setEnabled(true);
                        etOtros.setText("");
                        chkAseguradora.setEnabled(false);
                        chkAseguradora.setChecked(false);
                        chkAseguradora.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        spAseguradoras.setEnabled(false);
                        break;

                    case 3:
                    case 4:
                        etOtros.setText("");
                        chkAseguradora.setEnabled(true);
                        btnConfirmar.setEnabled(true);
                        chkAseguradora.setTextColor(getResources().getColor(R.color.colorAccent));
                        if (chkAseguradora.isChecked()){

                            spAseguradoras.setEnabled(true);
                        }else{
                            spAseguradoras.setEnabled(false);
                        }

                        break;
                }

            }


            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing, just another required interface callback
            }

        });
            //check aseguradoras
        spAseguradoras.setEnabled(false);

        chkAseguradora.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View view = findViewById(R.id.spinnerAseguradoras);
                if (isChecked) {

                    view.setVisibility(View.VISIBLE);
                    chkAseguradora.setTextColor(getResources().getColor(R.color.colorAccent2));
                    spAseguradoras.setEnabled(true);
                } else {
                    view.setVisibility(View.INVISIBLE);
                    spAseguradoras.setEnabled(false);
                    chkAseguradora.setTextColor(getResources().getColor(R.color.colorAccent));
                    //spAseguradoras.setSelection(0);
                }
            }
        });





    }
/*
    public void otros(){

        etOtros = (EditText) findViewById(R.id.editTextOtroEncargo);
        String selec = spEncargo.getSelectedItem().toString();

        if (selec.equals("otro") ){

            etOtros.setEnabled(true);
            etOtros.setText("indicar por favor");

        }else{
            etOtros.setEnabled(false);
        }
    }
*/


    public void confirmarReserva(View view) {
        //obtener los datos del bundle
        Bundle bundle = getIntent().getExtras();
        String nombreReserva = bundle.getString("nombre").toString();
        String telefonoReserva = bundle.getString("telefono").toString();
        String matriculaReserva = bundle.getString("matricula").toString();
        String coche = bundle.getString("coche");
        String encargoReserva = spEncargo.getSelectedItem().toString();
        String aseguradoraReserva = spAseguradoras.getSelectedItem().toString();
        String otrosReserva = etOtros.getText().toString().trim();
        //contruir el mensaje de salida
        String s = (new StringBuilder()).append(getResources().getString(R.string.mensaje1)).append(" A nombre de "+ nombreReserva.toUpperCase()+"\n").toString();
        String s1 = (new StringBuilder()).append(s).append("Su coche: ").append(coche).append(" con matrícula :").append(matriculaReserva.toUpperCase() + "\n").toString();
        String s2 = (new StringBuilder()).append(s1).append("\nEncargo : ").toString();
        String s3;
        String s4;

        if("otro".equals(encargoReserva))
        {
            if("".equals(otrosReserva))
                otrosReserva = "¡CONTACTAREMOS CON UD!";
            s3 = (new StringBuilder()).append(s2).append(otrosReserva).append("\n").toString();
        } else
        {
            s3 = (new StringBuilder()).append(s2).append(encargoReserva).append("\n").toString();
        }
        s4 = (new StringBuilder()).append(s3).append(getResources().getString(R.string.mensaje2)).append(" al teléfono :").append(telefonoReserva).toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s4);
        builder.setPositiveButton("aceptar", null);
        builder.show();
    }
}
