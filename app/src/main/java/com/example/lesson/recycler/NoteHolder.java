package com.example.lesson.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson.R;
import com.example.lesson.data.Note;

public class NoteHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private Note note;

    public NoteHolder(@NonNull View itemView, NotesAdapter.OnNoteClickListener listener) {
        super(itemView);
        title = itemView.findViewById(R.id.note_title);
        description = itemView.findViewById(R.id.note_description);
        itemView.setOnClickListener(view -> listener.onNoteClick(note));
    }
    void bind(Note note)
    {
        this.note = note;

        title.setText(note.getTitle());

        description.setText(note.getDescription());

    }


}
