package cl.inacap.simpsonapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.simpsonapiapp.adapter.PersonajeAdapter;
import cl.inacap.simpsonapiapp.dao.Personaje;

public class MainActivity extends AppCompatActivity {

    private List<Personaje> personajes = new ArrayList<>();
    private Toolbar toolbar;
    private ListView listView;
    private PersonajeAdapter personajeAdapter;
    private RequestQueue requestQueue;
    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.listView = findViewById(R.id.list_Personajes);
        this.listView.setAdapter(this.personajeAdapter);
        this.spinner = findViewById(R.id.spinnerSp);
        this.button = findViewById(R.id.buscarBtn);
        this.personajeAdapter = new PersonajeAdapter(MainActivity.this, R.layout.list_personajes, this.personajes);

        Integer [] cantidadConsejo = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, cantidadConsejo);
        spinner.setAdapter(adapter);
        this.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int cantidadFrase = (int) spinner.getSelectedItem();
                requestQueue = Volley.newRequestQueue(MainActivity.this);
                if (cantidadFrase != 0) {
                    personajes.clear();
                    JsonArrayRequest JARequest = new JsonArrayRequest("https://thesimpsonsquoteapi.glitch.me/quotes?count=" + cantidadFrase, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for (int i = 0; i < cantidadFrase; i++) {
                                  //Personaje____________________________
                                    Personaje personaje = new Personaje();
                                    personaje.setImage(response.getJSONObject(i).get("image").toString());
                                    personaje.setCharacter(response.getJSONObject(i).get("character").toString());
                                    personaje.setQuote(response.getJSONObject(i).get("quote").toString());
                                    personajes.add(personaje);
                                }
                            } catch (Exception ex) {
                                System.out.println(ex);
                                personajes.clear();
                            }
                        }
                    },error -> personajes.clear());
                    requestQueue.add(JARequest);
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
    }

}