package robin.b.doit;

import androidx.recyclerview.widget.LinearLayoutManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import robin.b.doit.Adapter.ToDoAdapter;
import robin.b.doit.Model.ToDoModel;
import robin.b.doit.Utils.DbHandler;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> tasksList;
    private DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DbHandler(this);
        db.openDatabse();

        tasksList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab = findViewById(R.id.fab);

        tasksList = db.getAllTasks();
        Collections.reverse(tasksList);
        tasksAdapter.setTasks(tasksList);

        fab.setOnClickListener(new View.OnClickListener()){
            @Override
                    public void onClick(View v){
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        }

        ToDoModel task = new ToDoModel();
        task.setTask("This is a test");
        task.setStatus(0);
        task.setId(1);

        tasksList.add(task);

        tasksAdapter.setTasks(tasksList);

        RecyclerView recyclerView = findViewById(R.id.tasksRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        tasksList = db.getAllTasks();
        Collections.reverse(tasksList);
        tasksAdapter.setTasks(tasksList);
        tasksAdapter.notifyDataSetChanged();

    }
}