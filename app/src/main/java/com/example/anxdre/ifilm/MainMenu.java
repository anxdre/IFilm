package com.example.anxdre.ifilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.anxdre.ifilm.adapter.FavoriteAdapter;
import com.example.anxdre.ifilm.adapter.MovieAdapter;
import com.example.anxdre.ifilm.adapter.ViewPagerAdapter;
import com.example.anxdre.ifilm.data.model.Favorite;
import com.example.anxdre.ifilm.data.model.Movie;
import com.example.anxdre.ifilm.fragment.FragmentMovieFavorite;
import com.example.anxdre.ifilm.fragment.FragmentMovieNow;
import com.example.anxdre.ifilm.fragment.FragmentMoviePopular;
import com.example.anxdre.ifilm.fragment.FragmentMovieUpcoming;

public class MainMenu extends AppCompatActivity implements MovieAdapter.ListOnClick, FavoriteAdapter.Clicked {
    public static ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);

        addtabs(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void addtabs (ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentMovieNow(), "Now Playing");
        adapter.addFrag(new FragmentMoviePopular(), "Popular");
        adapter.addFrag(new FragmentMovieUpcoming(), "Upcoming");
        adapter.addFrag(new FragmentMovieFavorite(), "Favorite");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void mclick(Movie movie) {
        Intent i = new Intent(MainMenu.this,DescFilm.class);
        i.putExtra("title",String.valueOf(movie.getTitle()));
        i.putExtra("image",String.valueOf(movie.getPoster()));
        i.putExtra("ID",String.valueOf(movie.getId()));
        i.putExtra("overview",String.valueOf(movie.getOverview()));
        i.putExtra("release_date",String.valueOf(movie.getRelease_date()));
        i.putExtra("vote_average",String.valueOf(movie.getVote_average()));
        startActivity(i);
    }

    @Override
    public void OnClick(Favorite favorite) {
        Intent a = new Intent(this, DescFilm.class);
        a.putExtra("ID", String.valueOf(favorite.getId()));
        a.putExtra("title", String.valueOf(favorite.getTitle()));
        a.putExtra("image", String.valueOf(favorite.getPoster()));
        startActivity(a);
    }
}
