package com.example.artur.fonda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Menu1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button bt_ordenar,bt_inventario;
    TextView tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bt_ordenar=(Button)(findViewById(R.id.bt_ordenar));
        bt_ordenar.setOnClickListener(this);
        bt_inventario=(Button)(findViewById(R.id.bt_inventario));
        bt_inventario.setOnClickListener(this);
        tv_user=(TextView)findViewById(R.id.et_usuario);
        String nombreUsuario=getIntent().getStringExtra("usuario");
        tv_user.setText(nombreUsuario);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admon) {
            Intent i = new Intent(getApplicationContext(),MenuAdministrar.class);
            i.putExtra("usuario",tv_user.getText().toString());
            startActivity(i);
            //this.finish();
        } else if (id == R.id.nav_salir) {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            //i.putExtra("usuario",tv_user.getText().toString());
            startActivity(i);
            this.finish();
        } else if (id == R.id.nav_contactos) {
            Intent i = new Intent(getApplicationContext(),Contacto.class);
            //i.putExtra("usuario",tv_user.getText().toString());
            startActivity(i);
        } else if (id == R.id.nav_acerca) {
            Intent i = new Intent(getApplicationContext(),Acerca.class);
            //i.putExtra("usuario",tv_user.getText().toString());
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_ordenar){
            Intent i = new Intent(getApplicationContext(),Ordenar.class);
            i.putExtra("usuario",tv_user.getText().toString());
            startActivity(i);
        }
        if (v.getId()==R.id.bt_inventario){
            Intent i = new Intent(getApplicationContext(),Inventario.class);
            startActivity(i);
        }
    }
}
