package com.med.favoritefruits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final int DETAILS_REQUEST = 100;
    private static final int DETAILS_RESULT = 200;
    private static final int RATING_RESULT = 300;
    protected ListView customList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }
    private void findViews(){
        customList=findViewById(R.id.custom_list);
        ArrayList<HashMap<String,String>> listItems = new ArrayList<>();
        HashMap<String,String> item;
        //Titres des fruits
        String[] titles = new String[]{
                getResources().getString(R.string.apple),
                getResources().getString(R.string.banane),
                getResources().getString(R.string.grape),
                getResources().getString(R.string.lemon),
                getResources().getString(R.string.strawberry)};
        //Description des fruits
        String[] descriptions = new String[]{
                getResources().getString(R.string.apple_description),
                getResources().getString(R.string.banane_description),
                getResources().getString(R.string.grape_description),
                getResources().getString(R.string.lemon_description),
                getResources().getString(R.string.strawberry_description)};
        // icone (images) des fruits
        String[] icons = new String[]{
                String.valueOf(R.mipmap.pomme),
                String.valueOf(R.mipmap.banane),
                String.valueOf(R.mipmap.raisin),
                String.valueOf(R.mipmap.citron),
                String.valueOf(R.mipmap.fraise),
        };
        //creation des items de la liste
        for (int i=0; i<=4; i++)
        {
            item = new HashMap<>();
            item.put("title", titles[i]);
            item.put("description", descriptions[i]);
            item.put("icon", icons[i]);
            listItems.add(item);
        }
        //creation de l'Adapter
        SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(),
                listItems,
                R.layout.activity_list_item,
                new String[]{"title", "description", "icon"},
                new int[]{R.id.title, R.id.description, R.id.icon});
        //Association de l'adapter à la liste
        customList.setAdapter(adapter);
        //Interraction avec les items de la liste
        customList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> item = (HashMap<String, String>)
                        customList.getItemAtPosition(position);
                //Début Partie 1 à compléter
                Intent details_intent=new Intent(MainActivity.this,DetailsActivity.class);
                Bundle oBundle = new Bundle();
                oBundle.putString("title",item.get("title"));
                details_intent.putExtras(oBundle);
                startActivityForResult(details_intent,DETAILS_REQUEST);
            }
        });
        customList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Debut Partie 2 à compléter
                Intent rating_intent=new Intent(MainActivity.this,RatingActivity.class);
                startActivityForResult(rating_intent,DETAILS_REQUEST);
                return true;
                //Fin Partie 2 à compléter
            }
        });
    }
    //Début Partie 3 à compléter
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent){
        if (requestCode==DETAILS_REQUEST){
            if (resultCode==DETAILS_RESULT){
                Toast.makeText(this,
                        getString(R.string.details_activity_result),Toast.LENGTH_SHORT).show();
            }
            else if (resultCode==RATING_RESULT){
                Toast.makeText(this,
                        getString(R.string.rating_activity_result),Toast.LENGTH_SHORT).show();
            }
        }
    }
    //Fin Partie 3 à compléter
}
