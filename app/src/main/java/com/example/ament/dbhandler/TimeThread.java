package com.example.ament.dbhandler;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ament on 28/10/2018.
 *
 * CONTEXT: passo il context ad al costruttore di una classe SOLO ed ESCLUSIVAMENTE SE
 *          essa non è un'activity (e quindi non estende Activity)
 *
 *          in questo caso, essendo che chi chiama questo thread possiede un context
 *          diverso, non posso usare getApplicationContext o TimeThread.this in quanto
 *          non esiste un context.
 *          Potrei decidere di estendere Activity, ma ciò non mi converrebbe perchè
 *          questo è solo un thread senza componenti grafici e quindi non esiterebbe
 *          comunque un context (contesto) da accedere! Se estendo Activity, non
 *          succede comunque nulla, sarebbe solo un'errore a livello concettuale
 *
 *          https://stackoverflow.com/questions/46333985/attempt-to-invoke-virtual-method-sqlite
 */

public class TimeThread extends Activity implements Runnable  {

    long time;
    DBactivity db;
    String editText;
    Context context;

    public TimeThread(long time, String editText,Context context) {
        this.time=time;
        this.editText=editText;
        this.context=context;
        db = new DBactivity();
    }

    @Override
    public void run () {
        while (true) {
            //System.out.println("time = " + time);

            long time2 = System.currentTimeMillis();
            // System.out.println("passedTime = " + time2);

            long tempoTrascorso = time2-time;

            // Se sono passati almeno 5000 millisecondi (cioè 5 secondi)
            if (tempoTrascorso % 5000 == 0) {   // se sono passati almeno 5 secondi

                   System.out.println("THREAD PARTITO. Tempo trascorso: " + tempoTrascorso );
                // time = System.currentTimeMillis();

                SupportDatabaseHandler dbs = new SupportDatabaseHandler(context);
                if (editText != null) {
                    Utility.show("DB INSERT: eseguito");

                    /*
                    * PROBLEMA RISOLTO: spiegazione del problema
                    *                   → non potevo accedere al db per una questione di context; deve essere sempre quello di chi chiama il thread.
                    *                   → non dovrei far estendere Activity a una classe che non lo è, perchè non contiene componenti grafici
                    */
                    // ERRORE: https://stackoverflow.com/questions/46333985/attempt-to-invoke-virtual-method-sqlite
                    db.saveInSupportDatabase();
                    //dbs.insertIntoSupportTable(editText);


                }
                //Toast.makeText(context, "SALVATAGGIO EDITTEXT IN DB TEMPORANEO RIUSCITO", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*
    * NON USATO
     *
     * METODO creare un timer di tot millisecondi
    */
    public synchronized boolean timer (int tempo) {
        long time = System.currentTimeMillis();
        long time2= System.currentTimeMillis();
        return time2 - time>10 ? true : false;
    }
}
