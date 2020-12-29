package com.example.ament.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by ament on 15/12/2017.
 *
 * 1 = checkbox data all'inizio della nota attivat0
 * 0 = altrimenti
 */

public class Impostazioni_DATABASE extends SQLiteOpenHelper {

    String table_name = "IMPOSTAZIONI_TABLE";
    SQLiteDatabase Externaldatabase;
    SQLiteDatabase database;

    public Impostazioni_DATABASE (Context context) {
        super(context, "Impostazioni_DATABASE.db", null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    public Impostazioni_DATABASE (Context context, boolean b) {
        super(context, Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/Impostazioni_DATABASE.db", null, 1);
        SQLiteDatabase Externaldatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMPOSTAZIONI_TABLE = "CREATE TABLE if not exists " + table_name + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dataEora TEXT, " +
                "data_a_inizio_nota TEXT) ";
        db.execSQL(CREATE_IMPOSTAZIONI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    //-----------------------------------------
    /*  UPGRADE SQlite QUERY: non funziona!   */

    public boolean updateData(String id,String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("Data_a_inizio_nota",value);
        db.update(table_name, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    //-----------------------------------------
    /*  LEGGO IL CONTENUTO DELLA RIGA NELLA COLONNA Data_a_inizio_nota  */

    public Cursor read_Data_a_inizio_nota () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Data_a_inizio_nota from "+table_name ,null);
        return res;
    }

 //-----------------------------------------
    /*  LEGGO IL CONTENUTO DELL'ID  */

    public Cursor getID () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from "+table_name ,null);
        return res;
    }

    //-----------------------------------------
    /*  LEGGO IL CONTENUTO DELL'ULTIMA ID  */

    public StringBuffer getLastID () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name ,null);
        res.moveToLast();
        StringBuffer buffer = new StringBuffer();
        buffer.append(res.getString(1)+" ");
        return buffer;
    }

    //-----------------------------------------
    /*  LEGGO IL CONTENUTO DELL'ULTIMA ID  */

    public Cursor getLastDataAllinizoDellaNota () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select max(id) from "+table_name ,null);
        return res;
    }

    //-----------------------------------------
    /*  TRASFORMO IL DATO LETTO DA CURSOR A STRINGBUFFER  */

    public StringBuffer cursorToStringBuffer (Cursor res) {
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0));
            buffer.append("------------------------------");
        }
        return buffer;
    }

    //-----------------------------------------
    /*  AGGIORNO VALORE NELLA CHECKBOX: essendoci solo 2 valori, non servono parametri, basta leggere
                                        il lavore di quello corrente e farne la negazione                */

    public void updateDataAllInizioValue () {
        SQLiteDatabase db = this.getWritableDatabase();

    }
    //-----------------------------------------
    /*INSERT INTO SQlite QUERY*/

    public boolean insertData (String data_a_inizio_nota) {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().toString() + "/DBhandlerData/Impostazioni_DATABASE.db", null, 0);
        SQLiteDatabase Externaldatabase = SQLiteDatabase.openDatabase("/data/data/com.example.ament.dbhandler/databases/Impostazioni_DATABASE.db", null, 0);
        DataEora d = new DataEora() ;
        String dataora = d.getDataEora();

  //    SQLiteDatabase database = this.getWritableDatabase();               // POSSO SCRIVERE SUL DATABASE
        // RICHIAMO ON CREATE PER ACCERTARMI CHE ESISTA LA TABELLA: se esiste già non fa nulla
        System.err.println("Inserimento eseguito");
        ContentValues contentValues = new ContentValues();                  // INSERISCO I VALORI PASSATI COME PARAMETRO
        contentValues.put("dataEora",dataora);
        contentValues.put("data_a_inizio_nota",data_a_inizio_nota);
        long result = database.insert(table_name,null,contentValues);
        database.insert(table_name,null,contentValues);
        Externaldatabase.insert(table_name,null,contentValues);


        if (result != 1) {
            return true; }
        else {
            return false; }
    }



}
