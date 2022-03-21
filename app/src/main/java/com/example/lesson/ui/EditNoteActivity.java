package com.example.lesson.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson.R;
import com.example.lesson.data.InMemoryRepoImp;
import com.example.lesson.data.Note;
import com.example.lesson.data.Repo;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private Note note;
    private EditText editTextAddText;
    private EditText editTextTitle;
    private Repo repo = InMemoryRepoImp.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = (Note) getIntent().getSerializableExtra(Note.NOTE);

        // TODO создать внешний вид для редактирования заметки
        setContentView(R.layout.activity_edit_note);
        editTextTitle = findViewById(R.id.edittext_title);


        editTextTitle.setText(note.getTitle());  // передается текст title в этот активити


        editTextAddText = findViewById(R.id.edittext_add_text);
        editTextAddText.setText(note.getDescription()); // передается текст description в этот активити

        Button buttEditSave = findViewById(R.id.butt_edit_Save);

        buttEditSave.setOnClickListener(this);

    }

    void saveNote() {
        int noteId = note.getId();
        Note editNoteText = note;
        editNoteText.setTitle(editTextTitle.getText().toString());  // устанавливается изменный текст
        editNoteText.setDescription(editTextAddText.getText().toString()); // устанавливается изменный текст
        editNoteText.setId(noteId);
        repo.update(editNoteText);

        Intent result = new Intent();
        result.putExtra(Note.NOTE, this.note);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onClick(View v) { // method for button
        switch (v.getId()) {
            case R.id.butt_edit_Save:
                saveNote();
                break;

        }


    }

}