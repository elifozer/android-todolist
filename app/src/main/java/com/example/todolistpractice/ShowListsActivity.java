package com.example.todolistpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolistpractice.data.ToDoListHandler;
import com.example.todolistpractice.model.ToDoList;
import com.example.todolistpractice.ui.ListsRVAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShowListsActivity extends AppCompatActivity {
    private static final String TAG = "ShowListsActivity";

    private TextView popupTitle;
    private EditText popupListName;
    private Button popupButton;
    private FloatingActionButton fabAdd;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.AdapterDataObserver observer;

    private List<ToDoList> toDoLists;
    private ToDoListHandler toDoListHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists);

        fabAdd = findViewById(R.id.showLists_fab_add);
        recyclerView = findViewById(R.id.showLists_recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toDoListHandler = new ToDoListHandler(this);
        toDoLists = toDoListHandler.getAllToDoLists();

        for(ToDoList t: toDoLists)
            Log.d(TAG, "onCreate: listName=" + t.getListName());

        adapter = new ListsRVAdapter(ShowListsActivity.this, toDoLists);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        observer = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);

                if(adapter.getItemCount() == 0){
                    Log.d(TAG, "onItemRangeRemoved: itemCount=" + adapter.getItemCount());

                    startActivity(new Intent(ShowListsActivity.this, MainActivity.class));
                    finish();
                }
            }
        };

        adapter.registerAdapterDataObserver(observer);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.popup_todolist,null);

        popupTitle = view.findViewById(R.id.popup_title);
        popupListName = view.findViewById(R.id.popup_listName);
        popupButton = view.findViewById(R.id.popup_button);

        popupTitle.setText(R.string.popup_addTitle);
        popupButton.setText(R.string.popup_saveButton);

        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newListName = popupListName.getText().toString().trim();

                if(!newListName.isEmpty()){
                    ToDoList toDoList = new ToDoList();
                    toDoList.setListName(newListName);

                    saveToDoList(toDoList);
                }
                else{
                    Snackbar.make(v, "List name cannot be empty", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveToDoList(ToDoList toDoList) {
        toDoListHandler = new ToDoListHandler(this);
        toDoListHandler.addToDoList(toDoList);

        toDoLists.add(0, toDoList);

        Log.d(TAG, "saveToDoList: listId=" + toDoList.getId());

        adapter.notifyItemInserted(0);

        alertDialog.dismiss();
    }
}
