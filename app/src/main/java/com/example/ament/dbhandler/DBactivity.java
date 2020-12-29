package com.example.ament.dbhandler;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DBactivity extends Activity {

    public static EditText editText;
    Button save;
    Button settings;
    Button exit;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;
    Button button18;
    Button button19;
    Button button21;
    Button button22;
    Button button40;
    Button button41;
    Button button50;
    Button button51;
    Button button52;

    // PULSANTI SEGRETI
    Button button53;        // Buonanotte
    Button button54;        // Encryption
    Button button23;        // Buongiorno 1
    Button button24;        // Buongiorno 2
    Button button25;        // Buongiorno 3
    Button button26;        // Buongiorno 4
    Button button27;        // Buongiorno 5
    Button button28;        // Buongiorno 6
    Button button29;        // Buongiorno 7
    Button button30;        // Buongiorno 8

    Button button31;        // Buonanotte 1
    Button button32;        // Buonanotte 2
    Button button33;        // Buonanotte 3
    Button button34;        // Buonanotte 4
    Button button35;        // Buonanotte 5
    Button button36;        // Buonanotte 6
    Button button37;        // Buonanotte 7
    Button button38;        // Buonanotte 8


    int coloreAzzuro = 0x00bfff;
    int coloreRosso = 0xFF0000;


    private int resumeCount = 0;

    Boolean abilitaOdisabilitaTastiera = true;
    static boolean setSecretButtonsVisibility = false;
    String suGiuHelper = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        editText = (EditText) findViewById(R.id.editText);

        editText.setCursorVisible(true);    // il cursore si vede
        editText.setSelection(0);           // la editText parte dall'inizio
        editText.setSingleLine(false);      // faccio si che il cursore nella editText possa andare a capo
        editText.setFocusable(true);        // evita che alla pressione dei button la edit text apra la tastiera

        save = (Button) findViewById(R.id.button0);               // salva                            OK, DA FINIRE
        settings = (Button) findViewById(R.id.button1);           // impostazioni                     OK, DA FINIRE
        exit = (Button) findViewById(R.id.button);                // termina app                      OK
        button2 = (Button) findViewById(R.id.button2);            // copia 3 catene + data            OK
        button3 = (Button) findViewById(R.id.button3);            // copia data e ora                 OK
        button4 = (Button) findViewById(R.id.button4);            // tutto maiuscolo                  OK
        button5 = (Button) findViewById(R.id.button5);            // tutto minuscolo                  OK
        button6 = (Button) findViewById(R.id.button6);            // copia                            OK
        button7 = (Button) findViewById(R.id.button7);            // taglia                           OK
        button8 = (Button) findViewById(R.id.button8);            // seleziona tutto                  OK
        button9 = (Button) findViewById(R.id.button9);            // deseleziona tutto                OK
        button10 = (Button) findViewById(R.id.button10);          // avanti
        button11 = (Button) findViewById(R.id.button11);          // indietro
        button12 = (Button) findViewById(R.id.button12);          // traduci
        button13 = (Button) findViewById(R.id.button13);          // cerca  su google
        button14 = (Button) findViewById(R.id.button14);          // cancella tutto                   OK
        button15 = (Button) findViewById(R.id.button15);          // incolla                          OK
        button16 = (Button) findViewById(R.id.button16);          // mostra note salvate nel DB
        button17 = (Button) findViewById(R.id.button17);          // bottone super user
        button18 = (Button) findViewById(R.id.button18);          // bottone encryption
        button19 = (Button) findViewById(R.id.button19);          // aggiorna                         OK
        button21 = (Button) findViewById(R.id.button21);          // meno                             OK
        button22 = (Button) findViewById(R.id.button22);          // freccietta
        button40 = (Button) findViewById(R.id.button40);          // formatta testo                   OK
        button41 = (Button) findViewById(R.id.button41);          // tutto in grassetto               OK
        button50 = (Button) findViewById(R.id.button50);          // tastiera / no tastiera           OK
        button51 = (Button) findViewById(R.id.button51);          // cursore su
        button52 = (Button) findViewById(R.id.button52);          // cursore giu

        /* SECRET BUTTONS */
        button23 = (Button) findViewById(R.id.button23);            // Buongiorno [1]
        button24 = (Button) findViewById(R.id.button24);            // Buongiorno [2]
        button25 = (Button) findViewById(R.id.button25);;        // Buongiorno 3
        button26 = (Button) findViewById(R.id.button26);;        // Buongiorno 4
        button27 = (Button) findViewById(R.id.button27);;        // Buongiorno 5
        button28 = (Button) findViewById(R.id.button28);;        // Buongiorno 6
        button29 = (Button) findViewById(R.id.button29);;        // Buongiorno 7
        button30 = (Button) findViewById(R.id.button30);;        // Buongiorno 8

        button31 = (Button) findViewById(R.id.button31);;        // Buonanotte 1
        button32 = (Button) findViewById(R.id.button32);;        // Buonanotte 2
        button33 = (Button) findViewById(R.id.button33);;        // Buonanotte 3
        button34 = (Button) findViewById(R.id.button34);;        // Buonanotte 4
        button35 = (Button) findViewById(R.id.button35);;        // Buonanotte 5
        button36 = (Button) findViewById(R.id.button36);;        // Buonanotte 6
        button37 = (Button) findViewById(R.id.button37);;        // Buonanotte 7
        button38 = (Button) findViewById(R.id.button38);;        // Buonanotte 8


        SupportDatabaseHandler db = new SupportDatabaseHandler(DBactivity.this);
        StringBuffer str = db.getLasPos(DBactivity.this);
        MainDatabaseHandler d = new MainDatabaseHandler(DBactivity.this);
        MainDatabaseHandler d1 = new MainDatabaseHandler(DBactivity.this, true);

        if (str != null) {
            editText.setText(str.toString());
            editText.setSelection(editText.getText().length() - 1);
        }


        /* VEDO I BOTTONI SEGRETI NELL'ACTIVITY */
        if (setSecretButtonsVisibility) {
            button17.setVisibility(View.VISIBLE);
            button18.setVisibility(View.VISIBLE);
            button23.setVisibility(View.VISIBLE);
            button24.setVisibility(View.VISIBLE);
            button25.setVisibility(View.VISIBLE);
            button26.setVisibility(View.VISIBLE);
            button27.setVisibility(View.VISIBLE);
            button28.setVisibility(View.VISIBLE);
            button29.setVisibility(View.VISIBLE);
            button30.setVisibility(View.VISIBLE);

            button31.setVisibility(View.VISIBLE);
            button32.setVisibility(View.VISIBLE);
            button33.setVisibility(View.VISIBLE);
            button34.setVisibility(View.VISIBLE);
            button35.setVisibility(View.VISIBLE);
            button36.setVisibility(View.VISIBLE);
            button37.setVisibility(View.VISIBLE);
            button38.setVisibility(View.VISIBLE);


        } else {
            button17.setVisibility(View.GONE);
            button18.setVisibility(View.GONE);
            button23.setVisibility(View.GONE);
            button24.setVisibility(View.GONE);
            button25.setVisibility(View.GONE);
            button26.setVisibility(View.GONE);
            button27.setVisibility(View.GONE);
            button28.setVisibility(View.GONE);
            button29.setVisibility(View.GONE);
            button30.setVisibility(View.GONE);

            button31.setVisibility(View.GONE);
            button32.setVisibility(View.GONE);
            button33.setVisibility(View.GONE);
            button34.setVisibility(View.GONE);
            button35.setVisibility(View.GONE);
            button36.setVisibility(View.GONE);
            button37.setVisibility(View.GONE);
            button38.setVisibility(View.GONE);
        }

        if (editText.getText() != null) {
        format(); }

        /* ELIMINO I DOPPI SPAZI (DA CONTINUARE) */
            //   deleteDoppiSpazi(editText.getText().toString());

        /*
        if (d.getWritableDatabase().rawQuery("select id from NOTES_TABLE",null) != null) {
            Toast.makeText(getApplicationContext(),"select id from NOTES_TABLE worked", Toast.LENGTH_SHORT).show();
            d.reorderIDs();
            Utility.show("DBactivity/reorder(): fin ora tutto ok");
            SQLiteDatabase s = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/MainDatabaseHandler.db",null,0);
            d.reorderIDs(s);
        }
        */


        //********** LISTENERS **********
        /*  IMPOSTAZIONI  */
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInSupportDatabase();
                Intent intent = new Intent(getApplicationContext(), Impostazioni.class);
                startActivity(intent);
            }
        });

        /*  EXIT  */
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*  // AGGIORNO L'ACTIVITY!
                    Intent homeintent = new Intent(DBactivity.this, MainActivity.class);
                    startActivity(homeintent);
                    DBactivity.this.finish(); */
                saveInSupportDatabase();
                System.exit(0);
            }
        });

        /*  SAVE  */

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //       Toast.makeText(getApplicationContext(),"SAVE: Ancora da implementare", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "SAVE: nota salvata", Toast.LENGTH_SHORT).show();
                DataEora d = new DataEora();
                MainDatabaseHandler m = new MainDatabaseHandler(getApplicationContext());             // DB on app data storage
                MainDatabaseHandler m1 = new MainDatabaseHandler(getApplicationContext(), true);       // DB on internal storage

                m.insertData(editText.getText().toString(), d.getData().toString(), d.getOra().toString());
                // Salvo i dati sul database dell'applicazione
                m1.insertData(editText.getText().toString(), d.getData().toString(), d.getOra().toString());
                // salvo i dati sul database pubblico sulla memoria interna

                //  m.close();
                //  m.viewAll(DBactivity.this);
                saveInSupportDatabase();   // salvo nel db di supporto la stringa contenuta nella edittext
                saveInSupportDatabase(""); // e successivamente salvo una stringa vuota, cosi che la volta dopo che riapro
                // l'app, non venga ripristinata la stringa già salvata nel db e contenuta nell'ultima
                // tupla del dp di supporto, e non rischiando cosi di salvare un'altra volta la stessa stringa
                // nel db pensando di non avelra ancora salvata!
                StringBuffer str = m.getLasPos(DBactivity.this);
                editText.setText("");
                System.err.println("DBactivity/save/getLastPos(): " + str);

            }
        });


        //------------------------------
        /*  COPIA 3 CATENE PIU DATA  */

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataEora d = new DataEora();
                String dataEora = d.getData() + " " + d.getOra();
                String e = editText.getText().toString();
                editText.setText(e + "\n\n88-----88-----88\n" + dataEora + "\n\n- ");
                editText.setSelection(editText.getText().toString().length());
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  COPIA DATA E ORA  */

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                *   Seleziono tutto così se viene schiacciato per sbaglio
                *   non si elimina tutto il testo
                **/
                DataEora d = new DataEora();
                String dataEora = d.getData() + " " + d.getOra();
                String e = editText.getText().toString();
                editText.setText(e + dataEora + "\n\n- ");
                editText.setSelection(editText.getText().toString().length() - 1);
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  TUTTO MAIUSCOLO  */

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = editText.getText().toString();
                String E = e.toUpperCase();
                format();
                editText.setText(E);

            }
        });

        //------------------------------
        /*  TUTTO MINUSCOLO  */

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String E = editText.getText().toString();
                String e = E.toLowerCase();
                format();
                editText.setText(e);
            }
        });

        //------------------------------
        /*  COPIA  */

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyOnClipboard c = new CopyOnClipboard();
                closeKeyboard();
                int startSelection = editText.getSelectionStart();
                int endSelection = editText.getSelectionEnd();
                String selectedText = editText.getText().toString().substring(startSelection, endSelection);
                c.setClipboard(getApplicationContext(), selectedText);
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  TAGLIA  */
        //  DA SISTEMARE, QUANDO PREMI 'TAGLIA', NON VIENE INCOLLATO NULL SULLA CLIPBOARD,
        //  CIOE' INCOLLA NON FUNZIONA!


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInSupportDatabase();
                CopyOnClipboard c = new CopyOnClipboard();
                closeKeyboard();
                int startSelection = editText.getSelectionStart();
                int endSelection = editText.getSelectionEnd();
                String selectedText = editText.getText().toString().substring(startSelection, endSelection);
                c.setClipboard(getApplicationContext(), selectedText);
                editText.setText("");
            }
        });

        //------------------------------
        /*  SELEZIONA TUTTO  */

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInSupportDatabase();
                editText.setSelection(0);
                editText.setSelection(0, editText.getText().toString().length());

            }
        });

        //------------------------------
        /*  DESELEZIONA TUTTO  */
        saveInSupportDatabase();
        editText.setSelection(editText.getText().length() - 1);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelection(0);
            }
        });

        //------------------------------
        /*  AVANTI  */

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "AVANTI: Ancora da implementare", Toast.LENGTH_SHORT).show();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  INDIETRO  */

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "INDIETRO: Ancora da implementare", Toast.LENGTH_SHORT).show();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  TRADUCI  */

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TRADUCI: Ancora da implementare", Toast.LENGTH_SHORT).show();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  CERCA SU GOOGLE  */

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInSupportDatabase();
                Toast.makeText(getApplicationContext(), "CERCA SU GOOGLE: Ancora da implementare", Toast.LENGTH_SHORT).show();
            }
        });

        //------------------------------
        /*  CANCELLA TUTTO  */

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                saveInSupportDatabase();
            }
        });


        //------------------------------
        /*  INCOLLA  */

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String copiedText = clipboard.getText().toString();
                String e = editText.getText().toString();
                editText.setText(e + " " + copiedText);
                saveInSupportDatabase();

            }
        });

        //------------------------------
        /*  AGGIORNA L'ACTIVITY PER EVITARE CHE LA TASTIERA DIA FASTIDIO  */

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInSupportDatabase();
                startActivity(getIntent());
                editText.setSelection(editText.getText().toString().length());
                System.err.println("ACTIVITY: aggiornata");
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  MENO DA INSERIRE NELLA EDITTEXT SENZA DOVERLO SCRIVERE MANUALMENTE  */

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                editText.setText(s + "\n- ");
                editText.setSelection(editText.getText().toString().length());
                format();
                saveInSupportDatabase();

            }
        });

        //------------------------------
        /*  FRECCIA DA INSERIRE NELLA EDITTEXT SENZA DOVERLO SCRIVERE MANUALMENTE  */

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                editText.setText(s + "\n-> ");
                editText.setSelection(editText.getText().toString().length());
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  MOSTRA NOTE SALVATE NEL DB  */

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteSalvateNelDatabase.class);
                System.err.println("[1] note salate si APREEEEEEEEEEEEEEEEEEEEE");
                startActivity(intent);
            }
        });

        //------------------------------
        /*  FORMATTA TESTO  */
        button40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();                // ottengo una stringa della edittext
                correctStrings(s);

                char[] s_ = s.toCharArray();                           // trasformo in un array di caratteri per valutare ogni lettera
                String formattedString;                                 // nuova stringa in cui verra messo il testo formattato
                String[] s_split = s.split("\n");

                Utility.showArr(s_split);
                editText.setText("");


                boolean b = s_split[s_split.length-1].contains("A")   || s_split[s_split.length-1].contains("B")    || s_split[s_split.length-1].contains("C")
                        || s_split[s_split.length-1].contains("D")    || s_split[s_split.length-1].contains("E")    || s_split[s_split.length-1].contains("F")
                        || s_split[s_split.length-1].contains("G")    || s_split[s_split.length-1].contains("H")    || s_split[s_split.length-1].contains("I")
                        || s_split[s_split.length-1].contains("J")    || s_split[s_split.length-1].contains("K")    || s_split[s_split.length-1].contains("L")
                        || s_split[s_split.length-1].contains("M")    || s_split[s_split.length-1].contains("N")    || s_split[s_split.length-1].contains("O")
                        || s_split[s_split.length-1].contains("P")    || s_split[s_split.length-1].contains("Q")    || s_split[s_split.length-1].contains("R")
                        || s_split[s_split.length-1].contains("S")    || s_split[s_split.length-1].contains("T")    || s_split[s_split.length-1].contains("U")
                        || s_split[s_split.length-1].contains("V")    || s_split[s_split.length-1].contains("W")    || s_split[s_split.length-1].contains("X")
                        || s_split[s_split.length-1].contains("Y")    || s_split[s_split.length-1].contains("Z")    || s_split[s_split.length-1].contains("a")
                        || s_split[s_split.length-1].contains("b")    || s_split[s_split.length-1].contains("c")    || s_split[s_split.length-1].contains("d")
                        || s_split[s_split.length-1].contains("e")    || s_split[s_split.length-1].contains("f")    || s_split[s_split.length-1].contains("g")
                        || s_split[s_split.length-1].contains("h")    || s_split[s_split.length-1].contains("i")    || s_split[s_split.length-1].contains("j")
                        || s_split[s_split.length-1].contains("k")    || s_split[s_split.length-1].contains("l")    || s_split[s_split.length-1].contains("m")
                        || s_split[s_split.length-1].contains("n")    || s_split[s_split.length-1].contains("o")    || s_split[s_split.length-1].contains("p")
                        || s_split[s_split.length-1].contains("q")    || s_split[s_split.length-1].contains("r")    || s_split[s_split.length-1].contains("s")
                        || s_split[s_split.length-1].contains("t")    || s_split[s_split.length-1].contains("u")    || s_split[s_split.length-1].contains("v")
                        || s_split[s_split.length-1].contains("w")    || s_split[s_split.length-1].contains("x")    || s_split[s_split.length-1].contains("y")
                        || s_split[s_split.length-1].contains("z")    || s_split[s_split.length-1].contains("0")    || s_split[s_split.length-1].contains("1")
                        || s_split[s_split.length-1].contains("2")    || s_split[s_split.length-1].contains("3")    || s_split[s_split.length-1].contains("4")
                        || s_split[s_split.length-1].contains("5")    || s_split[s_split.length-1].contains("6")    || s_split[s_split.length-1].contains("7")
                        || s_split[s_split.length-1].contains("8")    || s_split[s_split.length-1].contains("9");


