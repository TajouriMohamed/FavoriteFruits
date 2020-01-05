package com.med.favoritefruits;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {
    private static final int DETAILS_RESULT = 200;
    private String title;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findViews();
    }

    @SuppressLint("NewApi")
    private void findViews() {
        TextView fruit_details = findViewById(R.id.fruit_details);
        final TextView fruit_title = findViewById(R.id.fruit_title);
        ImageView fruit_image = findViewById(R.id.fruit_image);
        ratingBar = findViewById(R.id.fruit_ratingBar);
        Button validateRating = findViewById(R.id.fruit_validateRating);

        Bundle oBundle = this.getIntent().getExtras();
        if (oBundle != null && oBundle.containsKey("title")) {
            title = oBundle.getString("title");

            //Titres des fruits
            final String[] titles = new String[]{
                    getResources().getString(R.string.apple),
                    getResources().getString(R.string.banane),
                    getResources().getString(R.string.grape),
                    getResources().getString(R.string.lemon),
                    getResources().getString(R.string.strawberry)};
            //Description des fruits
            String[] long_description = new String[]{
                    getResources().getString(R.string.apple_long_description),
                    getResources().getString(R.string.banana_long_description),
                    getResources().getString(R.string.grape_long_description),
                    getResources().getString(R.string.lemon_long_description),
                    getResources().getString(R.string.strawberry_long_description)};
            if (Objects.equals(title, titles[0])) {
                fruit_title.setText(title);
                fruit_details.setText(long_description[0]);
                fruit_image.setImageResource(R.mipmap.pomme);
            } else if (Objects.equals(title, titles[1])){
                fruit_title.setText(title);
                fruit_details.setText(long_description[1]);
                fruit_image.setImageResource(R.mipmap.banane);
            } else if (Objects.equals(title, titles[2])){
                fruit_title.setText(title);
                fruit_details.setText(long_description[2]);
                fruit_image.setImageResource(R.mipmap.raisin);
            } else if (Objects.equals(title, titles[3])){
                fruit_title.setText(title);
                fruit_details.setText(long_description[3]);
                fruit_image.setImageResource(R.mipmap.citron);
            } else if (Objects.equals(title, titles[4])){
                fruit_title.setText(title);
                fruit_details.setText(long_description[4]);
                fruit_image.setImageResource(R.mipmap.fraise);
            } else {
                Toast.makeText(this,"Erreur !",Toast.LENGTH_SHORT).show();
            }
            mPreferences = getSharedPreferences(getString(R.string.prefs), Context.MODE_PRIVATE);

            String rate = mPreferences.getString(title,"");
            checkSharedPreferences(title,rate);
            validateRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    configureSharedPreferences(title,String.valueOf(ratingBar.getRating()));
                    //Retour vers la MainActivity en détruisant
                    //l'activité courant
                    setResult(DETAILS_RESULT);
                    finish();
                }
            });
        }
    }

    private void configureSharedPreferences(String title, String ratingValue) {
        mPreferences = getSharedPreferences(getString(R.string.prefs),Context.MODE_PRIVATE);
        mEditor=mPreferences.edit();
        mEditor.putString(title,ratingValue);
        mEditor.apply();
    }
    private void checkSharedPreferences(String title, String ratingValue) {
        if (!ratingValue.equals("")){
            ratingBar.setRating(Float.parseFloat(ratingValue));
        }
    }
}
