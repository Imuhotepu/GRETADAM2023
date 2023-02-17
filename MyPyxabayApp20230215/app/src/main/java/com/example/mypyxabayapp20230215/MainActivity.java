package com.example.mypyxabayapp20230215;

import static com.example.mypyxabayapp20230215.Constantes.EXTRA_CREATOR;
import static com.example.mypyxabayapp20230215.Constantes.EXTRA_LIKES;
import static com.example.mypyxabayapp20230215.Constantes.EXTRA_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaperRecycler.OnItemClickListener {
    /**
     * VARS global
     **/
    private RecyclerView recyclerView;
    private AdaperRecycler adaperRecycler;
    private ArrayList<ModelItem> itemArrayList;

    private RequestQueue requestQueue;
    private String search;

    public void init() {
        //Lien design Java
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
    }

    private void parseJson() {
        search = "moto";
        String pixaKey = "18505218-2c9fcd868cf27b6335884f9e0";
        //http
        String urlJSONFile = "https://pixabay.com/api/"
                + "?key=" + pixaKey
                +"&q=" + search
                + "&image_type=photo"
                + "&orientation=horizontal"
                + "&pretty=true";

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlJSONFile, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // On récupère le tableau de données JSON à partir de notre objet JsonObjectRequest
                        // dans un try and catch ajouter en auto en corrigeant l'erreur
                        JSONArray jsonArray = response.getJSONArray("hits");
                        // On récupère dans un premier temps toutes les données présentent dans le Array avec une boucle for
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);
                            // Puis on sélectionne celles dn on à besoin soit user - likes - webformatURL
                            String creator = hit.getString("user");
                            int likes = hit.getInt("likes");
                            String imageUrl = hit.getString("webformatURL");
                            // On ajoute les données à notre tableau en utilisant son model
                            itemArrayList.add(new ModelItem(imageUrl, creator, likes));
                        }


                        // En dehors du try on adapte le tableau de données
                        adaperRecycler = new AdaperRecycler(MainActivity.this, itemArrayList); // Noter MainActivity.this car nous sommes dans une classe interne
                        // Puis on lie l'adpter au Recycler
                        recyclerView.setAdapter(adaperRecycler);

                        /** #10.3 On peut alors ajouter le listener **/

                        adaperRecycler.setMyOnClickListener(MainActivity.this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
/** #9.1 On rempli la request avec les données récupérées **/
requestQueue.add(request);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        parseJson();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"t'as cliqué a la pos"+position, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onItemClick" + position);

      Intent intent = new Intent(MainActivity.this, DetailActivity.class);
      ModelItem clickItem = itemArrayList.get(position);
      intent.putExtra(EXTRA_URL, clickItem.getImageURL());
      intent.putExtra(EXTRA_CREATOR, clickItem.getCreator());
      intent.putExtra(EXTRA_LIKES, clickItem.getLikes());
      startActivity(intent);
    }

}