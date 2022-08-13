package ahmad.fikri.mydiary;

/**
 *
 * NIM      : 10119106
 * Nama     : Ahmad Fikri Maulana
 * Kelas    : IF-3
 *
 * **/

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ahmad.fikri.mydiary.Adapter;
import ahmad.fikri.mydiary.AddNoteActivity;
import ahmad.fikri.mydiary.Note;
import ahmad.fikri.mydiary.NoteDatabase;
import ahmad.fikri.mydiary.R;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    FloatingActionButton button;
    Adapter adapter;
    List<Note> notes;
    FragmentManager fragmentManager;

    public HomeFragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.listOfNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        button = view.findViewById(R.id.addBtn);
        NoteDatabase db = new NoteDatabase(view.getContext());
        notes = db.getNotes();
        adapter = new Adapter(view.getContext(),notes);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(add);
            }
        });

        return view;

    }

}