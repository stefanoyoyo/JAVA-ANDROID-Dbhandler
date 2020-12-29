package com.example.ament.dbhandler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ament on 12/12/2017.
 */

public class SupportDatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private String database_name = "SupportDatabaseHandler.db";
    private String table_name = "TABELLA_DI_SUPPORTO";
    private static String how_to_order = "data";

    public SupportDatabaseHandler (Context context) {
        super(context, "SupportDatabaseHandler.db", null, 1);
   //   onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SUPPORT_TABLE = "CREATE TABLE if not exists " + table_name + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nota_memorizzata TEXT) ";
        db.execSQL(CREATE_SUPPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
    /*
    *       METODI
    */

    //-----------------------------------------
    /*      SHOW MESSAGE    */

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
    /*      GET ALL DATA        */

    /**
     * This method is used to get all thr data from the table.
     *
     * @return a method contaning the data required. Its containement can't be shown, it needs to be
     *         tranformed into a stirng buffer, so another method which does it is required.
     */
    public Cursor getAllData(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name, null);
        return res;
    }

    //-----------------------------------------
    /*  VIEW ALL FROM SUPPORT DATABASE      */

    public void viewAllFromSupportTable(Context c) {
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
            buffer.append("Nota memorizzata: "+ res.getString(1)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString(),c);
    }

    //-----------------------------------------
    /*      INSERISCO DATI NELLA TABELLA DI SUPPORTO    */

    public boolean insertIntoSupportTable (String NotaDaInserire) {
        boolean b = true;
        try {
            System.out.println("insertIntoSupportTable method started");
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();                  // INSERISCO I VALORI PASSATI COME PARAMETRO
            contentValues.put("nota_memorizzata", NotaDaInserire);
            long result = database.insert(table_name, null, contentValues);

            if (result != 1) {
                b =  true;
            } else {
                b = false;
            }

        } catch (Exception exc) {
            System.err.println("Fatal error occurred");
        }
        return b;
    }

    //-----------------------------------------
    /*      VERIFICO SE LA TABELLA CONTIENE QUALCOSA O E' VUOTA    */

    /**
     *
     * @param db database to pass as parameter
     * @param table to pass as parameter
     * @return a boolean
     */

    public boolean checkEmpty(SQLiteDatabase db, String table){
        return DatabaseUtils.queryNumEntries(db, table) == 0;
    }

    //-----------------------------------------
    /*      VERIFICO SE LA TABELLA CONTIENE QUALCOSA O E' VUOTA    */

    /**
     * @return a boolean, true if table exists, false otherwise
     */

    public boolean checkEmpty(){
        return DatabaseUtils.queryNumEntries(this.getWritableDatabase(), table_name) == 0;
    }

    //-----------------------------------------
    /*      OTTENGO LA STRINGA NELL'ULTIMA TUPLA DELLA TABELLA    */

    public StringBuffer getLasPos (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        StringBuffer buffer = new StringBuffer();
        Cursor res = getAllData("TABELLA_DI_SUPPORTO");;
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
    /*      OTTENGO L'ID NELL'ULTIMA TUPLA DELLA TABELLA    */

    public StringBuffer getLasID (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        StringBuffer buffer = new StringBuffer();
        Cursor res = getAllData("TABELLA_DI_SUPPORTO");;
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return null;
        }

        res.moveToLast();
        buffer.append(res.getString(0)+"\n\n");
        return buffer;
    }

    //-----------------------------------------
    /*      MOSTRO LA STRINGA NELL'ULTIMA TUPLA DELLA TABELLA    */

    public String viewLastPos (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData("TABELLA_DI_SUPPORTO");;
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToLast();
        buffer.append("Id pos: "+ res.getString(0)+"\n");
        buffer.append("Pos: "+ res.getString(1)+"\n\n");

        // Show all data
        showMessage("LAST POSITION DATA \n",buffer.toString(),c);
        return buffer.toString();
    }




}
