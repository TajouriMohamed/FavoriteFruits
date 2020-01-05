package com.med.favoritefruits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class RatingActivity extends AppCompatActivity {
    private static final int RATING_RESULT = 300;
    private RatingBar appleRatingBar,
            bananaRatingBar,
            grapeRatingBar,
            lemonRatingBar,
            strawberryRatingBar;
    private Button revalidateRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        findViews();
    }

    private void findViews() {
        appleRatingBar=findViewById(R.id.apple_ratingBar);
        bananaRatingBar=findViewById(R.id.banana_ratingBar);
        grapeRatingBar=findViewById(R.id.grape_ratingBar);
        lemonRatingBar=findViewById(R.id.lemon_ratingBar);
        strawberryRatingBar=findViewById(R.id.strawberry_ratingBar);
        revalidateRating=findViewById(R.id.fruit_revalidateRating);
        //Récupération des préférences
        gonfigureSharedPreferences();
    }

    private void gonfigureSharedPreferences() {
        SharedPreferences mPreferences = getSharedPreferences(getString(R.string.prefs), Context.MODE_PRIVATE);
        String appleRating =
                mPreferences.getString(getString(R.string.apple),null);
        String bananaRating =
                mPreferences.getString(getString(R.string.banane),null);
        String grapeRating =
                mPreferences.getString(getString(R.string.grape),null);
        String lemonRating =
                mPreferences.getString(getString(R.string.lemon),null);
        String strawberryRating =
                mPreferences.getString(getString(R.string.strawberry),null);
        //Affichage des préférences
        if (appleRating != null){
            appleRatingBar.setRating(Float.parseFloat(appleRating));
        }
        if (bananaRating != null){
            bananaRatingBar.setRating(Float.parseFloat(bananaRating));
        }
        if (grapeRating != null){
            grapeRatingBar.setRating(Float.parseFloat(grapeRating));
        }
        if (lemonRating != null){
            lemonRatingBar.setRating(Float.parseFloat(lemonRating));
        }
        if (strawberryRating != null){
            strawberryRatingBar.setRating(Float.parseFloat(strawberryRating));
        }
        //Click sur le bouton revalider
        revalidateRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On se limite à revenir vers la MainActivity pour
                //mettre à jour les préférences
                setResult(RATING_RESULT);
                //Détruire l'activité courante et revenir
                //à l'activité principale
                finish();
            }
        });
    }
}
