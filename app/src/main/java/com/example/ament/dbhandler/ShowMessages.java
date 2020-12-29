package com.example.ament.dbhandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.io.IOException;

class showMessages extends Activity {
    enum Answer { YES, NO, ERROR};
    static Answer choice;
    private String scelta = null;
    public static int sceltaa ;
    public AndroidFileExistanceCheck a ;

 /**
     * This method is used to show a message into an alertDialog windows.
	 * Mostro un messaggio all'interno di una finestra di allerta, cioè unafinestrella bianca. 
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

	//--------------------

    /**
     * QUESTO METODO MOSTRERA' IN UNA FINESTRA UN TITOLO E UNA STIRNGA.
     * DISPORRA' ANCHE DI TASTI SI E NO PER PERMETTERE ALL'UTENTE DI
     * COMUNICARE ALL'APP LA SUA DECISIONE.
     *
     * @param title da mostrare
     * @param Message da mostrare
     * @param context della finestra, del tipo MainActivity.this
     */
	public void showMessageWithButtons (String title,String Message,Context context) {
        a= new AndroidFileExistanceCheck();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
		builder.setPositiveButton("SI",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // call your code here
                choice = Answer.YES;
                sceltaa = 1;    // SI
                Utility.show("Sceltaa: " + sceltaa);
                try {
                    a.createFile(a.existsFile());

                    //  *** ISTRUZIONI NECESSARIE A DBhandler ma non alla classe! ***
                    /*
                    imageView.setImageResource(R.drawable.ok);
                    Intent intent = new Intent(getApplicationContext(), DBactivity.class);
                    finish();
                    startActivity(intent);
                    */
                    //  *** ISTRUZIONI NECESSARIE A DBhandler ma non alla classe! ***

                } catch (IOException e) {}
            }
        });
		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                choice = Answer.NO;
                sceltaa = 0;    // NO
                Utility.show("SHOWMESSAGES/Sceltaa: " + sceltaa);
            }
        });

        builder.setMessage(Message);
        builder.show();

    }

    //--------------------

    public String getScelta() {
        return scelta;
    }
}

