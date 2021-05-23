package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class ModificacionAlarma extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private Object Context;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificacion_alarma);
        //View view = inflanter.inflate(R.layout.modificacion_alarma, container, false);

        final AlarmManager alarma;
        //variables del layout
        Button aceptar = (Button) findViewById(R.id.btnaceptar);
        Button cancelar = (Button) findViewById(R.id.btncancelar);

        final TextView alarmaview = (TextView) findViewById(R.id.avisoalarm);

        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePiker);

        //inicio de alarma
        //alarma = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        final Calendar calendar = Calendar.getInstance();

        //metodo aceptar alarma y cambio de aviso
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hora = String.valueOf(timePicker.getCurrentHour());
                String minuto = String.valueOf(timePicker.getCurrentMinute());

                if (timePicker.getCurrentHour() < 10) {
                    hora = String.valueOf("0" + timePicker.getCurrentHour());
                }

                if (timePicker.getCurrentMinute() < 10) {
                    minuto = String.valueOf("0" + timePicker.getCurrentMinute());
                }

                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                //alarma.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                 //    AlarmManager.INTERVAL_DAY, pendingIntent);

                alarmaview.setText("La alarma sonara a las " + hora + ":" + minuto);

                Intent intent = new Intent(getApplicationContext(), RecibirAlarma.class);
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModificacionAlarma.this);
                builder.setMessage("¿Esta seguro de cancelar la configuración?");
                builder.setTitle("Alerta!");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    //al dar si va al inicio y cerramo
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), RepocitorioAlarmas.class);
                        startActivity(i);

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        //return view;
    }
}
