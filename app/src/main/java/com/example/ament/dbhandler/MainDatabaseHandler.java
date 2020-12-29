package com.example.ament.dbhandler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by ament on 11/08/2017.
 *
 * MODEL OF DATABASE NEEDED
 *
 * MainDatabaseHandler (Id int primary key AUTOINCREMENT, note_memorizzate TEXT, data DATE, ora DATETIME);
 *
 * TEORIA:  - per eseguire delle query senza ritorno di valori, è sufficiente scrivere dei comandi SQL all'interno
 *          di db.execSQL(QUERY_TO_RUN).
 *          - Se invece fosse necessario avere dei valori di ritorno, come nelle query,
 *          sarebbe necessario usare un oggetto di tipo cursor, nel quale inserire il risultato della query fatta
 *          mediante  Cursor res = db.rawQuery("select * from "+table_name,null);.
 *          - Per inserire all'interno di una
 *          tabella, occorre inserire tramite il metodo insert, un oggetto di tipo ContentValue in cui inserire i
 *          valori, uno per colonna tramite il metodo put.
 *
 *          NB: è possibile fare delle query sia mediante l'utilizzo di java, che mediante l'utilizzo di SQL!
 *
 * COME CREARE UN DATABASE: -   creare una classe che estende SQLiteOpenHelper e implementa i suoi metodi astratti
 *                          -   richimare il super nel construttore con 4 parametri
 *                              ES: super(context, "PARKCAR_DATABASE.db", null, 1);
 *                          -   Nella classe che utilizza il database (creando un'istanza di quello che lo crea),
 *                              occorre utilizzare (sull'istanza) il metodo .getWritableDatabase();
 *                              ES: nella classe MemorizzaPosizioneAuto
 *
 *                                PositionDatabase db = new PositionDatabase(getApplicationContext());
 *                                ...
 *                                db.getWritableDatabase();
 */

public class MainDatabaseHandler extends SQLiteOpenHelper {

    public static StringBuffer stringheAccorciate;

    Context context;
    private String database_name = "MainDatabaseHandler.db";
    private String table_name = "NOTES_TABLE";
    private String support_table_name = "TABELLA_DI_SUPPORTO";
    private static String how_to_order = "data";                    // This variable is initialized to "data". It allows to order the
                                                                    // result of the query specifing how to order.

    public MainDatabaseHandler(Context context) {
        super(context, "MainDatabaseHandler.db", null, 1);
  //    SQLiteDatabase database = this.getWritableDatabase();       // This line allows the program to view and access the database
                                                                    // HO COMMENTATO QUESTA LINEA DI CODICE PERCHE TANTO LA INSERICO
                                                                    // NEL METODO INSERT!
    }

    public MainDatabaseHandler(Context context, boolean saveOnExternalPath) {
        super(context, Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/MainDatabaseHandler.db", null, 1);
    }

    //-----------------------------------------
    /*  CREATE TABLE QUERY  */

    /**
     * ON CREATE:   creo la tabella del database. Nella query ho inserito una
     *              CREATE TABLE IF NOT EXISTS per poter verificare l'esistenza
     *              della tabella ad ogni inserimento e crearne una nuova
     *              qualora non dovesse già esistere.
     *
     * @param db su cui scrivere la tabella
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREO LA TABELLA PRINCIPALE
            String CREATE_MAIN_TABLE = "CREATE TABLE if not exists " + table_name + "( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nota_memorizzata TEXT, " +
                    "data DATE," +
                    "ora DATETIME )";
            db.execSQL(CREATE_MAIN_TABLE);
    }
    //-----------------------------------------
    /*  DROP TABLE IF EXISTS    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        /*
        db.execSQL("DROP TABLE IF EXISTS " + support_table_name);
        */
        onCreate(db);
    }

    //-----------------------------------------
    /*  UPGRADE SQlite QUERY    */

    /**
     * This method can be used to update the values contained into the row of a database table
     *
     * @param id to update
     * @param name to update
     * @param surname to update
     * @param marks to update
     * @return true if update happened succesfully
     */
    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("nota_memorizzata",name);
        contentValues.put("data",surname);
        contentValues.put("ora",marks);
        db.update(table_name, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    //-----------------------------------------
    /*  DELETE FROM .. WHERE id = id inserito SQlite QUERY   */

    /**
     * This method deletes the rows equal to the id value. Id is a primary key so just one row will be deleted
     *
     * @param id to delete fro the table
     * @return the number of rows deleted
     */
    public Integer onDeleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table_name, "ID = ?",new String[] {id});
    }

    //-----------------------------------------

    public Cursor queryIDfromStringaMemorizzata (String stringa) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from NOTES_TABLE where nota_memorizzata = ' + stringa + ';",null);
        return res;
    }
    //-----------------------------------------
    /*  DELETE FROM SQlite QUERY    */

    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete All
        db.execSQL("delete from "+ table_name);
        // 3. close
        db.close();
    }
    //-----------------------------------------
    /*  ESISTE IL DATABASE? */

    public boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    //-----------------------------------------

    public boolean insertData (SQLiteDatabase db, String nota_memorizzate, String data, String ora) {
        SQLiteDatabase database = this.getWritableDatabase();               // POSSO SCRIVERE SUL DATABASE
        // RICHIAMO ON CREATE PER ACCERTARMI CHE ESISTA LA TABELLA: se esiste già non fa nulla

        System.err.println("Inserimento eseguito");
        ContentValues contentValues = new ContentValues();                  // INSERISCO I VALORI PASSATI COME PARAMETRO
        contentValues.put("nota_memorizzata", nota_memorizzate);
        contentValues.put("data",data);
        contentValues.put("ora",ora);
        long result = database.insert(table_name,null,contentValues);
        db.insert(table_name,null,contentValues);

        if (result != 1) {
            return true; }
        else {
            return false; }
    }

    //-----------------------------------------
    /*INSERT INTO SQlite QUERY*/

    /**
     * This method adds the parameter elements into a row of the table
     *
     * NO ID WILL BE ADDED, BECAUSE IT AUTOINCREMENTS ITSELF!
     * @param nota_memorizzate second column
     * @param data third column
     * @param ora fourth column
     * @return false if .insert method from SQLiteOpenHelper inserts the value into the table and return -1, true otherwise.
     */
    public boolean insertData (String nota_memorizzate, String data, String ora) {
        SQLiteDatabase database = this.getWritableDatabase();               // POSSO SCRIVERE SUL DATABASE
                                                                            // RICHIAMO ON CREATE PER ACCERTARMI CHE ESISTA LA TABELLA: se esiste già non fa nulla
        SQLiteDatabase db =  SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/MainDatabaseHandler.db", null,0);
        SQLiteDatabase db1 = SQLiteDatabase.openDatabase("/data/data/com.example.ament.dbhandler/databases/MainDatabaseHandler.db",null,0);
        System.err.println("Inserimento eseguito");
        ContentValues contentValues = new ContentValues();                  // INSERISCO I VALORI PASSATI COME PARAMETRO
        contentValues.put("nota_memorizzata", nota_memorizzate);
        contentValues.put("data",data);
        contentValues.put("ora",ora);
        long result = database.insert(table_name,null,contentValues);
        db.insert(table_name,null,contentValues);
        db1.insert(table_name,null,contentValues);


        if (result != 1) {
            return true; }
            else {
                return false; }
    }

    //--------------- QUERIES ON DATABASE ------------------------

    /**
     * This method is used to get all thr data from the table.
     *
     * @return a method contaning the data required. Its containement can't be shown, it needs to be
     *         tranformed into a stirng buffer, so another method which does it is required.
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        return res;
    }

    //-----------------------------------------

    public Cursor getIDandNote() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id,nota_memorizzata from "+table_name,null);
        StringBuffer s = new StringBuffer();
        return res;
    }

    //-----------------------------------------
    /*      OTTENGO (TUTTE LE STRINGHE DELLA TABELLA) LA STRINGA NELL'ULTIMA TUPLA DELLA TABELLA    */

    public Cursor getAllNote() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select nota_memorizzata from "+table_name,null);
        return res;
    }

    //-----------------------------------------
    /*      VEDO TUTTE LE NOTE      */

    public StringBuffer viewAllNote (Cursor c) {
        c = getAllNote();
        StringBuffer buffer = new StringBuffer();
        int cont = 0;
        while (c.moveToLast()) {
            buffer.append("NOTA ("+cont+") -> " + c.getString(0));
            cont++;
            buffer.append("------------------------------");
        }
        return buffer;
    }



    //-----------------------------------------

    public StringBuffer getLasPos (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        StringBuffer buffer = new StringBuffer();
        Cursor res = getAllData(table_name);;
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return null;
        }

        res.moveToLast();
        buffer.append(res.getString(1)+" ");
        return buffer;
    }

    //-----------------------------------------
    /**
     * This method is used to get all thr data from the table.
     *
     * @return a method contaning the data required. Its containement can't be shown, it needs to be
     *         tranformed into a stirng buffer, so another method which does it is required.
     */
    public Cursor getAllData(String table_name) {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            res = db.rawQuery("select * from " + table_name, null);
        } catch (NullPointerException e) {
            System.err.println("ERROR: 0 " + e);
        }
        return res;
    }

    //-----------------------------------------
    public void viewAll(String table_name, Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData(table_name);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id pos: "+ res.getString(0)+"\n");
            buffer.append("Data: "+ res.getString(2)+"\n");
            buffer.append("Ora: "+ res.getString(3)+"\n");
            buffer.append("Nota memorizzata: "+ res.getString(1)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString(),c);
    }
    //-----------------------------------------
    /*  CONVERTE L'OGGETTO CURSOR IN CUI SONO STATE INSERITE LE TUPLE DEL DATABASE, IN UNO STRINGBUFFER */

    public StringBuffer cursorToStringBuffer (Cursor res) {
        StringBuffer buffer = new StringBuffer();
        int cont = 1;
        while (res.moveToNext()) {
            buffer.append("NOTA ("+cont+")\n" + res.getString(0));
            cont++;
            buffer.append("------------------------------");
        }
        return buffer;
    }

    //-----------------------------------------

    public void deleteLineByID (String query,String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from NOTES_TABLE where id = " + id);

    }

    //-----------------------------------------
    public StringBuffer cursorToStringBuffer_0 (Cursor res) {
        StringBuffer buffer = new StringBuffer();
        int cont = 1;
        while (res.moveToNext()) {
            buffer.append("ID --> " + res.getString(0) + " --------\n");
            buffer.append("NOTA ("+cont+") -> " + res.getString(1));


            cont++;
            buffer.append("------------------------------");
        }
        return buffer;
    }

    //-----------------------------------------
    /**
     * This method is used to show the content of the table_name table. It does  query to the
     * database_name by using the getAllData() method. Finally, it translates the values contained
     * the values of a row to a String value.
     *
     * @param c to get the context from the activity. No getApplicationContext() can be used here, but just "name_activity.class"!
     *
     * THIS METHOD IS USED TO SHOW THE CONTAINMENT OF THE TABLE
     */
    public void viewAll(Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id pos: "+ res.getString(0)+"\n");
            buffer.append("Data: "+ res.getString(2)+"\n");
            buffer.append("Ora: "+ res.getString(3)+"\n");
            buffer.append("Nota memorizzata: "+ res.getString(1)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString(),c);
    }
    //-----------------------------------------

    /*
    public void viewAllFromSupportTable(Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData(support_table_name);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id pos: "+ res.getString(0)+"\n");
            buffer.append("Data: "+ res.getString(2)+"\n");
            buffer.append("Ora: "+ res.getString(3)+"\n");
            buffer.append("Nota memorizzata: "+ res.getString(1)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString(),c);
    }
    */
    //-----------------------------------------

    /**
     * This method is used to get the max value of a SQLIte colums
     *
     * @param parameter_to_select to get from the table
     * @return The data
     */
    public Cursor selectMAX (String parameter_to_select) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT MAX(" + parameter_to_select + ") FROM " + table_name,null) ;
        return res;
    }
    //-----------------------------------------
    public void viewLastPos (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData();;
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToLast();
        buffer.append("Id pos: "+ res.getString(0)+"\n");
        buffer.append("Data: "+ res.getString(2)+"\n");
        buffer.append("Ora: "+ res.getString(3)+"\n");
        buffer.append("NOTA: "+ res.getString(1)+"\n\n");

        // Show all data
        showMessage("LAST POSITION DATA \n",buffer.toString(),c);
    }
    //-----------------------------------------
        public String selectJustLastPosition (Context c) {
            SQLiteDatabase myDb = this.getWritableDatabase();
            Cursor res = getAllData();;
            if(res.getCount() == 0) {
                // show message
                showMessage("Error","Nothing found",c);
                return null;
            }
            StringBuffer buffer = new StringBuffer();
            res.moveToLast();
            buffer.append("NOTA: "+ res.getString(1)+"\n\n");

            // Show all data
          //   showMessage("LAST POS. ",buffer.toString(),c);

            return res.getString(1);
        }
    //-----------------------------------------
    /*  AGGIORNA ID RIMETTENDOLI IN ORDINE*/
    /*  Non posso aggiornare una chiave primaria, faro quindi una query nella quale seleziono
    *   le tuple non nulle ordinate in ordine crescente. Cancello poi la tabella e la ricreo
        inserendo i valori corretti */

    public void reorderIDs () {
        // FACCIO LA QUERY
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("delete from " + table_name + " where id is null",null);
        StringBuffer buffer = new StringBuffer();
        int n = 1;
        res.moveToFirst();
        while (res.moveToNext()) {
       //   buffer.append(n); n++;              // inserisco n e aumento di 1
       //   posso anche evitare di inserire una variabile che incrementa da sola come id della
       //   tabella, perchè lo fa da sola la primary key nello schema sqlite con AUTOINCREMENT

            buffer.append(res.getString(1));    // inserisco la nota memorizzata
            buffer.append(res.getString(2));    // inserisco la data
            buffer.append(res.getString(3));    // inserisco l'ora
        }

        // finito di prelevare le tuple dalla tabella, cancello quella esistente e ne creo una uguale
        myDb.execSQL("DROP TABLE " + table_name);
        onCreate(myDb);


        // creo nuova tabella con i valori non nulli
        res.moveToFirst();
        while (res.moveToNext()) {
            insertData(res.getString(1), res.getString(2), res.getString(3));
        }


    }

    //-----------------------------------------

    public void reorderIDs (SQLiteDatabase myDb) {
        // FACCIO LA QUERY
        Cursor res = myDb.rawQuery("delete from " + table_name + " where id is null",null);
        StringBuffer buffer = new StringBuffer();
        int n = 1;
        res.moveToFirst();
        while (res.moveToNext()) {
            //   buffer.append(n); n++;              // inserisco n e aumento di 1
            //   posso anche evitare di inserire una variabile che incrementa da sola come id della
            //   tabella, perchè lo fa da sola la primary key nello schema sqlite con AUTOINCREMENT

            buffer.append(res.getString(1));    // inserisco la nota memorizzata
            buffer.append(res.getString(2));    // inserisco la data
            buffer.append(res.getString(3));    // inserisco l'ora
        }

        // finito di prelevare le tuple dalla tabella, cancello quella esistente e ne creo una uguale
        myDb.execSQL("DROP TABLE " + table_name);
        onCreate(myDb);


        // creo nuova tabella con i valori non nulli
        res.moveToFirst();
        while (res.moveToNext()) {
            insertData(myDb,res.getString(1), res.getString(2), res.getString(3));
        }


    }

    //-----------------------------------------

    public void viewMax (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = selectMAX("id");
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("MAX ID "+ res.getString(0)+"\n");
        }

        // Show all data
        showMessage("MAX ID ",buffer.toString(),c);
    }

    //-----------------------------------------

    /**
     * This method is used to show a message into an alertDialog windows
     *
     * @param title to set for the window
     * @param Message to show inside the window
     */
    public void showMessage(String title,String Message,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //-----------------------------------------
    public void dropAndRemakeTable () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+ table_name);
        System.err.println("TABLE DROPPED SUCCESFULLY!");
        onCreate(db);
        System.err.println("TABLE CREATED SUCCESFULLY!");
        db.close();
    }
    //-----------------------------------------
    public boolean isDatabaseEmpty () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table_name, null);
        Boolean rowExists;
        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;
        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }
    //-----------------------------------------
    /*      ELIMINO DATA    */
    public void eliminaData (String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+table_name+ " where data = '" + data + "'");
    }

    //-----------------------------------------
    /*  COPIO IL DATABASE IN UNA CARTELLA ACCESSIBILE*/

    public void copyDB () {
      //  String CreateOrInsertDBPath = Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/cacheFolder/CreateOrInsertDB.txt";

        String DBPath = Environment.getRootDirectory().toString() + "/data/data/com.example.ament.dbhandler/databases/MainDatabaseHandler.db";
        File f = new File (DBPath);     // il file esiste


    }





}
