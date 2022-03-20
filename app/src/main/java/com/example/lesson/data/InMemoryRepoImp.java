package com.example.lesson.data;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepoImp implements Repo{
    // Singleton
    private static InMemoryRepoImp repo;

    private InMemoryRepoImp()
    {
        init();
    }

    private void init() {

        create(new Note("Заметка ", "Описание 1"));
        create(new Note("Заметка ", "Описание 2"));
        create(new Note("Заметка ", "Описание 3"));
        create(new Note("Заметка ", "Описание 4"));
        create(new Note("Заметка ", "Описание 5"));
        create(new Note("Заметка ", "Описание 6"));
        create(new Note("Заметка ", "Описание 7"));
        create(new Note("Заметка ", "Описание 8"));
        create(new Note("Заметка ", "Описание 9"));
        create(new Note("Заметка ", "Описание 10"));

    }

    public static InMemoryRepoImp getInstance() {
        if(repo == null)
        {
            repo = new InMemoryRepoImp();
        }
        return repo;
    }

    private final ArrayList<Note> notes = new ArrayList<>();
    private int counter = 0;


    @Override
    public int create(Note note) {
        int id = counter++;
        note.setId(id);
        notes.add(note);
        return id;
    }

    @Override
    public Note read(int id) {
        for(int i = 0; i < notes.size(); i++)
        {
            if(notes.get(i).getId() == id)
                return notes.get(i);
        }
        return null;
    }

    @Override
    public void update(Note note) {
        for(int i = 0; i < notes.size(); i++)
        {
            if(notes.get(i).getId() == note.getId()) {
                notes.set(i, note);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for(int i = 0; i < notes.size(); i++)
        {
            if(notes.get(i).getId() == id) {
                notes.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Note> getAll() {
        return notes;
    }
}
