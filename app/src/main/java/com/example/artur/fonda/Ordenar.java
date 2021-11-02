package com.example.artur.fonda;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.security.AccessController.getContext;

public class Ordenar extends AppCompatActivity implements View.OnClickListener{
    Button bt_ord,bt_busc,fecha;
    CheckBox c_1,c_2,c_3,c_4,c_5;
    Spinner spin1;
    EditText et_platillo,et_fecha,et_i1,et_i2,et_i3,et_i4,et_i5;
    //String[] menu = new String[15];
    Connection conn;
    //List menu=new ArrayList();
    //ArrayList<String> list;
    List<String> menu=new ArrayList();
    RadioButton r1,r2,r3,r4,r5;
    private  int dia,mes,ano;
    //String con,con2,con3,con4,con5;
    int i=1;
    TextView tv_user;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordenar);
        c_1=(CheckBox)findViewById(R.id.c_1);
        c_1.setOnClickListener(this);
        c_2=(CheckBox)findViewById(R.id.c_2);
        c_2.setOnClickListener(this);
        c_3=(CheckBox)findViewById(R.id.c_3);
        c_3.setOnClickListener(this);
        c_4=(CheckBox)findViewById(R.id.c_4);
        c_4.setOnClickListener(this);
        c_5=(CheckBox)findViewById(R.id.c_5);
        c_5.setOnClickListener(this);
        r1=(RadioButton)findViewById(R.id.r1);
        r1.setOnClickListener(this);
        r2=(RadioButton)findViewById(R.id.r2);
        r2.setOnClickListener(this);
        r3=(RadioButton)findViewById(R.id.r3);
        r3.setOnClickListener(this);
        r4=(RadioButton)findViewById(R.id.r4);
        r4.setOnClickListener(this);
        r5=(RadioButton)findViewById(R.id.r5);
        r5.setOnClickListener(this);
        et_platillo=(EditText) findViewById(R.id.et_platillo);
        et_fecha=(EditText)findViewById(R.id.et_fecha);
        bt_ord=(Button)findViewById(R.id.bt_ord);
        bt_ord.setOnClickListener(this);
        bt_busc=(Button)findViewById(R.id.bt_busc);
        bt_busc.setOnClickListener(this);
        fecha=(Button)findViewById(R.id.fecha);
        fecha.setOnClickListener(this);
        spin1 = (Spinner) findViewById(R.id.spin_1);
        et_i1=(EditText) findViewById(R.id.et_i1);
        et_i2=(EditText) findViewById(R.id.et_i2);
        et_i3=(EditText) findViewById(R.id.et_i3);
        et_i4=(EditText) findViewById(R.id.et_i4);
        et_i5=(EditText) findViewById(R.id.et_i5);
        tv_user=(TextView) findViewById(R.id.tv_user);
        String nombreUsuario=getIntent().getStringExtra("usuario");
        tv_user.setText(nombreUsuario);
        //spin1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,menu));
        //ArrayAdapter<String> spiner=new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_spinner_item,menu);
        //spiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, menu);
        spin1.setAdapter(adapter);
    }
    class registrar extends AsyncTask<String,Void, String> {
        @Override
        protected void onPreExecute() {

        }


        @Override
        protected String doInBackground(String... strings) {
            try{
                String user=tv_user.getText().toString();
                String fecha=et_fecha.getText().toString();
                String platillo=et_platillo.getText().toString();
                String i1=et_i1.getText().toString();
                String i2=et_i2.getText().toString();
                String i3=et_i3.getText().toString();
                String i4=et_i4.getText().toString();
                String i5=et_i5.getText().toString();
                Thread.sleep(30);
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="insert into Pedido values(null,'"+fecha+"','"+platillo+"','"+i1+"','"+i2+"','"+i3+"','"+i4+"','"+i5+"','"+user+"',null,null,null);";
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
            Toast.makeText(Ordenar.this,"Registro realizado",Toast.LENGTH_LONG).show();
            et_platillo.setText("");
            et_i1.setText("");
            et_i2.setText("");
            et_i3.setText("");
            et_i4.setText("");
            et_i5.setText("");
        }

    }
    class consultar1 extends AsyncTask<String,Void, ResultSet> {
        @Override
        protected void onPreExecute() {
            //progressBar2.setVisibility(View.VISIBLE);
        }

        @Override
        protected ResultSet doInBackground(String... strings) {
            //String v=strings[0];
            try{
                String plat = et_platillo.getText().toString();
                Thread.sleep(10);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="Select * from Ingredientes where nombreplat='" + plat + "'";
                //estado.executeQuery(peticion);
                //estado.executeUpdate(peticion);
                ResultSet result=estado.executeQuery(peticion);
                //result.getString(0);
                /*while ( result.next()) {
                    int i=1;
                    //numTrab++;
// numTrabajadores.append( Resul.getObject( i )).toString();
// numTrabajadores.append( " " );
                    //menu[i]=result.getObject(i).toString();
                    i++;
                    menu.add(result.getObject(i).toString());
                }*/
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
            /*progressBar2.setVisibility(View.INVISIBLE);
            progressBar2.setVisibility(View.GONE);*/
            try{
                if(conn !=null){
                    if(!result.next()){
                        System.out.println("Tampoco funciono");
                    }else{
                        c_1.setText(result.getString(3));
                        c_2.setText(result.getString(4));
                        c_3.setText(result.getString(5));
                        c_4.setText(result.getString(6));
                        c_5.setText(result.getString(7));
                        Toast.makeText(Ordenar.this,"Consulta realizada",Toast.LENGTH_LONG).show();
                    }

                }else{
                    System.out.println("Tampoco funciono");
                }
            }catch (SQLException e){
                System.out.println(e);
            }


        }

    }
    @Override
    public void onClick(View v) {
        if (r1.isChecked()){
            et_platillo.setText(r1.getText().toString());
        }
        if (r2.isChecked()){
            et_platillo.setText(r2.getText().toString());
        }
        if (r3.isChecked()){
            et_platillo.setText(r3.getText().toString());
        }
        if (r4.isChecked()){
            et_platillo.setText(r4.getText().toString());
        }
        if (r5.isChecked()){
            et_platillo.setText(r5.getText().toString());
        }
        if(c_1.isChecked()){
            et_i1.setText(c_1.getText().toString());
        }
        if(c_2.isChecked()){
            et_i2.setText(c_2.getText().toString());
        }
        if(c_3.isChecked()){
            et_i3.setText(c_3.getText().toString());
        }
        if(c_4.isChecked()){
            et_i4.setText(c_4.getText().toString());
        }
        if(c_5.isChecked()){
            et_i5.setText(c_5.getText().toString());
        }
        //new consultar().execute();
        if (v.getId()==R.id.bt_busc){
            new consultar1().execute();

            //String plat=et_platillo.getText().toString();
        }
        if (v.getId()==R.id.fecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    //et_fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    et_fecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v.getId()==R.id.bt_ord){
            new registrar().execute();
        }
    }
}