/*
                int n = 0;
                // per ogni rica controllo se ci sono caratteri.
                    for (int i = 0; i< s_split.length; i++ ) {
                        boolean c = s_split[i].contains("A")   || s_split[i].contains("B")    || s_split[i].contains("C")
                                || s_split[i].contains("D")    || s_split[i].contains("E")    || s_split[i].contains("F")
                                || s_split[i].contains("G")    || s_split[i].contains("H")    || s_split[i].contains("I")
                                || s_split[i].contains("J")    || s_split[i].contains("K")    || s_split[i].contains("L")
                                || s_split[i].contains("M")    || s_split[i].contains("N")    || s_split[i].contains("O")
                                || s_split[i].contains("P")    || s_split[i].contains("Q")    || s_split[i].contains("R")
                                || s_split[i].contains("S")    || s_split[i].contains("T")    || s_split[i].contains("U")
                                || s_split[i].contains("V")    || s_split[i].contains("W")    || s_split[i].contains("X")
                                || s_split[i].contains("Y")    || s_split[i].contains("Z")    || s_split[i].contains("a")
                                || s_split[i].contains("b")    || s_split[i].contains("c")    || s_split[i].contains("d")
                                || s_split[i].contains("e")    || s_split[i].contains("f")    || s_split[i].contains("g")
                                || s_split[i].contains("h")    || s_split[i].contains("i")    || s_split[i].contains("j")
                                || s_split[i].contains("k")    || s_split[i].contains("l")    || s_split[i].contains("m")
                                || s_split[i].contains("n")    || s_split[i].contains("o")    || s_split[i].contains("p")
                                || s_split[i].contains("q")    || s_split[i].contains("r")    || s_split[i].contains("s")
                                || s_split[i].contains("t")    || s_split[i].contains("u")    || s_split[i].contains("v")
                                || s_split[i].contains("w")    || s_split[i].contains("x")    || s_split[i].contains("y")
                                || s_split[i].contains("z")    || s_split[i].contains("0")    || s_split[i].contains("1")
                                || s_split[i].contains("2")    || s_split[i].contains("3")    || s_split[i].contains("4")
                                || s_split[i].contains("5")    || s_split[i].contains("6")    || s_split[i].contains("7")
                                || s_split[i].contains("8")    || s_split[i].contains("9")    || s_split[i].contains("-");;
                        if (b ) {
                            ++n;
                            System.err.println("im here " + n);
                        }
                    }
*/


				/*PER ELIMINARE LO SPAZIO FINLE, PROVARE A INSERIRE -1 DOPO length*/
                for (int k = 0; k < s_split.length; k++) {
                    editText.setText(Html.fromHtml("<b>" + s_split[k] + "</b>"));
                }
                editText.setText(Html.fromHtml(ToHtmlBreaklineAndBoldInsert(s)));


                editText.setSelection(editText.getText().length() - 1);
      //          editText.setSelection(n);
                saveInSupportDatabase();

            }
        });

        //------------------------------
        /*  TUTTO IN GRASSETTO  */

        button41.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();                                           // ottengo una stringa della edittext
                editText.setText(Html.fromHtml("<b>" + ToHtmlBreaklineInsert(s) + "</b>"));
                /*
                MainDatabaseHandler m = new MainDatabaseHandler(DBactivity.this);
                MainDatabaseHandler m = new MainDatabaseHandler(DBactivity.this,true);

                m.copyDB();
                File f = new File ("/data/data/com.example.ament.dbhandler/databases/MainDatabaseHandler.db");
                Toast.makeText(getApplicationContext(),"il file esiste? " + f.exists(), Toast.LENGTH_SHORT).show(); // il file esiste
                AndroidFileExistanceCheck a = new AndroidFileExistanceCheck();
                try {
                    Toast.makeText(getApplicationContext(),"il file esiste? " + Environment.getExternalStorageDirectory(), Toast.LENGTH_SHORT).show(); // il file esiste
                    a.copy(new File ("/storage/emulated/0/DBhandlerData"),f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  ABILITA / DISABILITA TASTIERA  */

        button50.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                /*
                // tolgo il T9, non va bene, ma questo codice lo conservo

                editText.setInputType(InputType.TYPE_NULL);
                editText.setTextIsSelectable(true);
                 */

                /*
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                editText.getWindowVisibleDisplayFrame(r);

                int heightDiff = editText.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...

                    //Hide the keyboard instantly!
                    if (getCurrentFocus() != null)
                    {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
                */

                if (abilitaOdisabilitaTastiera == false) {
                    abilitaOdisabilitaTastiera = true;
                    Toast.makeText(getApplicationContext(), "abilitaOdisabilitaTastiera: true", Toast.LENGTH_SHORT).show();
                    button50.setBackgroundResource(R.drawable.tastiera);
                } else if (abilitaOdisabilitaTastiera == true) {
                    abilitaOdisabilitaTastiera = false;
                    Toast.makeText(getApplicationContext(), "abilitaOdisabilitaTastiera: false", Toast.LENGTH_SHORT).show();
                    button50.setBackgroundResource(R.drawable.notastiera);
                }
                saveInSupportDatabase();

            }
        });

        //------------------------------
        /*  CURSORE SU  */

        button51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelection(0);
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  CURSORE GIU  */

        button52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelection(editText.getText().length() - 1);
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  PULSANTE SEGRETO  */
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //------------------------------
        /*  BUONGIORNO [1]  */
        button23.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                        meno + " " + par + tratt +
                        "Buongiorno Tatina mia bellissima adoratissima fantastica stupenda \uD83D\uDE18❤❤❤  Come hai mommito stanotte? " +
                         tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [2]  */
        button24.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno Tatina mia stupenda fantastica amatissima bellissima \uD83D\uDE18❤❤❤  Come hai mommito stanotte?  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [3]  */
        button25.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno Tatina mia stupenda bellissima fantastica amatissima \uD83D\uDE18❤❤❤  Come hai mommito stanotte?  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [4]  */
        button26.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno Tatina mia stupenda amatissima bellissima fantastica \uD83D\uDE18❤❤❤  Come hai mommito stanotte?  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [5]  */
        button27.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno Tatina mia stupenda amatissima bellissima fantastica \uD83D\uDE18❤❤❤  Come hai mommito stanotte? " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [6]  */
        button28.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno mia fantastica piccola Tatina amatissima bellissima  \uD83D\uDE18❤❤❤  Come hai mommito stanotte? " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [7]  */
        button29.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno mia piccola Tatina fantastica bellissima  \uD83D\uDE18❤❤❤  Come hai mommito stanotte? " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONGIORNO [8]  */
        button30.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                meno + " " + par + tratt +
                                " Buongiorno bellissima piccola mia Tatina stupenda   \uD83D\uDE18❤❤❤  Come hai mommito stanotte? " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [1]  */
        button31.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte bellissima piccola mia Tatina stupenda   \uD83D\uDE18❤❤❤  Fai sogni d'oro BUH \uD83D\uDE18❤❤ " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [2]  */
        button32.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte Tatina mia stupenda fantastica amatissima bellissima \uD83D\uDE18❤❤❤  Fai sogni d'oro TATINA BUH \uD83D\uDE18❤❤  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [3]  */
        button33.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte Tatina mia stupenda bellissima fantastica amatissima \uD83D\uDE18❤❤❤  Fai sogni d'oro MIA BUH \uD83D\uDE18❤❤  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [4]  */
        button34.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte Tatina mia stupenda amatissima bellissima fantastica \uD83D\uDE18❤❤❤  Fai sogni d'oro TATINA BUH \uD83D\uDE18❤❤  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [5]  */
        button35.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte Tatina mia stupenda amatissima bellissima fantastica \uD83D\uDE18❤❤❤  Fai sogni d'oro TATINA MIAA \uD83D\uDE18❤❤  " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [6]  */
        button36.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte mia fantastica piccola Tatina amatissima bellissima  \uD83D\uDE18❤❤❤   Fai sogni d'oro MIAA TATINA BUUUH \uD83D\uDE18❤❤ " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [7]  */
        button37.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte mia piccola Tatina fantastica bellissima  \uD83D\uDE18❤❤❤  Fai sogni d'oro MIA BUUUH \uD83D\uDE18❤❤ " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  BUONANOTTE [8]  */
        button38.setOnClickListener(new View.OnClickListener() {
            char meno = '-';
            char par = '(';
            char par0 = ')';
            char tratt = '"';
            @Override
            public void onClick(View v) {
                editText.setText(
                        editText.getText().toString() +
                                "\n" +  meno + " " + par + tratt +
                                " Buonanotte bellissima piccola mia Tatina stupenda   \uD83D\uDE18❤❤❤  Fai sogni d'oro TATINA MIA BUUUH \uD83D\uDE18❤❤ " +
                                tratt + par0
                );
                format();
                saveInSupportDatabase();
            }
        });

        //------------------------------
        /*  LISTENER TEMPORALE OGNI 1000 ms  */
        //  EditTextUpdateOnTime.timer();
        String editTextParameter = editText.getText().toString();
        // la stringa contiene solo "\n\n\n\n\n\n\n\n\n"
        TimeThread t = new TimeThread(System.currentTimeMillis(),editTextParameter,DBactivity.this);
        Thread th = new Thread(t);
        th.start();



        //------------------------------
        /*  LISTENER PER LA EDITTEXT, DI CUI SALVO LO STATO NEL DATABASE DI SUPPORTO  */

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

                operOrCloseKeyboard(abilitaOdisabilitaTastiera);

                SupportDatabaseHandler dbs = new SupportDatabaseHandler(DBactivity.this);
                if (editText.getText().toString() != null) {
                    dbs.insertIntoSupportTable(editText.getText().toString());
                    // TEST SU LOG
                    StringBuffer str = dbs.getLasPos(DBactivity.this);
                    StringBuffer id = dbs.getLasID(DBactivity.this);
                    System.err.println("CONTENUTO DATABASE: ID = " + id + " NOTA: " + str);
                    //  dbs.viewAllFromSupportTable(DBactivity.this);

                    // DEVO AGGIORNARE L'ACTIVITY, PERCHE' SENNO LA TASTIERA COPRE LA EDITTEXT!
                }

            }
        });


    }

    /*
    * ON RESUME: mi permette di ricaricare l'activity.
    * Al momento non funziona. Quando accedo a NoteSalvateNelDatabase
    * l'app lascia una strana eccezione
    *
    * java.lang.IndexOutOfBoundsException: setSpan (-1 ... -1) starts before 0
    *
    * ADESSO: funziona. Non bisognava chiamare il metodo format() da qui.

    @Override
    public void onResume() {
        super.onResume();
        if (resumeCount > 0) {
            Toast.makeText(getApplicationContext(), "RESUME", Toast.LENGTH_SHORT).show();
            startActivity(getIntent());
        }
        ++resumeCount;
        return;
    }
*/

    public void closeKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //------------------------------

    public void saveInSupportDatabase(String whatTosave) {
        SupportDatabaseHandler dbs = new SupportDatabaseHandler(DBactivity.this);
        if (editText.getText().toString() != null) {
            Utility.show("DB INSERT: eseguito");
            dbs.insertIntoSupportTable(whatTosave);
        }
    }

    //------------------------------
    public void saveInSupportDatabase() {
        SupportDatabaseHandler dbs = new SupportDatabaseHandler(DBactivity.this);
        if (editText.getText().toString() != null) {
            Utility.show("DB INSERT: eseguito");
            dbs.insertIntoSupportTable(editText.getText().toString());
        }
    }
    //------------------------------
    /*  METODO CHE DECIDE NEL LISTENER DELLA EDIT TEXT SE APRIRE O NO LA TASTIERA   */

    public void operOrCloseKeyboard(Boolean abilitaOdisabilitaTastiera) {
        if (abilitaOdisabilitaTastiera == true || abilitaOdisabilitaTastiera == null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            editText.setCursorVisible(true);
        } else if (!abilitaOdisabilitaTastiera) {
            Rect r = new Rect();
            //r will be populated with the coordinates of your view that area still visible.
            editText.getWindowVisibleDisplayFrame(r);

            int heightDiff = editText.getRootView().getHeight() - (r.bottom - r.top);
            if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...

                //Hide the keyboard instantly!
                if (getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
            editText.setCursorVisible(false);
        }
    }

    //------------------------------
    /*  TRASFORMO TUTTO IN GRASSETTO    */
    /*  INSERISCO I TAG HTML DI INTERROMPI RIGA IN UNA STRINGA
        (PER FAR DIVENTARE TUTTE LE LINEE IN GRASSETTO, HO BISOGNO
         DI PASSARE DA HTML, IN QUANTO JAVA, NON SUPPORTA LE STRINGHE
         CON UNA CERTA FORMATTAZIONE. INSERENDO I DOVUTI TAG, HTML PROVVEDE
         DA SOLO A TRASFORMARE IN GRASSETTO TUTTA LA STRINGA.

         METODO RIUTILIZZABILE!

         QUESTO METODO
         PROVVEDE AD ANDARE A CAPO, IN QUANTO HTML NON LO FA DA SOLO QUANDO
         LEGGE IL \n DALLE STRINGHE DI JAVA. */

    public String ToHtmlBreaklineInsert(String s) {
        String[] str = s.split("\n");              // HO SPLITTATO AD OGNI A CAPO
        String[] new_str = new String[str.length];
        StringBuilder sss = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            String g = "";
            g = str[i] + "<br>";
            new_str[i] = g;
        }
        StringBuffer k = new StringBuffer();
        for (int i = 0; i < new_str.length; i++) {
            k.append(new_str[i]);
        }
        String h = k.toString();
        Utility.show("s -> " + h);
        return h;
    }

    //------------------------------
    /*  QUESTO METODO MANDA A CAPO IL TESTO E METTE IN GRASSETTO LE DATE CONTENUTE  */

    public String ToHtmlBreaklineAndBoldInsert(String s) {
        Character c0 = '"';
        Character c1 = '(';
        Character c2 = ')';
        char vir = '"';
        String ss0 = c1.toString() + c0.toString(); // ("
        String ss1 = c0.toString() + c2.toString(); // ")
        String a = "";
        String b = ""; // questa stringa la uso per il caso in cui una nota dovesse mai iniziare con (" ...


        String[] str = s.split("\n");              // HO SPLITTATO AD OGNI A CAPO COSI POSSO ANALIZZARE TRANQUILLAMENTE OGNI RIGA
        String[] new_str = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            String g = "";
            String s0 = "";
            String s1 = "";
            String s2 = "";
            String s3 = "";         // miei discorsi
            int index_of_meno;
            int index_of_due_punti;
            int index_of_2_trattini_par_aperta;
            int index_of_2_trattini_par_chiusa;
            int index_of_parentesi_aperta;
            int index_of_parentesi_chiusa;
            if (str[i].contains("8")) {      // COLORO CORNICE (88-----88-----88)
               a = "<font size=\"5\" color=\"0x00bfff\"><b>" + str[i] + "</b></font>";
                g = a;
            }
            /*
            COLORO DATA, CIOE
             88-----88-----88
             23/12/2017 23:55
            **/
            if (str[i].contains("/")) {
                a = "<font size=\"5\" color=\"0x7fffd4\"><b>" + str[i] + "</b></font>";
                g = a;
            }
            String separate3 [] ;


            /*  QUESTO CICLO NON VIENE MAI STRANAMENTE ESEGUITO
           try {
               for (int k = 0; k < str.length; k++) {                           // scorro le lettere delle parole di ogni riga
                   if (str[i].charAt(k) == c1 && str[i].charAt(k + 1) == c0) {                        // ("
                       int index0 = str[i].indexOf(c1 + c0 + "");              // ("

                       int index1 = str[i].indexOf(c0 + c2 + "");      // ")
                       String s4 = str[i].substring(index0, index1);
                       String zz = str[i].substring(0, index0 - 1);                        // stringa prima di quella da rendere rossa
                       String gg = str[i].substring(index1 + 1, str[i].length());            // stringa dopo di quella rendere rossa
                       String z = "<b>" + s4 + "</b>";
                       String d = "<font size=\"5\" color=\"0xFF0000\"><b>" + z.toUpperCase() + "</b></font>";
                       String e = d;
                       String ff = zz + e + gg;
                       g = ff;
                       System.out.println("RUNNNNN");
                   }
               }
           }catch (IndexOutOfBoundsException e) {
               System.out.println("ERROR OCCURRED");
           }
           */



            if (str[i].contains("-") && str[i].contains(":")) {
                index_of_meno = getCharPosition(str[i], '-');
                index_of_due_punti = getCharPosition(str[i], ':');

                s1 = str[i].substring(index_of_meno, index_of_due_punti);    // parola chiave da grassettare
                s2 = str[i].substring(index_of_due_punti, str[i].length());  // tutto il resto
                // cerco le mie risposte caratterizzate da (" e ")

                Utility.show("index_of_meno -> " + index_of_meno + "\n" + "index_of_due_punti -> " + index_of_due_punti);   // FUNZIONA
                Utility.show("s1 -> " + s1 + "\n" + "s2 -> " + s2);

                String z = "<b>" + s0 + "</b>";

                String d = "<font size=\"5\" color=\"0x7fffd4\"><b>" + s1.toUpperCase() + "</b></font>";
                String e = s2;
                String ff = z + d + e;
                g = ff;
            }
            if (str[i].contains("-") && !str[i].contains(":")) {
                a = "<font size=\"5\" color=\"0x00bfff\"><b>" + str[i] + "</b></font>";
                a = "<font size=\"5\" color=\"0x7fffd4\"><b>" + str[i] + "</b></font>";
                g = a;
            }



            /*
        //      DA CONTINUARE! QUESTO CODICE SI OCCUPA DI COLORARE IL TESTO CONTENUTO TRA ("..")
        //      Se l'array alla posiione i contiene (" oppure ")
        //      <font face=”Showcard Gothic”>TESTO DA SCRIVERE</font>

            if ((str[i].contains(ss0) && str[i].contains(ss1) )) {
                index_of_parentesi_aperta = getCharPosition (str[i],'(');
                index_of_2_trattini_par_aperta = getCharPosition (str[i],'"');

                if ((str[i].contains(ss0) && str[i].contains(ss1) )) {
                    index_of_2_trattini_par_chiusa = getCharPosition (str[i],'"');
                    index_of_parentesi_chiusa = getCharPosition (str[i],')');
                    String aaa = str[i].substring(0,index_of_parentesi_aperta);
                    b = str[i].substring(index_of_2_trattini_par_aperta,index_of_2_trattini_par_chiusa);
                    String ccc = str[i].substring(index_of_parentesi_chiusa,str[i].length());
                    String aa = "<font size=\"5\" color=\"0x80FF80\"><b><font face=Showcard Gothic”>" + b + "</font>";
                    String finall = aaa + b + ccc;
                    g = finall;
                }
            }
            */

            g = g + "<br>";
            new_str[i] = g;

        }
        StringBuffer k = new StringBuffer();

        for (int i = 0; i < new_str.length; i++) {
            k.append(new_str[i]);
        }
        String h = k.toString();
        Utility.show("s -> " + h);
        return h;
    }

    //------------------------------
    /*  METODO CHE RIMPIAZZA UNA STRINGA CON UN'ALTRA STRINGA  */

    public String replaceString(String contenutoOriginaleConParoleDaRimpiazzare, String cosaRimpiazzare, String rimpiazzo) {
        if (contenutoOriginaleConParoleDaRimpiazzare.contains(cosaRimpiazzare)) {
            contenutoOriginaleConParoleDaRimpiazzare = contenutoOriginaleConParoleDaRimpiazzare.replace(cosaRimpiazzare, rimpiazzo);
        }
        return contenutoOriginaleConParoleDaRimpiazzare;
    }

    //------------------------------
    /*  METODO CHE RESTITUISCE LA POSIZIONE DI UN CARATTERE IN UNA STRINGA  */

    public int getCharPosition(String s, char carattere_da_cercare) {
        int pos = 0;
        boolean trovato = false;
        for (int i = 0; i < s.length() && !trovato; i++) {
            if (carattere_da_cercare == s.charAt(i)) {
                pos = i;
                trovato = true;
            }
        }
        return pos;
    }

    //------------------------------
    /*  METODO CHE RENDE UPPERCASE TUTTE LE LETTERE IN UNA STRINGA  */

    public String allUppercaseWords(String s) {

        return s.toUpperCase();
    }

    //------------------------------
    /*  METODO CHE RESTITUISCE LA POSIZIONE DEL CURSORE IN UNA STRINGA  */

    public int cursorPosition(EditText e) {
        return e.getSelectionStart();

    }

    //------------------------------

    public void correctStrings(String s) {
        s = editText.getText().toString();                     // ottengo una stringa della edittext
        s = replaceString(s, "tatina", "Tatina");              // rimpiazzo tutte le parole che potrei aver digitato male
        s = replaceString(s, "tatino", "Tatino");
        s = replaceString(s, "tatini", "Tatini");
        s = replaceString(s, "buh", "BUH");
    }

    //------------------------------
    /* FORMATTO LA EDITTEXT */

    public void format() {
        String s = editText.getText().toString();                // ottengo una stringa della edittext
        correctStrings(s);

        char[] s_ = s.toCharArray();                           // trasformo in un array di caratteri per valutare ogni lettera
        String formattedString;                                 // nuova stringa in cui verra messo il testo formattato
        String[] s_split = s.split("\n");

        Utility.showArr(s_split);
        editText.setText("");

        for (int k = 0; k < s_split.length; k++) {
            editText.setText(Html.fromHtml("<b>" + s_split[k] + "</b>"));
        }
        editText.setText(Html.fromHtml(ToHtmlBreaklineAndBoldInsert(s)));
        editText.setSelection(editText.getText().length() - 2);
        saveInSupportDatabase();
    }

    /*  NON FUNZIONA    */
    public void deleteDoppiSpazi(String s) {
        try {
            String[] s_split = s.split("\n");
            for (int i = 0; i < s_split.length; i++) {
                if (s_split[i].contains("88") && s_split[i+1].contains("\n")) {
                    Toast.makeText(getApplicationContext(), "i =" + i, Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e) {

        }

        }





    }


