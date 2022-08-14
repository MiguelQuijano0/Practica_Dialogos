 package com.example.practica_dialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
     Button toastsimple;
     Button toastpersonalizado;
     Button dialogsimple;
     Button dialogpersonalizado;
     Button correo;
     private Boolean usado;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         toastsimple = (Button) findViewById(R.id.Toastsimple);
         toastpersonalizado = (Button) findViewById(R.id.Toastpersonalizado);
         dialogsimple = (Button) findViewById(R.id.Dialogsimple);
         dialogpersonalizado = (Button) findViewById(R.id.Dialogpersonalizado);
         correo = (Button) findViewById(R.id.Correo);

         usado = false;
         toastsimple.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!usado)
                     mostrarToast();
             }
         });
         toastpersonalizado.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mostrarToastpersonalizado();
             }
         });
         dialogsimple.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mostrarDialogo();
             }
         });
         dialogpersonalizado.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mostrarDialogopersonalizado();
             }
         });
         correo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 capturarEmail();
             }
         });
     }

     private void mostrarToast() {
         Toast.makeText(getApplicationContext(), "Seleccionaste Toast Simple", Toast.LENGTH_LONG).show();
     }

     private void mostrarToastpersonalizado() {
         Toast toast = new Toast(this);
         toast.setDuration(Toast.LENGTH_LONG);
         toast.setGravity(Gravity.CENTER,0,50);
         LayoutInflater inflater = getLayoutInflater();
         View view = inflater.inflate(R.layout.customtoast,null);
         toast.setView(view);
         toast.show();
     }


     private void mostrarDialogopersonalizado(){
         AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
         final View customlayout = getLayoutInflater().inflate(R.layout.diaologopersonalizado,null);
         alert.setView(customlayout);
         alert.setCancelable(false);
         EditText mail = customlayout.findViewById(R.id.email);

         alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {
                 String email = mail.getText().toString();
             }
         });
         AlertDialog dialog = alert.create();
         dialog.show();
     }

     private void capturarEmail() {
         final EditText email = new EditText(this);
         AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
         alert.setTitle("Ingresa un correo electrónico");
         alert.setCancelable(false);
         alert.setView(email);
         alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {
                 String correo = email.getText().toString();
                 Toast.makeText(getApplicationContext(), "Su correo es: " + correo, Toast.LENGTH_LONG).show();
             }
         });
         AlertDialog dialog = alert.create();
         dialog.show();
     }
     private void mostrarDialogo() {
         AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
         alert.setTitle(R.string.txtbienvenida);
         alert.setIcon(R.drawable.toast);
         alert.setPositiveButton(R.string.txtpositivo, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {
                 Toast.makeText(getApplicationContext(), "Gracias por Aceptar", Toast.LENGTH_LONG).show();
                 }
         });
         alert.setNegativeButton(R.string.txtnegativo, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {
                 Toast.makeText(getApplicationContext(), "Opcion Cancelada", Toast.LENGTH_LONG).show();
             }
         });
         String[] opc = {"Continuar viendo a tu ex", "Ver solo una vez mas a tu ex", "No volver a ver a tu ex"};
         alert.setSingleChoiceItems(opc, 0, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {
                 Toast.makeText(getApplicationContext(), "Opcion " + i + " seleccionada", Toast.LENGTH_LONG).show();
                 switch (i) {
                     case 0:
                         usado = false;
                         break;
                     case 1:
                         break;
                     case 2:
                         usado = true;
                         break;
                 }
             }
         });
         AlertDialog dialog = alert.create();
         dialog.show();
     }
 }