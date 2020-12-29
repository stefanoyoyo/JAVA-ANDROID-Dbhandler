package com.example.ament.dbhandler;

// CI SONO DELLE COSE DA FARE, SCRITTE IN FONDO A QUESTA CLASSE!

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import static com.example.ament.dbhandler.PermissionRequest.verifyStoragePermissions;
import static com.example.ament.dbhandler.showMessages.sceltaa;

public class MainActivity extends AppCompatActivity {

    // ******UTILITY PER LE CLASSI*******
    public static int countActivityStart = 0;

    String deviceLanguage;
    String CreateOrInsertDB;
    int languageIndex;  // 0= italiano  1= inglese

    //********ELEMENTI GRAFICI DEL LAYOUT activity_main********
    TextView textView2;
    TextView textView;
    public static ImageView imageView;
    private ConstraintLayout ConstraintLayout;


    //***CLASSES OBJECTS***
    private AndroidFileExistanceCheck a = new AndroidFileExistanceCheck();


    //********STRING LANGUAGES********
        String [] welcome = {
                "DBhandler è un semplice applicativo che permette di aggiornare un file database già" +
                        " esistente o di crearne (e quindi editarne) uno nuovo ","DBhandler is a simple" +
                        " application allowing the user to update an existing database or to create and " +
                        " edit it"
        };
        String [] verificaDatabase = {"Verifica controllo Database -> ","Database existance check"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //***INIT GRAPHIC VARIABLES***
        textView2 = (TextView) findViewById(R.id.textView);
        textView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
        ConstraintLayout =(ConstraintLayout)findViewById(R.id.constraintLayout);


        ///***INIT VARIABLES***
        showMessages showMessages = new showMessages ();
        boolean existsDatabase = false;
        boolean existCreateOrInsertDB = false;
        boolean existsFolder = false;
        boolean readWritePermissionGranted = false;


        //***SCELGO LA LINGUA***
        deviceLanguage = Locale.getDefault().getDisplayLanguage();
        show(deviceLanguage);
        if (deviceLanguage.equals("italiano")) {
            languageIndex = 0;
        } else {
            languageIndex = 1;
        }


        //***PATHS***
        String CreateOrInsertDBPath = Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/cacheFolder/CreateOrInsertDB.txt";

        //***FILES***
        File fileCreateOrInsertDBPath = new File(CreateOrInsertDBPath);

        //***--------------------PARTE L'APP--------------------***
        textView2.setText(welcome[languageIndex]);
        textView.setText(verificaDatabase[languageIndex]);

        existsDatabase = a.existsFile();
        existsFolder = a.existsFolder();
        existCreateOrInsertDB = a.existsFile(new File(CreateOrInsertDBPath));

        readWritePermissionGranted = verifyStoragePermissions(this);

        show("la cartella esiste? " + existsDatabase);
        show("Il file database esiste? " + existsFolder);
        show("Il file CreateOrInsertDB.txt esiste? " + existCreateOrInsertDB);
        show("permesso verificato? " + readWritePermissionGranted);


        if (!a.existsFile()) {
            imageView.setImageResource(R.drawable.x);
            if (!a.existsFolder() || !a.existsFile()) {
                showMessages.showMessageWithButtons("DATABASE NON TROVATO", "Vuoi creare un nuovo database " +
                        "vuoto oppure inserirne uno già esistente? \nSI = creo nuovo database \nNO = aspetto che " +
                        "venga inserito manualmente  ", MainActivity.this);

                String scelta = showMessages.getScelta();

                try {
                    a.createFolder();
                } catch (IOException e) { }
                show("Scelta: " + scelta);
                    int choice = sceltaa;
                show("CHOICE: " + choice);
            }
        }

        if (a.existsFile() && a.existsFolder()) {
            imageView.setImageResource(R.drawable.ok);

            Intent intent = new Intent(getApplicationContext(), DBactivity.class);
            finish();
            startActivity(intent);

            /*
            // *** IN QUESTO MODO NON CAMBIO ACTIVITY MA è ANCHE SCOMODO ***

            ConstraintLayout.removeAllViews();
            ConstraintLayout.setBackgroundResource(R.drawable.black_note);
            */
        }

    }

    //--------------------

