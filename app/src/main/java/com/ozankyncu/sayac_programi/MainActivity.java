package com.ozankyncu.sayac_programi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    Button btn;
    int count;
    RelativeLayout arkaplan;
    Boolean ses_durumu,titresim_durumu;
    SharedPreferences preferences,ayarlar;

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("sayac",count);

        editor.commit();
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("SAYAC");
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.buton_rengi));
        arkaplan=(RelativeLayout)findViewById(R.id.rllayout);
        btn=(Button)findViewById(R.id.button_sayac);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        /*ayarları yukle*/
        ayarlar= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ayarlariYukle();

        count=preferences.getInt("sayac",0);
        btn.setText(count+"");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                btn.setText(""+count);
            }
        });

    }

    private void ayarlariYukle() {
        String position=ayarlar.getString("arkaplan","3");//default deger
        switch (Integer.valueOf(position))
        {
            case 0:
                arkaplan.setBackgroundColor(Color.RED);
                break;
            case 1:
                arkaplan.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                arkaplan.setBackgroundColor(Color.BLUE);
                break;
            case 3:
                arkaplan.setBackgroundColor(Color.DKGRAY);
                break;
            case 4:
                arkaplan.setBackgroundColor(getResources().getColor(R.color.arkaplan));
                break;

        }
        ses_durumu=ayarlar.getBoolean("ses",false);
        titresim_durumu=ayarlar.getBoolean("titresim",false);
        ayarlar.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings)
        {
            Intent ıntent=new Intent(getApplicationContext(),Ayarlar.class);
            startActivity(ıntent);
            return true;
        }
        else if(item.getItemId()==R.id.sifirla)
        {
            count=0;
            btn.setText(""+count);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            ayarlariYukle();
    }
}
