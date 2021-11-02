package com.example.artur.fonda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener  {
    Button bt_ordenar,bt_inventario,bt_cuentas,bt_historial;
    TextView tv_user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        bt_ordenar=(Button)(findViewById(R.id.bt_ordenar));
        bt_ordenar.setOnClickListener(this);
        bt_inventario=(Button)(findViewById(R.id.bt_inventario));
        bt_inventario.setOnClickListener(this);

        tv_user=(TextView)(findViewById(R.id.tv_user));
        String nombreusuario=getIntent().getStringExtra("usuario");
        tv_user.setText(" "+nombreusuario);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_ordenar){
            Intent i = new Intent(getApplicationContext(),Ordenar.class);
            startActivity(i);
        }
        if (v.getId()==R.id.bt_inventario){
            Intent i = new Intent(getApplicationContext(),Inventario.class);
            startActivity(i);
        }

    }
}
