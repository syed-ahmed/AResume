package com.thesyedahmed.aresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class ProjectsActivity extends AppCompatActivity {
    private List<ProjectsItem> projects;
    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        projects = new ArrayList<>();
        projects.add(new ProjectsItem("AResume", "\nAn Augmented Reality Android app that makes resume reviewing fun and interactive.", R.drawable.ic_launcher,
                "", 1));
        projects.add(new ProjectsItem("RocreadAR", "\nAn Augmented Reality Android app that aims at integrating different media for publishing and communication.", R.drawable.rocreadar,
                "", 2));
        projects.add(new ProjectsItem("ClickWars", "A game based iOS app that implements Multipeer Connectivity Framework and Face Detection.", R.drawable.clickwars,
                "http://www.goo.gl/4qX6sA", 3));
        projects.add(new ProjectsItem("Resarch on Trial Division vs Lucas-Lehmer Algorithm", "Out of trial division algorithm for finding primes and Lucas-Lehmer algorithm for finding Mersenne primes, which algorithm would yield a big prime number faster?", R.drawable.research,
                "http://www.goo.gl/ZoVC3h", 4));
        projects.add(new ProjectsItem("Patterns in Complex Numbers", "Research paper that establishes a conjecture between the solutions of a complex polynomial and the number of sides in a regular polygon.", R.drawable.research,
                "http://docs.google.com/viewer?url=http://www.goo.gl/MgwgWS", 5));
        projects.add(new ProjectsItem("Modelling Probabilities in Games of Tennis", "Research paper that analyzes the probabilities involved in a game of tennis and provides an interesting insight on the odds of winning by players, when they reach deuce.", R.drawable.research,
                "http://docs.google.com/viewer?url=http://www.goo.gl/yUDUu9", 6));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.app_intro:
                Intent intro_intent = new Intent(this, AppIntroductionActivity.class);
                startActivity(intro_intent);
                return true;
            case R.id.about:
                Intent about_intent = new Intent(this, AboutActivity.class);
                startActivity(about_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeAdapter(){
        ProjectsAdapter adapter = new ProjectsAdapter(projects);
        rv.setAdapter(adapter);
    }
}

