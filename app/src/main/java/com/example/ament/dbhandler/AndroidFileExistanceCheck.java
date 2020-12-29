package com.example.ament.dbhandler;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by ament on 27/11/2017.
 */

public class AndroidFileExistanceCheck {


    private final File path = Environment.getExternalStorageDirectory(); // /storage/emulated/0
    private final String fileName = "MainDatabaseHandler.db";
    private final String folderName = "DBhandlerData";
    File folder = new File(path + "/"+folderName);
    File database = new File (folder.toString()+"/"+fileName);

    boolean existsFile;
    boolean existsFolder;
    boolean existCreateOrInsertDB = false;


    String CreateOrInsertDBPath = Environment.getExternalStorageDirectory().toString()+"/DBhandlerData/cacheFolder/CreateOrInsertDB.txt";



    //--------------METODI--------------------------
    /**
     * This method checks if a file exists or not in the main folder
     *
     * @return a boolean true if it exists or false if it doea not exists
     */
    public boolean existsFile () {
        existsFile =  database.exists();
        return existsFile;
    }

    //----------------------------------------

    /**
     *  questo metodo controlla se esiste un file passato come parametro
     *
     * @param file da controllare
     * @return un booleano, true se esiste e false altrimenti
     */
    public boolean existsFile (File file) {
        existsFile =  file.exists();
        return existsFile;
    }

    //----------------------------------------

    public boolean createFile (boolean existsFile) throws IOException {
        boolean b = false;
        if (!existsFile) {

            File f = new File(database.toString());
            b = f.createNewFile();


        }
        return b;
    }
    //----------------------------------------

    /**
     * This method checks if the app folder exists
     *
     * @return a boolean, true if it exists, false otherwise
     */
    public boolean existsFolder () {
        existsFolder = folder.exists();
        return existsFolder;
    }

    //----------------------------------------

    /**
     * This method will create the app folder in /storage/emulated/0
     *
     *  @return a boolean, true if the folder is created , false otherwise
     */
    public boolean createFolder () throws IOException {
        boolean success = false;
        boolean success1 = false;
        File f = new File(CreateOrInsertDBPath);
        if (!existsFolder) {
            File cacheFolder = new File (folder.toString()+"/cacheFolder");
            success = folder.mkdir();   // Creo la cartella principale
            cacheFolder.mkdir();        // Creo la sottocartella cacheFolder
            f.createNewFile();          // Creo CreateOrInsertDB per poter ricordare se creare un db
                                        // oppure usarne uno già pre- esistente
        }
        return success;
    }

    //----------------------------------------

    /**
     * Questo metodo recupera il nome del file dal suo percorso completo
     *
     * @param completePath percorso completo del file dal quale prelevare il nome
     * @return il nome del file
     */
    public String getFileNameFromPath (File completePath) {
        return completePath.getName();
    }

    //----------------------------------------
    /*
    *   NON FUNZIONA, RISOLVERE
    */

    public static void copy (File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().createNewFile();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }



}
