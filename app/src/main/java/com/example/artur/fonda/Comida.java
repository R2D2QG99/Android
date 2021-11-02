package com.example.artur.fonda;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Comida extends AppCompatActivity implements View.OnClickListener {
EditText et_nombre,et_costo,et_ing1,et_ing2,et_ing3,et_ing4,et_ing5;
Button bt_registrar2;
    //ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);
        et_nombre=(EditText)findViewById(R.id.et_nombre);
        et_costo=(EditText)findViewById(R.id.et_costo);
        et_ing1=(EditText)findViewById(R.id.et_ing1);
        et_ing2=(EditText)findViewById(R.id.et_ing2);
        et_ing3=(EditText)findViewById(R.id.et_ing3);
        et_ing4=(EditText)findViewById(R.id.et_ing4);
        et_ing5=(EditText)findViewById(R.id.et_ing5);
        bt_registrar2=(Button)findViewById(R.id.bt_registrar2);
        bt_registrar2.setOnClickListener(this);
        //progressBar2 = findViewById(R.id.progressbar2);
    }
    class async2 extends AsyncTask<String,Void, String> {
        @Override
        protected void onPreExecute() {
            //progressBar2.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String nom=et_nombre.getText().toString();
                String nom1=et_nombre.getText().toString();
                String cos=et_costo.getText().toString();
                String i1=et_ing1.getText().toString();
                String i2=et_ing2.getText().toString();
                String i3=et_ing3.getText().toString();
                String i4=et_ing4.getText().toString();
                String i5=et_ing5.getText().toString();
                Thread.sleep(100);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="insert into Ingredientes values(null,'"+nom1+"','"+i1+"','"+i2+"','"+i3+"','"+i4+"','"+i5+"',null);";
                String peticion1="insert into Platillo values(null,'"+nom+"','"+cos+"',null,null);";
                //estado.executeQuery(peticion);
                estado.executeUpdate(peticion);
                estado.executeUpdate(peticion1);
                //ResultSet result=estado.executeQuery(peticion);
                //result.next();
                //Toast.makeText(Agregar.this,"Registro realizado",Toast.LENGTH_LONG).show();
                // result.setText("Registro realizado");
                //return result;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            //progressBar2.setVisibility(View.INVISIBLE);
            //progressBar2.setVisibility(View.GONE);
            Toast.makeText(Comida.this,"Registro realizado x2 :v",Toast.LENGTH_LONG).show();
            et_nombre.setText("");
            et_costo.setText("");
            et_ing1.setText("");
            et_ing2.setText("");
            et_ing3.setText("");
            et_ing4.setText("");
            et_ing5.setText("");
        }

    }
    /*class async1 extends AsyncTask<String,Void, String> {
        @Override
        protected void onPreExecute() {
            //progressBar2.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String nom=et_nombre.getText().toString();
                String cos=et_nombre.getText().toString();
                Thread.sleep(10);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                //String peticion="insert into Ingredientes values(null,'"+i1+"','"+i2+"','"+i3+"','"+i4+"','"+i5+"');";
                String peticion="insert into Platillo values(null,'"+nom+"','"+cos+"',null,null);";
                //estado.executeQuery(peticion);
                //estado.executeUpdate(peticion);
                estado.executeUpdate(peticion);
                //ResultSet result=estado.executeQuery(peticion);
                //result.next();
                //Toast.makeText(Agregar.this,"Registro realizado",Toast.LENGTH_LONG).show();
                // result.setText("Registro realizado");
                //return result;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            //progressBar2.setVisibility(View.INVISIBLE);
            //progressBar2.setVisibility(View.GONE);
            Toast.makeText(Comida.this,"Registro realizado x1",Toast.LENGTH_LONG).show();
        }

    }*/
    @Override
    public void onClick(View v) {
    if(v.getId()==R.id.bt_registrar2){
        //new async1().execute();
        String nom=et_nombre.getText().toString();
        String nom1=et_nombre.getText().toString();
        String cos=et_costo.getText().toString();
        String i1=et_ing1.getText().toString();
        String i2=et_ing2.getText().toString();
        String i3=et_ing3.getText().toString();
        String i4=et_ing4.getText().toString();
        String i5=et_ing5.getText().toString();
        if(TextUtils.isEmpty(nom)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(cos)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(i1)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(i2)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(i3)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(i4)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(i5)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        new async2().execute();

    }
}}
