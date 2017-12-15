package com.neo.org.peliculasstudioghibli;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.neo.org.peliculasstudioghibli.model.Film;


public class DetailActivity extends AppCompatActivity {

    TextView mTextViewTitle;
    TextView mTextViewDescription;
    TextView mTextViewDirector;
    TextView mTextViewProducer;
    TextView mTextViewScore;
    TextView mTextViewDate;
    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Film film = new Film();
        mTextViewTitle = findViewById(R.id.film_title_text_view);
        mTextViewDescription = findViewById(R.id.film_description_text_view);
        mTextViewDirector = findViewById(R.id.film_director_text_view);
        mTextViewProducer = findViewById(R.id.film_producer_text_view);
        mTextViewScore = findViewById(R.id.film_score_text_view);
        mTextViewDate = findViewById(R.id.film_date_text_view);

        Intent detailIntent = DetailActivity.this.getIntent();

        if (detailIntent.hasExtra("film")) {
            film = (Film) detailIntent.getSerializableExtra("film");
        }


        mTextViewTitle.setText(film.getTitle());
        mTextViewDescription.setText(film.getDescription());
        mTextViewDirector.setText(film.getDirector());
        mTextViewProducer.setText(film.getProducer());
        mTextViewScore.setText(film.getRt_score());
        mTextViewDate.setText(film.getRelease_date());
    }
}