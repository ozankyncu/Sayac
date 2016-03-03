package com.ozankyncu.sayac_programi;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    int count;
    SharedPreferences preferences;

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

        btn=(Button)findViewById(R.id.button_sayac);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings)
        {
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
}
