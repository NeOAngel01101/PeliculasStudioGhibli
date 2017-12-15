package com.neo.org.peliculasstudioghibli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neo.org.peliculasstudioghibli.model.Film;


import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView mListViewFilms;
    ArrayList<Film> mListaFilms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewFilms = findViewById(R.id.list_view_films);

        final ArrayAdapter<Film> adapter = new ArrayAdapter<Film>(
                this,
                R.layout.list_item_film,
                R.id.text_view_film,
                mListaFilms
        );

        mListViewFilms.setAdapter(adapter);

        mListViewFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(
                        MainActivity.this,
                        DetailActivity.class);
                detailIntent.putExtra("film", mListaFilms.get(position));
                startActivity(detailIntent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://ghibliapi.herokuapp.com/films",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("MainActivity", "Algo esta fallando!!!");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("MainActivity", "OK->Response:"+ responseString);
                        Gson gson = new GsonBuilder().create();
                        Film[] films = gson.fromJson(responseString, Film[].class);
                        adapter.clear();
                        for (Film film: films){
                            adapter.add(film);
                        }
                    }
                });
    }

}

