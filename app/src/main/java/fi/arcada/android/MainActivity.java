package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView myText;
    TextView textSetting;

    //1. Deklarera ett SharedPreferences-objekt
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = findViewById(R.id.editTextView);
        textSetting = findViewById(R.id.textSetting);

        //2. Instansiera prefs
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //3. Uppdatera värdet i myText-fältet med värdet från preference-variabeln myOtherText
        myText.setText(
                //Om variabeln myOtherText inte finns i Shared Preferences används default värdet ("Hello") i stället
                prefs.getString("myOtherText", "Hello")
        );

        textSetting.setText(
                prefs.getString("textSetting", "def val")
        );

    }
    public void buttonClick(View view){

        if (view.getId() == R.id.buttonSave){
            //4. Initsiera ett Editor-objekt för att kunna ändra på preferences
            SharedPreferences.Editor prefsEditor = prefs.edit();

            //5. spara värdet från myText-fältet som "myOtherText" i pref
            prefsEditor.putString("myOtherText", myText.getText().toString());

            //6. Spara alla ändringar i minnet och i xml-filen som innehåller SharedPreferences
            prefsEditor.apply();
        } else if (view.getId() == R.id.btnOtherActivity){

            Intent intent = new Intent(this, OtherActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnSettings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }



    }
}