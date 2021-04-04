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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SearchView;
import android.widget.Toast;

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
    private Button button_LaunchMain;
    private ArrayList<String> tagPreferences = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        button_LaunchMain = findViewById(R.id.button_LaunchMain);
        button_LaunchMain.setOnClickListener(v -> goToMain());
    }

    @Override
    protected void onPostResume() {
        tagPreferences.clear();
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        tagList = new ArrayList<>();

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

    public void tagCheckBoxToggle(View v) {
        CheckBox checkBox = (CheckBox) v;
        String checkBoxText = checkBox.getText().toString();
        if (checkBox.isChecked()) {
            tagPreferences.add(checkBoxText);
        } else {
            tagPreferences.remove(checkBoxText);
        }
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

    private void goToMain() {
        if (tagPreferences.size() > 0) {
            Intent intent = new Intent(this, StreamerdexMainActivity.class);
            intent.putStringArrayListExtra("tagPreferences", tagPreferences);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),  "Make sure to select at least 1 tag.", Toast.LENGTH_SHORT).show();
        }
    }

}