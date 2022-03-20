package com.example.lesson.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson.R;
import com.example.lesson.data.InMemoryRepoImp;
import com.example.lesson.data.Note;
import com.example.lesson.data.Repo;
import com.example.lesson.recycler.NotesAdapter;

public class NotesListActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {
    public static final int EDIT_NOTE_REQUEST = 0;
    public static final String EDIT_NOTE = "EDIT_NOTE";
    private RecyclerView list;
    private Repo repo = InMemoryRepoImp.getInstance();
    private NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        list = findViewById(R.id.list);

        adapter = new NotesAdapter();

        adapter.setOnNoteClickListener(this);

        adapter.setNotes(repo.getAll());

        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onNoteClick(Note note) {

        Log.d("happy", note.getDescription());

        Intent editNoteIntent = new Intent(this, EditNoteActivity.class);

        editNoteIntent.putExtra(Note.NOTE, note);

        startActivityForResult(editNoteIntent, EDIT_NOTE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) throw new AssertionError();
            Note note = (Note) data.getSerializableExtra(Note.NOTE);
            repo.update(note);
            adapter.setNotes(repo.getAll());
        }


    }

    /*public int getEditText (){

        SharedPreferences sharedPreferences =getSharedPreferences(String.valueOf(NotesListActivity.EDIT_NOTE_REQUEST),MODE_PRIVATE);
        return 0;}
*/

}
