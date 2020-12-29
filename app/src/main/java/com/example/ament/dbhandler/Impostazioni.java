package com.example.ament.dbhandler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Impostazioni extends Activity {

    CheckBox dataAllInizioDelTesto;
    CheckBox superUser;
    Button button17;
    Button button18;

    Impostazioni_DATABASE db;
    Impostazioni_DATABASE dbExternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);

        dataAllInizioDelTesto = (CheckBox) findViewById(R.id.checkBox);
        superUser = (CheckBox) findViewById(R.id.checkBox2);
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);

        db = new Impostazioni_DATABASE(Impostazioni.this);
        dbExternalStorage = new Impostazioni_DATABASE(Impostazioni.this, true); // creo un database anche nella cartella
                                                                                // accessibile all'utente senza permessi di root
        StringBuffer str = db.cursorToStringBuffer(db.read_Data_a_inizio_nota());

        /* CONTROLLO SE setSecretButtonsVisibility SIA GIA STATA FLAGGATA*/
        if (DBactivity.setSecretButtonsVisibility) {
            superUser.setChecked(true);
        }

        if ((db.cursorToStringBuffer(db.read_Data_a_inizio_nota()).equals("res"))) {        // se lo stato della checkbox è indefinito
            dataAllInizioDelTesto.setChecked(true);
            // AGGIORNO LO STATO DELLA RIGA CHE CONTIENE IL VALORE CHE INDICA
            // SE LA CHECKBOX E' STATA CLICCATA O MENO.
            // 1 = CHECKBOX CLICCATA
            // 0 = CHECKBOX NON CLICCATA
        } else if ((db.cursorToStringBuffer(db.read_Data_a_inizio_nota()).equals("1"))) {
            dataAllInizioDelTesto.setChecked(true);
        } else if ((db.cursorToStringBuffer(db.read_Data_a_inizio_nota()).equals("0"))) {
            dataAllInizioDelTesto.setChecked(false);
        }

        //--------------------
        /*      BUTTON18 LISTENER*/
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoseDaRicordare.class);
                startActivity(intent);
            }
        });

        //------------------------------


        dataAllInizioDelTesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataAllInizioDelTesto.isChecked()) {
                    Toast.makeText(getApplicationContext(), "CLICCATO: la data verrà inserita all'inizio di ogni nota", Toast.LENGTH_SHORT).show();
                    DataEora d = new DataEora() ;
                    String dataEora = d.getData() + " " + d.getOra()+"\n\n- ";
                    DBactivity.editText.setText(dataEora+"");
                    /*
                    *       *****************
                    *   DEVO CREARE UN DATABASE PER LE IMPOSTAZIONI PER FAR SI CHE L'APP SI RICORDI
                     *  CHE LA CKECKBOX E' STATA CLICCATA, SENNO' AD OGNI RIAVVIO DELL'ACTIVITY
                     *  IL CHECK VIENE RIMOSSO
                     *      *****************
                     *
                     *      database creato, gli inserimenti funzionano nonostante appaia sempre un'eccezione strana,
                     *      devo solo ancora riuscire a vedere il boooleano salvato nel db e usarlo
                    */
                    Impostazioni_DATABASE db = new Impostazioni_DATABASE(Impostazioni.this);    // creo il database
                    Cursor res = db.read_Data_a_inizio_nota();                                  // ottengo il dato sotto forma di cursor
                    StringBuffer str = db.cursorToStringBuffer(res);                            // trasormo da cursor a StringBuffer
                    String dato  = res.toString();                                              // ottengo la stringa dal cursore
                    String data = str.toString();
                    dbExternalStorage.insertData("true");
           //       dbExternalStorage.cursorToStringBuffer(res);
                    System.err.println("[mom] " + dbExternalStorage.getLastDataAllinizoDellaNota().getString(1));

                } else {
                    Toast.makeText(getApplicationContext(), "NON CLICCATO: la data non sarà presente all'inizio di ogni nota", Toast.LENGTH_SHORT).show();
                    DBactivity.editText.setText("");
                    dbExternalStorage.insertData("false");
                }
            }
        });


        superUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      button17.setVisibility(View.VISIBLE);
          //      button18.setVisibility(View.VISIBLE);
                if (DBactivity.setSecretButtonsVisibility) {
                    DBactivity.setSecretButtonsVisibility = false;
                    Toast.makeText(getApplicationContext(), "FALSE", Toast.LENGTH_SHORT).show();
                } else {
                    DBactivity.setSecretButtonsVisibility = true;
                    Toast.makeText(getApplicationContext(), "TRUE", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
