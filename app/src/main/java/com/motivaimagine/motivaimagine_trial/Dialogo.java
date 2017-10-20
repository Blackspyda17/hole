package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


/**
 * Created by gpaez on 10/20/2017.
 */

public class Dialogo {
    public static void messageDialog(final Activity a, String title, String message){



       AlertDialog dialogo = new AlertDialog.Builder(a)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

            /*            int id= android.os.Process.myPid();
                        android.os.Process.killProcess(id);*/


                       // System.exit(0);
                    }
                })
                .setIcon(R.drawable.ic_error_black_24dp)
                .show();

    }

    public static void messageDialog(final Activity a){



        new AlertDialog.Builder(a)
                .setTitle("Network Connection")
                .setMessage("No Internet Connection")
                .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        a.finish();
            /*            int id= android.os.Process.myPid();
                        android.os.Process.killProcess(id);*/


                        // System.exit(0);
                    }
                })
                .setIcon(R.drawable.ic_error_black_24dp)
                .show();


    }



}
