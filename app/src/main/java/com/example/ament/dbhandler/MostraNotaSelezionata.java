package com.example.ament.dbhandler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MostraNotaSelezionata extends AppCompatActivity {

    public static EditText editText;
    public static DBactivity dBactivity = new DBactivity ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_nota_selezionata);

        editText = (EditText) findViewById(R.id.editText2);
        editText.setCursorVisible(true);    // il cursore si vede
        editText.setSelection(0);           // la editText parte dall'inizio
        editText.setSingleLine(false);      // faccio si che il cursore nella editText possa andare a capo
        editText.setFocusable(true);        // evita che alla pressione dei button la edit text apra la tastiera
                                            // per mettere il testo a capo, devo impostare la gravity a top nel file di layout

        String s = NoteSalvateNelDatabase.lineeIntere[NoteSalvateNelDatabase.pos];
        formatText(true,s);
         //        editText.setText(NoteSalvateNelDatabase.lineeIntere[NoteSalvateNelDatabase.pos]);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operOrCloseKeyboard (false);
            }
        });

    }

    /**
     *  Cerco di formattare il testo anche quando mostro una nota! NON RIUSCIO!
     **/
    public String formatText (boolean doItOrNot, String s) {

        if (doItOrNot) {
            // formatto la stringa
            System.out.println(s);
            editText.setBackgroundColor(Color.BLACK);
            editText.setTextColor(Color.WHITE);
     //     operOrCloseKeyboard (false);                            // tastiera bloccata
            char [] s_ = s.toCharArray();                           // trasformo in un array di caratteri per valutare ogni lettera
            String formattedString;                                 // nuova stringa in cui verra messo il testo formattatO
            String [] s_split = s.split("\n");

            Utility.showArr(s_split);
            editText.setText("");

            for (int k = 0; k< s_split.length; k++) {
                editText.setText( Html.fromHtml("<b>" + s_split[k] + "</b>") );
            }

            editText.setText( Html.fromHtml(dBactivity.ToHtmlBreaklineAndBoldInsert(s) ) );
        }
        return s;
    }

    /*
    *   METODO DEVE ESSERE LANCIATO DINAMICAMENTE DA UN BOTTONE O DA UN LISTENER
    */
    public void operOrCloseKeyboard (Boolean abilitaOdisabilitaTastiera) {
        if (abilitaOdisabilitaTastiera == true || abilitaOdisabilitaTastiera == null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            editText.setCursorVisible(true);
        }
        else if (!abilitaOdisabilitaTastiera) {
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
            editText.setCursorVisible(false);
        }
    }

}
