package com.example.artur.fonda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Contacto extends AppCompatActivity implements View.OnClickListener {
Button bt_regresar;
ImageButton img_face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        bt_regresar=(Button)findViewById(R.id.bt_regresar);
        bt_regresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_regresar){
            Intent i = new Intent(getApplicationContext(),Menu1.class);
            startActivity(i);
            this.finish();
        }
    }
}
