package com.example.artur.fonda;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Pass_admon extends AppCompatActivity implements View.OnClickListener {
    EditText et_pass;
    TextView et_user;
    Button bt_cambiar;
    //ProgressBar progressbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_c);
        et_pass=(EditText)(findViewById(R.id.et_pass));
        bt_cambiar=(Button)(findViewById(R.id.bt_cambiar));
        bt_cambiar.setOnClickListener(this);
        //progressbar1= findViewById(R.id.progressBar);
        et_user=(TextView)(findViewById(R.id.et_user));
        String nombreUser=getIntent().getStringExtra("usuario");
        et_user.setText(nombreUser);
    }
    class async1 extends AsyncTask<String,Void, String> {
        String ConnectionResult = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            //progressbar1.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                //Thread.sleep(2000);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda", "u530930615_artur", "hn159728");
                Statement estado = conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion = "Update Empleado Set password='" + pass + "'where nombreuser='" + user +"'";
                //estado.executeQuery(peticion);
                //ResultSet result = estado.executeQuery(peticion);
                estado.executeUpdate(peticion);
                /*if (result.next()) {
                    ConnectionResult = "Cambios realizados";
                    isSuccess = true;
                    conn.close();
                } else {
                    ConnectionResult = "Invalid Credentials!";
                    isSuccess = false;
                }*/
                //return result;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                isSuccess = false;
                ConnectionResult = ex.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //progressbar1.setVisibility(View.INVISIBLE);
            //progressbar1.setVisibility(View.GONE);
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Intent e = new Intent(getApplicationContext(), Menu3.class);
                //e.putExtra("usuario",et_user.getText().toString());
                startActivity(e);
                Toast.makeText(Pass_admon.this, "Cambio realizado", Toast.LENGTH_LONG).show();
                //finish();
                et_pass.setText("");
            }
        }
    }
    @Override
    public void onClick(View v) {
        new async1().execute();
        Intent e = new Intent(getApplicationContext(), Menu3.class);
        e.putExtra("usuario",et_user.getText().toString());
        startActivity(e);
        Toast.makeText(Pass_admon.this, "Cambio realizado", Toast.LENGTH_LONG).show();
        //finish();
        et_pass.setText("");
        // this.finish();
    }
}