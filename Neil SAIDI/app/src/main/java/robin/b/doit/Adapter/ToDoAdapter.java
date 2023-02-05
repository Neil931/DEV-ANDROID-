package robin.b.doit.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import robin.b.doit.AddNewTask;
import robin.b.doit.MainActivity;
import robin.b.doit.Model.ToDoModel;
import robin.b.doit.R;
import robin.b.doit.Utils.DbHandler;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private MainActivity activity;
    private DbHandler db;

    public ToDoAdapter(DbHandler db,  MainActivity activity){

        this.activity = activity;
        this.db = db;
    }

    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabse();
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    db.updateStatus(item.getId(), 1);
                }
                else{
                    db.updateStatus(item.getId(), 0);
                }
            }
        });

    }
    public int getItemCount(){
        return todoList.size();
    }

    private boolean toBoolean(int n){
        return n != 0;
    }

    public void setTasks(List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContect(){return activity;}

    public void  deleteItem(int position){
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }


    public void editItem(int position){
        ToDoModel item =  todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public  static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckbox);
        }
    }
}
