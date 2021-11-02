package com.example.artur.fonda;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Pedido extends AppCompatActivity implements View.OnClickListener {
TextView fecha,et_nombre,et_i1,et_i2,et_i3,et_i4,et_i5,et_user;
Button bfecha,consultar;
private  int dia,mes,ano;
    Connection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        fecha=(TextView) findViewById(R.id.et_fecha);
        et_nombre=(TextView) findViewById(R.id.et_nombre);
        et_i1=(TextView) findViewById(R.id.et_i1);
        et_i2=(TextView) findViewById(R.id.et_i2);
        et_i3=(TextView) findViewById(R.id.et_i3);
        et_i4=(TextView) findViewById(R.id.et_i4);
        et_i5=(TextView) findViewById(R.id.et_i5);
        et_user=(TextView) findViewById(R.id.et_user);
        bfecha=(Button)findViewById(R.id.bfecha);
        bfecha.setOnClickListener(this);
        consultar=(Button)findViewById(R.id.bt_consultar);
        consultar.setOnClickListener(this);
    }
    class consultar extends AsyncTask<String,Void, ResultSet> {
        @Override
        protected void onPreExecute() {

        }


        @Override
        protected ResultSet doInBackground(String... strings) {
            //String v=strings[0];
            try{
                String f = fecha.getText().toString();
                Thread.sleep(30);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://185.224.138.147:3306/u530930615_fonda","u530930615_artur","hn159728");
                Statement estado=conn.createStatement();
                System.out.println("Conexión establecida");
                String peticion="Select * from Pedido where fecha='" + f + "'";
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
            try{
                if(conn !=null){
                    if(!result.next()){
                        System.out.println("Tampoco funciono prro x1");
                    }else{
                        result.getString(1);
                        et_nombre.setText(result.getString(3));
                        et_i1.setText(result.getString(4));
                        et_i2.setText(result.getString(5));
                        et_i3.setText(result.getString(6));
                        et_i4.setText(result.getString(7));
                        et_i5.setText(result.getString(8));
                        et_user.setText(result.getString(9));
                        Toast.makeText(Pedido.this,"Consulta realizada",Toast.LENGTH_LONG).show();
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
        if (v.getId()==R.id.bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DATE);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    //et_fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    fecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v.getId()==R.id.bt_consultar){
            new consultar().execute();
        }
    }
}
