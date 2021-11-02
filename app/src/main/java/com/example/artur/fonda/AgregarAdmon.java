package com.example.artur.fonda;

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

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AgregarAdmon extends AppCompatActivity implements View.OnClickListener{
        EditText et_nombre,et_app,et_apm,et_calle,et_col,et_mun,et_pues,et_cel,et_user,et_pass;
        Button bt_registrar,bt_buscar;
        ProgressBar progressBar2;
        TextView result;
        ArrayList lista=new ArrayList();
    Connection conn;
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregaradmon);
        et_app=(EditText)findViewById(R.id.et_app);
        et_apm=(EditText)findViewById(R.id.et_apm);
        et_pues=(EditText)findViewById(R.id.et_pues);
        et_user = (EditText) findViewById(R.id.et_user);
        et_pass = (EditText) (findViewById(R.id.et_pass));
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_cel = (EditText) (findViewById(R.id.et_cel));
        et_col = (EditText) findViewById(R.id.et_col);
        et_calle = (EditText) (findViewById(R.id.et_calle));
        et_mun = (EditText) findViewById(R.id.et_mun);
        bt_registrar = (Button) (findViewById(R.id.bt_registrar));
        bt_registrar.setOnClickListener(this);
            bt_buscar = (Button) (findViewById(R.id.bt_consultar));
            bt_buscar.setOnClickListener(this);
        progressBar2 = findViewById(R.id.progressbar2);
        result = (TextView) (findViewById(R.id.result));
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
                String app=et_app.getText().toString();
                String apm=et_apm.getText().toString();
                String ca=et_calle.getText().toString();
                String col=et_col.getText().toString();
                String mun=et_mun.getText().toString();
                String pues=et_pues.getText().toString();
                String tel=et_cel.getText().toString();
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                Thread.sleep(3000);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="insert into Empleado values(null,'"+nom+"','"+app+"','"+apm+"','"+ca+"','"+col+"','"+mun+"','"+pues+"','"+tel+"','"+user+"','"+pass+"');";
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
            Toast.makeText(AgregarAdmon.this,"Registro realizado",Toast.LENGTH_LONG).show();
            et_nombre.setText("");
            et_app.setText("");
            et_apm.setText("");
            et_calle.setText("");
            et_col.setText("");
            et_mun.setText("");
            et_pues.setText("");
            et_cel.setText("");
            et_user.setText("");
            et_pass.setText("");
        }

    }
    class consultar extends AsyncTask<String,Void, ResultSet> {
        @Override
        protected void onPreExecute() {
            progressBar2.setVisibility(View.VISIBLE);
        }


        @Override
        protected ResultSet doInBackground(String... strings) {
            //String v=strings[0];
            try{
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                Thread.sleep(3000);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="Select * from Empleado where nombreuser='" + user + "'and password='" + pass + "'";
                //estado.executeQuery(peticion);
                //estado.executeUpdate(peticion);
                ResultSet result=estado.executeQuery(peticion);
                //result.getString(0);
                /*r1=result.getString(1);
                r2=result.getString(2);
                r3=result.getString(3);
                r4=result.getString(4);
                r5=result.getString(5);
                r6=result.getString(6);
                r7=result.getString(7);
                r8=result.getString(8);*/
                //String nombre= result.getString("nombre");
                //result.next();
                //Toast.makeText(Agregar.this,"Registro realizado",Toast.LENGTH_LONG).show();
                // result.setText("Registro realizado");
                return result;
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
        protected void onPostExecute(ResultSet result) {
            progressBar2.setVisibility(View.INVISIBLE);
            progressBar2.setVisibility(View.GONE);
            try{
                if(conn !=null){
                    if(!result.next()){
                        System.out.println("Tampoco funciono prro x1");
                    }else{
                        result.getString(1);
                        et_nombre.setText(result.getString(2));
                        et_app.setText(result.getString(3));
                        et_apm.setText(result.getString(4));
                        et_calle.setText(result.getString(5));
                        et_col.setText(result.getString(6));
                        et_mun.setText(result.getString(7));
                        et_pues.setText(result.getString(8));
                        et_cel.setText(result.getString(9));
                        Toast.makeText(AgregarAdmon.this,"Consulta realizada",Toast.LENGTH_LONG).show();
                    }

                }else{
                    System.out.println("Tampoco funciono prro x2");
                }
            }catch (SQLException e){
                System.out.println(e);
            }


        }

    }
        @Override
        public void onClick(View v) {
            String nom=et_nombre.getText().toString();
            String app=et_app.getText().toString();
            String apm=et_apm.getText().toString();
            String ca=et_calle.getText().toString();
            String col=et_col.getText().toString();
            String mun=et_mun.getText().toString();
            String pues=et_pues.getText().toString();
            String tel=et_cel.getText().toString();
            String user = et_user.getText().toString();
            String pass = et_pass.getText().toString();
            if(TextUtils.isEmpty(pass)){
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
            if(TextUtils.isEmpty(pues)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(mun)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(col)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(nom)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(app)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(apm)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(ca)){
                Toast.makeText(this,"Le falta ingresar un campo",Toast.LENGTH_LONG).show();
                return;
            }
            if(v.getId()==R.id.bt_registrar){
                new async2().execute();
            }
            if(v.getId()==R.id.bt_consultar){
                new consultar().execute();
            }
        }
    }