    /**
     * This class permette di aggiornare un activity
     */
    public void updateActivity () {
        //***UPDATE ACTIVITY***
        int cont = 0;
        while (cont == 0) {
            show("MainActivity/updateActivity: AGGIORNO MainActivity");
            startActivity(getIntent());
            cont++;
        }
    }

    //--------------------

    public boolean fileRename (File oldFilePath, File newFilePath) {
        boolean success = oldFilePath .renameTo(newFilePath );

        if(success)
            System.out.println("file is renamed..");
        return success;
    }

    //********Utility methods********
    public static void show (Object el) {
        System.err.println("Element: " + el);
    }
}
/*
* CARTELLA: controlllo per prima cosa se esiste. Se non esiste, ne devo creare una, per evitare di
*           creare un file nella cartella /storage/emulated/0
* FILE:     controllo che nella cartella il file esista. Se non esiste, chiedo all'utente di
*           inserne uno nella cartella o se vuole crearne uno nuovo.
* CACHE FOLDER: questa cartella conterrà dei file di utility.
*       '--->   CreateOrInsertDB: file che indica se l'utente ha deciso di creare un nuovo file
*               o di usarne uno già pre esistente
*
*/

/*
*   COSE DA FARE:           1.  Far cancellare periodicamente tutte le tuple dal database supplementare
 *                          2.  Creare un database per le impostazioni, cosi che l'applicazionie se le ricodi!
 *                          3.  Completare la parte relativa alle opzioni presenti in NOTE SALVATE
*
*   COME HO FATTO SI CHE L'APPLICAZIONE FUNZIONASSE UTILIZZANDO UN DB ESTERNO:
*   -   Nel creare un database, se passo al costruttore della superclasse (il super)
*       solo il suo nome, verrà creato un DB nella cartella privata riservata ai dati
 *      dell'app in data/data. Non è possibile copiare file da quella cartella ne verso
 *      la memoria interna, ne verso la micro sd.
 *  -   Ho quindi creato due costruttori nella classe del database (solo MainDatabaseHandler
 *      perchè è la più importante) e nel secondo costruttore, ho richiato la superclasse salvando
 *      però il databse in /.../0/DBhandlerData (non fornendo una stinga contenente solo il nome del
 *      file, ma tutto il percorso)
 *  -   In tutte le classi dell'applicazione, ho eseguito le stesse operazioni su tutti e due i
 *      costruttori implementati.
 *      ORA FINALMENTE, ANCHE IN DISPOSITIVI SENZA ROOT, SARA' POSSIBILE UTILIZZARE UN DATABASE PREESISTENTE
 *      GIA' USATO DA QUALCHE ALTRO DISPOSITIVO! L'APP ESEGUIRA' MODIFICHE SIA SUL DATABASE INTERNO
 *      CHE SUL DATABASE PRESENTE NELLA CARTELLA  /.../0/DBhandlerData, E SAEA' QUINDI POSSIBILE COPIARE
 *      IL FILE DA QUELLA CARTELLA DAL DISPOSITIVO VECCHIO E METTERLO NELLA STESSA CARTELLA DEL DISPOSITIVO
 *      NUOVO!
*
*
*       MIGLIORIE DA FARE:
*
*       - in formatta testo, faccio in modo che alla sua pressione, la edittext vada da sola a capo             [OK]
*       - MESSAGGI PREDEFINITI: ci sono dei tasti che inseriscono testo predefinito nella edittext.
*       questi tasti saranno accessibili solo richiamando un activiti mediante l'inserimento di un
*       codice particolare all'interno dell'app in una activity apposita. (MOMENTANEAMENTE) bastera
*       flaggare l'opzione nelle impostazioni dell'app per settare visibili due button nascosti.
*       - MIEI INTERVENTI NELLE CONVERSAZIONI: sono caratterizzati da ("...") e devono essere colorati
*       di rosso
*       - DOPPIONI NEL DATABASE: ad ogni inserimento di nota nel DB, vdevo fare 2 query di cancellazione
*       perchè vengono sempre salvate 3 righe uguali"
*       - ENCRYPTION: voglio che ci sia nell'app un tool di criptaggio testo secondo algoritmi definiti
*       da me
*       - ANALISI DOPPI SPAZI: eliminare i doppi spazi tra le catene delle note.
*       - BOTTONE DELETE CATENE: vengono eliminate delle date con catene inserite per errore
*
*/