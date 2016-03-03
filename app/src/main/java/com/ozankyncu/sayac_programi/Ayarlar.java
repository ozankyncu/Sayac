package com.ozankyncu.sayac_programi;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by ozankoyuncu on 3.3.2016.
 */
public class Ayarlar extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ayarlar);

    }
}
