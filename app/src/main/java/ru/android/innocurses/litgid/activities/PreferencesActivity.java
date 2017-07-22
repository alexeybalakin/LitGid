package ru.android.innocurses.litgid.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.util.Locale;

import ru.android.innocurses.litgid.R;

/**
 * Created by admin on 06.07.2017.
 */

public class PreferencesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        ListPreference listPreference = (ListPreference) findPreference("language_preference");
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferencesActivity.this);
                preferences.edit().putString("language", newValue.toString()).commit();
                if(preferences.getString("language", null) != null)
                    switch (preferences.getString("language", null)) {

                        case "English":
                            Locale localeEn = new Locale("en", "US");
                            Configuration configEn = new Configuration();
                            configEn.locale = localeEn;
                            getApplicationContext().getResources().updateConfiguration(configEn, null);
                            restartActivity();
                            break;
                        case "Russian":

                            Locale localeRu = new Locale("ru", "RU");
                            Configuration configRu = new Configuration();
                            configRu.locale = localeRu;
                            getApplicationContext().getResources().updateConfiguration(configRu, null);
                            restartActivity();
                            break;
                    }
                return true;
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("save_login")){
            Preference preference = findPreference("save_password");
            preference.setDefaultValue(false);
            sharedPreferences.edit().putBoolean("save_password", false).commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    private void restartActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.restartApplication);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent j = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(j);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}