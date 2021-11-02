package com.example.artur.fonda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Agregar extends AppCompatActivity implements View.OnClickListener {
    EditText et_user,et_pass,et_id,et_nombre,et_telefono,et_col,et_calle,et_mun;
    Button bt_registrar;
    ProgressBar progressBar2;
    TextView result;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);
        et_user= (EditText) findViewById(R.id.et_user);
        et_pass=(EditText) (findViewById(R.id.et_pass));
        et_nombre= (EditText) findViewById(R.id.et_nombre);
        et_telefono=(EditText) (findViewById(R.id.et_telefono));
        et_col= (EditText) findViewById(R.id.et_col);
        et_calle=(EditText) (findViewById(R.id.et_calle));
        et_mun= (EditText) findViewById(R.id.et_mun);
        bt_registrar=(Button)(findViewById(R.id.bt_registrar));
        bt_registrar.setOnClickListener(this);
        progressBar2= findViewById(R.id.progressbar2);
        result=(TextView)(findViewById(R.id.result));
    }
    class async2 extends AsyncTask<String,Void, String> {
        @Override
        protected void onPreExecute() {
            progressBar2.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String nom=et_nombre.getText().toString();
                String tel=et_telefono.getText().toString();
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                String col=et_col.getText().toString();
                String ca=et_calle.getText().toString();
                String mun=et_mun.getText().toString();
                Thread.sleep(3000);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="insert into Cliente values(null,'"+nom+"','"+tel+"','"+user+"','"+pass+"','"+col+"','"+ca+"','"+mun+"');";
                //estado.executeQuery(peticion);
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
            progressBar2.setVisibility(View.INVISIBLE);
            progressBar2.setVisibility(View.GONE);
            Toast.makeText(Agregar.this,"Registro realizado",Toast.LENGTH_LONG).show();
            et_nombre.setText("");
            et_telefono.setText("");
            et_user.setText("");
            et_pass.setText("");
            et_col.setText("");
            et_calle.setText("");
            et_mun.setText("");
        }

    }
    @Override
    public void onClick(View v) {
        String nom=et_nombre.getText().toString();
        String tel=et_telefono.getText().toString();
        String user = et_user.getText().toString();
        String pass = et_pass.getText().toString();
        String col=et_col.getText().toString();
        String ca=et_calle.getText().toString();
        String mun=et_mun.getText().toString();
        if(TextUtils.isEmpty(mun)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(ca)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(col)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(user)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(tel)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(nom)){
            Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        if(v.getId()==R.id.bt_registrar){
            new async2().execute();
            }
    }
}
