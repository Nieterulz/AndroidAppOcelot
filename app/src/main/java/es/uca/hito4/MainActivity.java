package es.uca.hito4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import es.uca.hito4.asistentes.AsistentesFragment;
import es.uca.hito4.fechas.FechasFragment;
import es.uca.hito4.localizacion.LocalizacionFragment;
import es.uca.hito4.programa.ProgramaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Asistentes");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.setDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_asistentes);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedor, new AsistentesFragment())
                .commit();

        try {
            if (getIntent().getExtras().containsKey("localizacion")) {
                navigationView.setCheckedItem(R.id.nav_localizacion);
                toolbar.setTitle("Localización");
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new LocalizacionFragment())
                        .commit();
            } else if(getIntent().getExtras().containsKey("programa")) {
                navigationView.setCheckedItem(R.id.nav_programa);
                toolbar.setTitle("Programa");
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new ProgramaFragment())
                        .commit();
            }
        }catch(Exception e){}

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(id == R.id.nav_asistentes){
            toolbar.setTitle("Asistentes");
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new AsistentesFragment())
                    .commit();
        }else if(id == R.id.nav_programa){
            toolbar.setTitle("Programa");
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new ProgramaFragment())
                    .commit();
        }else if(id == R.id.nav_fechas){
            toolbar.setTitle("Fechas");
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new FechasFragment())
                    .commit();
        }else if(id == R.id.nav_localizacion){
            toolbar.setTitle("Localizacion");
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new LocalizacionFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
