package com.example.artur.fonda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuAdministrar extends AppCompatActivity implements View.OnClickListener {
Button bt_cambiar;
TextView et_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_administrar);
        bt_cambiar= findViewById(R.id.bt_cambiar);
        bt_cambiar.setOnClickListener(this);
        et_user=(TextView)findViewById(R.id.et_user);
        String nombreUsuario=getIntent().getStringExtra("usuario");
        et_user.setText(nombreUsuario);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),CambiarPassC.class);
        i.putExtra("usuario",et_user.getText().toString());
        startActivity(i);
        //this.finish();
    }
}
