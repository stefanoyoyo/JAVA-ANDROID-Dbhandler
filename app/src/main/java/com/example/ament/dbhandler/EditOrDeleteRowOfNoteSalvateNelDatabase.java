package com.example.ament.dbhandler;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  COME CREARE UNA LISTVIEW:
 *      |
 *      '-> XML:                creo una listview nel documento XML, assicurandomi di darle una ID
 *      |                       inserire come ID nel file XML android:id="@id/android:list"
 *      |
 *      '-> JAVA:               creo un nuovo array di stringhe nel quale inserisco le stringhe che
 *      |                       dovranno comparire nella listview
 *      |
 *      '-> ARRAYADAPTER:       instanzio la classe ArrayAdapter di tipo parametrico <String> a cui
 *      |                       passo come parametri getListView().getContext(), android.R.layout.tipoDiListaDaCreare
 *      |                       (da ricordare che il tipo di lista è una costante) e l'array di stringhe
 *      |                       creato precedentemente
 *      |
 *      '-> INFINE:             collegare la listView con l'adapter digitando:
 *                              getListView().setAdapter(adapter)    con adapter = nome dato all'arrayadapter
 *
 *
 */
public class EditOrDeleteRowOfNoteSalvateNelDatabase extends ListActivity {

    ListView listView;
    EditText editText4;
    Button button17;
    TextView textView7;

    MainDatabaseHandler db,db0;
    long arrayAdapterIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete_row_of_note_salvate_nel_database);


        String [] howToSelectRowsFromTheSQLiteTable = {"ID","DATA","ORA"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,howToSelectRowsFromTheSQLiteTable);
        getListView().setAdapter(adapter);

        editText4 = (EditText) findViewById(R.id.editText4);
        button17 = (Button) findViewById(R.id.button17);
        textView7 = (TextView) findViewById(R.id.textView7);
        db0 = new MainDatabaseHandler(EditOrDeleteRowOfNoteSalvateNelDatabase.this);
        db = new MainDatabaseHandler(EditOrDeleteRowOfNoteSalvateNelDatabase.this,true);


        /*  ***LISTENERS***     */
        /*  CANCELLA RIGA SELEZIONATA      */

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.onDeleteData("0");
                String e = editText4.getText().toString();
                if (arrayAdapterIndex != -1) {
                 //   Toast.makeText(getApplicationContext(),"arrayAdapterIndex = " + arrayAdapterIndex, Toast.LENGTH_SHORT).show();
                    if (arrayAdapterIndex == 0) {
                        db.onDeleteData(e);
                        db0.onDeleteData(e);
                    } else if (arrayAdapterIndex == 1) {
                        if (e.contains("/")) {
                            MainDatabaseHandler db = new MainDatabaseHandler(EditOrDeleteRowOfNoteSalvateNelDatabase.this);
                            db.onDeleteData(e);
                            db0.onDeleteData(e);
                        } else {
                            Toast.makeText(getApplicationContext(),"ERRORE: inserisci una data", Toast.LENGTH_SHORT).show();

                        }
                        //db.onDeleteData(e);
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"ERRORE: inserisci qualcosa nel campo di testo", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //------------------------------
    /*      LISTENER PER LA LISTVIEW CHE CONTIENE L'ARRAYADAPTER        */

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //do something here using the position in the array
        arrayAdapterIndex = id;
        textView7 = (TextView) findViewById(R.id.textView7);
        if (id == 0) {
            textView7.setText("ID");
        } else if (id == 1) {
            textView7.setText("DATA");
        } else if (id == 2) {
            textView7.setText("ORA");
        }
    }
}
