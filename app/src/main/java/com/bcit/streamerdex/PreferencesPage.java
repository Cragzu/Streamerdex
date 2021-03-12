package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class PreferencesPage extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private TagAdapter tagAdapter;
    private ArrayList<TagItem> tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_page);

        loadTagList();
        setUpRecycler();
    }

    /**
     * Loads in dummy list of tags for now.
     */
    private void loadTagList() {
        tagList = new ArrayList<>();
        tagList.add(new TagItem("English"));
        tagList.add(new TagItem("No Backseating"));
        tagList.add(new TagItem("No Spoilers"));
        tagList.add(new TagItem("Driving/Racing Game"));
        tagList.add(new TagItem("Ranked"));
        tagList.add(new TagItem("ASMR"));
        tagList.add(new TagItem("IRL"));
        tagList.add(new TagItem("First Playthrough"));
        tagList.add(new TagItem("Closed Captions"));
        tagList.add(new TagItem("LGBTQIA+"));
        tagList.add(new TagItem("Platformer"));
        tagList.add(new TagItem("Animals"));
        tagList.add(new TagItem("Family Friendly"));
    }

    private void setUpRecycler() {
        RecyclerView recyclerView = findViewById(R.id.preferencesPage_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        tagAdapter = new TagAdapter(tagList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tagAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tag, menu);

        MenuItem searchItem = menu.findItem(R.id.preferences_searchView);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        tagAdapter.getFilter().filter(newText);
        return false;
    }
}