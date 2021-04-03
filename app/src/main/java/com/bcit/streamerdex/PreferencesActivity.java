package com.bcit.streamerdex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    DatabaseReference dbStreamerdex = FirebaseDatabase.getInstance()
            .getReference("streamerdex").child("tag_library");

    private SearchView searchView;
    private TagAdapter tagAdapter;
    private ArrayList<TagItem> tagList;
    private ArrayList<TagItem> selectedTagsList;

    private Button button_LaunchMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        button_LaunchMain = findViewById(R.id.button_LaunchMain);

        button_LaunchMain.setOnClickListener(v -> goToMain());
    }

    @Override
    protected void onStart() {
        super.onStart();
        tagList  = new ArrayList<>();

        // get data
        dbStreamerdex.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tagList.clear();
                for (DataSnapshot tagSnapshot : dataSnapshot.getChildren()) {
                    String tagString = tagSnapshot.getValue(String.class);
                    TagItem tagItem = new TagItem(tagString);
                    tagList.add(tagItem);
                }
                setUpRecycler();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
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

    private void goToMain() { // todo: should pass data from selected tags to get cards
        Intent i = new Intent(this, StreamerdexMainActivity.class);
        startActivity(i);
    }
}