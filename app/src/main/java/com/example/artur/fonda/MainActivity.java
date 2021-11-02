package com.example.artur.fonda;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_user,et_pass;
    Button bt_ingresar,bt_registrar;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user= findViewById(R.id.et_user);
        et_pass= findViewById(R.id.et_pass);
        bt_ingresar= findViewById(R.id.bt_ingresar);
        bt_ingresar.setOnClickListener(this);
        bt_registrar= findViewById(R.id.bt_registrar);
        bt_registrar.setOnClickListener(this);
        progressBar1= findViewById(R.id.progressBar);
    }
    class asyncadmon extends AsyncTask<String,Void, String>{
        String ConnectionResult = "";
        Boolean isSuccess = false;
        Boolean success=false;
        @Override
        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String email = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                Thread.sleep(100);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="Select * from Empleado where nombreuser='" + email + "'and password='" + pass + "'";
                //estado.executeQuery(peticion);
                ResultSet result=estado.executeQuery(peticion);
                if (result.next()) {
                    ConnectionResult = "Login successful";
                    success = true;
                    conn.close();
                } else {
                    ConnectionResult = "Invalid Credentials!";
                    success = false;
                }
                String peticion1="Select * from Cliente where nombreuser='" + email + "'and password='" + pass + "'";
                //estado.executeQuery(peticion);
                ResultSet result1=estado.executeQuery(peticion1);
                if (result1.next()) {
                    ConnectionResult = "Login successful";
                    isSuccess = true;
                    conn.close();
                } else {
                    ConnectionResult = "Invalid Credentials!";
                    isSuccess = false;
                }
                //return result;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (Exception ex)
            {
                isSuccess = false;
                ConnectionResult = ex.getMessage();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            progressBar1.setVisibility(View.INVISIBLE);
            progressBar1.setVisibility(View.GONE);
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            /*if(isSuccess)
            {
                Intent e = new Intent(getApplicationContext(),Menu1.class);
                e.putExtra("usuario",et_user.getText().toString());
                startActivity(e);
                Toast.makeText(MainActivity.this , "Logeado correctamente" , Toast.LENGTH_LONG).show();
                et_pass.setText("");
                et_user.setText("");
               // finish();
            }else{
                Toast.makeText(MainActivity.this , "Error Usuario/Contraseña incorrectos" , Toast.LENGTH_LONG).show();
            }*/
            if(success){
                Intent e = new Intent(getApplicationContext(),Menu3.class);
                e.putExtra("usuario",et_user.getText().toString());
                startActivity(e);
                Toast.makeText(MainActivity.this , "Logeado correctamente" , Toast.LENGTH_LONG).show();
                //finish();
                et_pass.setText("");
                et_user.setText("");
            }
            if(isSuccess)
            {
                Intent e = new Intent(getApplicationContext(),Menu1.class);
                e.putExtra("usuario",et_user.getText().toString());
                startActivity(e);
                Toast.makeText(MainActivity.this , "Inicio satisfactorio" , Toast.LENGTH_LONG).show();
                //finish();
                et_pass.setText("");
                et_user.setText("");
                //finish();
            }
            if (success==false && isSuccess==false){
                Toast.makeText(MainActivity.this , "Error Usuario/Contraseña incorrectos" , Toast.LENGTH_LONG).show();
            }
        }

    }
    /*class async1 extends AsyncTask<String,Void, String>{
        String ConnectionResult = "";
        Boolean isSuccess = false;
        @Override
        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String email = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                Thread.sleep(3000);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="Select * from Cliente where nombreuser='" + email + "'and password='" + pass + "'";
                //estado.executeQuery(peticion);
                ResultSet result=estado.executeQuery(peticion);
                if (result.next()) {
                    ConnectionResult = "Login successful";
                    isSuccess = true;
                    conn.close();
                } else {
                    ConnectionResult = "Invalid Credentials!";
                    isSuccess = false;
                }
                //return result;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (Exception ex)
            {
                isSuccess = false;
                ConnectionResult = ex.getMessage();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            progressBar1.setVisibility(View.INVISIBLE);
            progressBar1.setVisibility(View.GONE);
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Intent e = new Intent(getApplicationContext(),Menu1.class);
                e.putExtra("usuario",et_user.getText().toString());
                startActivity(e);
                Toast.makeText(MainActivity.this , "Inicio satisfactorio" , Toast.LENGTH_LONG).show();
                //finish();
                et_pass.setText("");
                et_user.setText("");
                //finish();
            }
        }

    }*/
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_registrar){
            Intent i = new Intent(getApplicationContext(),Agregar.class);
            startActivity(i);
        }
        if (v.getId()==R.id.bt_ingresar){
            String email = et_user.getText().toString();
            String pass = et_pass.getText().toString();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Se debe ingresar un usuario",Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
                return;
            }
           // new async1().execute(et_user.getText().toString());
            new asyncadmon().execute(et_user.getText().toString());
            }
        }
    }
