package com.example.ament.dbhandler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class NoteSalvateNelDatabase extends ListActivity {

    Button button27;
    Button button28;
    Button button29;
    Button button30;
    Button button31;
    Button button32;
    Button button33;
    Button button34;
    ListView list;

    String ID_from_setOnItemClickListener;

    public static String item;
    public static int pos;
    public static String[] lineeIntere;

    MainDatabaseHandler db,db0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_salvate_nel_database);

        System.err.println("[1] note salate si APREEEEEEEEEEEEEEEEEEEEE");

        button27 = (Button) findViewById(R.id.button27);                //  CANCELLA RIGA
        button28 = (Button) findViewById(R.id.button28);                //  CANCELLA TUTTO
        button29 = (Button) findViewById(R.id.button29);                //  MODIFICA RIGA
        button30 = (Button) findViewById(R.id.button30);                //  ESPORTA NOTE
        button31 = (Button) findViewById(R.id.button31);                //  AVANTI
        button32 = (Button) findViewById(R.id.button32);                //  INDIETRO
        button33 = (Button) findViewById(R.id.button33);                //  INDIETRO
        button34 = (Button) findViewById(R.id.button34);                //  INDIETRO

        db = new MainDatabaseHandler(NoteSalvateNelDatabase.this,true);
        db0 = new MainDatabaseHandler(NoteSalvateNelDatabase.this);

        list = (ListView) findViewById(android.R.id.list);
        Cursor res = null;

        try {
            res = db.getAllNote();
        } catch (IllegalStateException i) {
            System.err.println("ERROR: " + i);
        }
        StringBuffer  str = db.cursorToStringBuffer(res);               // CONVERTO L'OGGETTO str IN UN UNICA GRANDE
                                                                        // STRINGA, E POI UTILIZZO IL METODO split()
                                                                        // PER OTTENERE UN ARRAY DI STRINGHE

        String s []  = str.toString().split("------------------------------");
        for (int i = 0; i< s.length; i++) {
            if (s[i].length()>17) {
                s[i] = s[i].substring(0, 29);
            }
        }
        lineeIntere = str.toString().split("------------------------------");
        System.err.println("NoteSalvateNelDatabase/s.length: " + s.length );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,s);
        getListView().setAdapter(adapter);


        /*  ***LISTENERS***     */
        /*  CANCELLA RIGA       */
        button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                *   DA COMPLETARE. DEVE APPARIRE UNA FINESTRA DI DIALOGO CHE
                *   CHIEDE QUALE RIGA CANCELLARE INSERENDO L'ID. INSERIRE ANCHE
                *   UNA FINESTRA CHE CHIEDE LA CONFERMA.
                */
                Intent intent = new Intent(getApplicationContext(), EditOrDeleteRowOfNoteSalvateNelDatabase.class);
                startActivity(intent);
                //db.onDeleteData("0");
            }
        });

        //------------------------------
        /*  CANCELLA TUTTO  */

        button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  CREO UNA FINESTRA CHE CHIEDE CONFERMA, PER EVITARE CHE POSSA ESSERE ELIMINATO
                //  TUTTO PER ERRORE
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteSalvateNelDatabase.this);
                builder.setCancelable(true);
                builder.setTitle("Confermi cancellazione note");
                builder.setPositiveButton("SI",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        db.deleteAll();
                        Toast.makeText(getApplicationContext(),"SI: tutte le note sono state eliminate dal database", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"NO: nessuna nota eliminata dal database", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setMessage("Confermi la cancellazione di tutte le note presenti nel DATABASE? Questa operazione è irreversibile");
                builder.show();
            }

        });

        //-----------------------------------------
    /*  ON ITEM CLICK LISTENER: VOGLIO CHE QUANDO VIENE PREMUTO UN ITEM DELLA LIST VIEW ACCADA QUALCOSA */

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    //         Toast.makeText(getApplicationContext(),"int position = " + position+1 + "\nlong id = " + id,Toast.LENGTH_SHORT).show();
                    item = (String) list.getItemAtPosition(position);
                    Utility.show("item: " + item);
                    MainDatabaseHandler d0 = new MainDatabaseHandler(NoteSalvateNelDatabase.this, true);
                    MainDatabaseHandler d1 = new MainDatabaseHandler(NoteSalvateNelDatabase.this, true);
                    //         SQLiteDatabase db =  SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/MainDatabaseHandler.db", null,0);
                    //         SQLiteDatabase db1 = SQLiteDatabase.openDatabase("/data/data/com.example.ament.dbhandler/databases/MainDatabaseHandler.db",null,0);
                    Cursor res = d1.getIDandNote();
                    Utility.show("str      -->  ");
                    res.moveToFirst();
                    StringBuffer s = d1.cursorToStringBuffer_0(res);                 // select id,nota_memorizzata from NOTES_TABLE
                    String ss = s.toString();
                    String arrs[] = ss.split("------------------------------");    // PER OTTENERE L'ID, SPLITTO L'ARRAY PER OTTENERE LA STRINGA.
                    Integer n = 0;
                    int z = (int) id;                                  // POSSO COSI POI OTTENERE L'ID E LA NOTA MEMORIZZATA DI OGNI TUPLA IN
                    if (z > 0) {                                                      // IN OGNI INDICE DELL'ARRAY. POSSO COSI SPLITTARE ULTERIORMENTE OGNI
                        Utility.show(arrs[z - 1]);
                    }                               // INDICE DELL'ARRAY PER OTTENERE POI L'INDICE DELLA TUPLA NEL DATABASE
                    String IDsplit[] = arrs[z - 1].split("--------");                // NON POSSO SOLO OTTENERE L'ID DELLA PRIMA TUPLA.. *** RISOLVERE ***
                    Utility.show("IDsplit[0] " + IDsplit[0]);
                    Utility.show("IDsplit[1] " + IDsplit[1]);


                    String ID_OF_TUPLA_TO_DELETE = IDsplit[0];
                    ID_from_setOnItemClickListener = ID_OF_TUPLA_TO_DELETE;

                    //     d0.onDeleteData(ID_OF_TUPLA_TO_DELETE);
                    //     d1.onDeleteData(ID_OF_TUPLA_TO_DELETE);
                    //     d1.deleteLineByID("delete from NOTES_TABLE where id = ", ID_OF_TUPLA_TO_DELETE);

                    /*
                    //  QUI CERCHERO' DI TROVARE IL MODO DI RIUSCIRE AD AVERE ANCHE L'ID DELLA PRIMA LINEA.
                    //  LO FARO' INCERENDO DUE RISULTATI ALL'INTERNO DI UN CURSOR (VISTO CHE PURTROPPO, FACENDO
                    //  UNA QUERY CHE RITORNA UN SOLO RISULTATO, IL CURSOR NON LO MOSTRA, MENTRE SE INVECE LA QUERY
                    //  RITORNA PIU RISULTATI INVECE LO FA). SCEGLIERO' POI L'ID DELLA PRIMA STRINGA PRESENTE NEL
                    //  CURSOR (res.getString(0) ) ET VOILA'..

                    Utility.show("\n\nbla bla -> " + IDsplit[1].substring(13, IDsplit[1].length()));
                    String xx = IDsplit[1].substring(13, IDsplit[1].length());
                    String IDsplit1 [] = arrs[z].split("--------");
                    String yy = IDsplit1[1].substring(13, IDsplit[1].length());
                    Utility.show("\n\nciao ciao -> " + IDsplit1[1].substring(13, IDsplit[1].length()));

                    Cursor cc = d0.queryIDfromStringaMemorizzata(xx);
                    StringBuffer dd = d0.cursorToStringBuffer(cc);
                    Utility.show("dd --> " + dd);
                    */

                    Toast.makeText(getApplicationContext(), "ID NOTA NELLA TABELLA DEL DB " + ID_OF_TUPLA_TO_DELETE, Toast.LENGTH_LONG).show();
                    // CAMBIO SCHERMATA PER MOSTRARE LA NOTA INTERA IN MOSTRA NOTA SELEZIONATA
                    Intent intent = new Intent(getApplicationContext(), MostraNotaSelezionata.class);
                    startActivity(intent);
                    pos = position;
                } catch (Exception exc) {
                             Toast.makeText(getApplicationContext(),"IMPOSSIBILE ELIMINARE PRIMA LINEA DELLA TABELLA DEL DB AL MOMENTO" + id,Toast.LENGTH_SHORT).show();

                }

            }
        });

        //-----------------------------------------

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                try {
                    MainDatabaseHandler d0 = new MainDatabaseHandler(NoteSalvateNelDatabase.this, true);
                    MainDatabaseHandler d1 = new MainDatabaseHandler(NoteSalvateNelDatabase.this, true);
                    //         SQLiteDatabase db =  SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/MainDatabaseHandler.db", null,0);
                    //         SQLiteDatabase db1 = SQLiteDatabase.openDatabase("/data/data/com.example.ament.dbhandler/databases/MainDatabaseHandler.db",null,0);
                    Cursor res = d1.getIDandNote();
                    Utility.show("str      -->  ");
                    res.moveToFirst();
                    StringBuffer s = d1.cursorToStringBuffer_0(res);                 // select id,nota_memorizzata from NOTES_TABLE
                    String ss = s.toString();
                    String arrs[] = ss.split("------------------------------");    // PER OTTENERE L'ID, SPLITTO L'ARRAY PER OTTENERE LA STRINGA.
                    Integer n = 0;
                    int z = (int) id;                                  // POSSO COSI POI OTTENERE L'ID E LA NOTA MEMORIZZATA DI OGNI TUPLA IN
                    if (z > 0) {                                                      // IN OGNI INDICE DELL'ARRAY. POSSO COSI SPLITTARE ULTERIORMENTE OGNI
                        Utility.show(arrs[z - 1]);
                    }                               // INDICE DELL'ARRAY PER OTTENERE POI L'INDICE DELLA TUPLA NEL DATABASE
                    String IDsplit[] = arrs[z - 1].split("--------");                // NON POSSO SOLO OTTENERE L'ID DELLA PRIMA TUPLA.. *** RISOLVERE ***
                    Utility.show("IDsplit[0] " + IDsplit[0]);
                    Utility.show("IDsplit[1] " + IDsplit[1]);


                    String ID_OF_TUPLA_TO_DELETE = IDsplit[0];
                    ID_from_setOnItemClickListener = ID_OF_TUPLA_TO_DELETE;
                    Toast.makeText(getApplicationContext(), "ID NOTA NELLA TABELLA DEL DB " + ID_from_setOnItemClickListener, Toast.LENGTH_LONG).show();
                } catch (Exception exc) {
                    Toast.makeText(getApplicationContext(),"IMPOSSIBILE ELIMINARE PRIMA LINEA DELLA TABELLA DEL DB AL MOMENTO" + id,Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });
        //------------------------------
        /*  MODIFICA RIGA  */

        button29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                *   PER MODIFICARE LA RIGA, DEVO CHIEDERE ALL'UTENTE L'ID DI QUALE RIGA
                *   MODIFICARE. FATTO CIO', ESEGUIRO' LA QUERY SQLite CORRISPONDENTE,
                *   PER DISPORRE DELLA STRINGA DA MODIFICARE. LA METTERO' INFINE NELLA
                *   EDITTEXT, DOVE L'UTENTE POTRA' MODIFICARLA. UNA VOLTA FINITO, EGLI
                *   DOVRA' ANDARE SALVARE. PER SALVARE IL CAMBIAMENTO, EFFETTUERO' UNA QUERY
                *   DI UPGRADE UTILIZZANDO COME ID QUELLO DELLA TUPLA DA MODIFICARE.
                */
                showMessages showMessages = new showMessages ();
                showMessages.showMessageWithButtons("LINEA DA MODIFICARE","Quale linea vuoi modificare? ", NoteSalvateNelDatabase.this);
            }
        });

        /*-----ESPORTA NOTE-----*/
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File path = Environment.getExternalStorageDirectory(); // /storage/emulated/0
                final String fileName = "conversazioni.txt";
                final String folderName = "DBhandlerData";

                // creo il file con il path specificato
                File note = new File(path + "/"+folderName + "/" + fileName);
                try {
                    note.createNewFile();
                } catch (IOException e) {}

                // seleziono tutte le note dal database e creo una stringa da inserire nel file
                MainDatabaseHandler a = new MainDatabaseHandler(getBaseContext());
                Cursor allNote = a.getAllNote();
           //     StringBuffer str = a.viewAllNote(allNote);

            }
        });



    }



}
