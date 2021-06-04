package gparap.apps.todo_list.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gparap.apps.todo_list.R;
import gparap.apps.todo_list.model.ToDoModel;
import gparap.apps.todo_list.data.DatabaseManager;

/**
 * Created by gparap on 2020-10-16.
 */
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<ToDoModel> todosList;
    private final DatabaseManager databaseManager;
    private long id;

    public void setTodosList(List<ToDoModel> todosList) {
        this.todosList = todosList;
    }

    public ToDoAdapter(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.todosList = new ArrayList<>();
    }

    /**
     * Adds a new To_Do to list.
     *
     * @param toDoModel To_Do object
     */
    public void addToDo(ToDoModel toDoModel) {
        todosList.add(toDoModel);
        notifyItemInserted(this.todosList.size());
    }

    /**
     * Deletes a To_Do from list and database.
     *
     * @param position element's position
     */
    public void deleteTodo(int position) {
        //delete from database
        if (databaseManager.deleteToDo(todosList.get(position).getId())) {
            //delete from list
            todosList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public ToDoAdapter.ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create new todos view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ToDoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ToDoAdapter.ToDoViewHolder holder, final int position) {
        //bind ViewHolder contents with list elements
        holder.textViewToDo.setText(todosList.get(position).getTodo());
        holder.textViewByTime.setText(todosList.get(position).getTime());
        holder.textViewOnDate.setText(todosList.get(position).getDate());
        holder.checkBoxDone.setChecked(todosList.get(position).isDone());

        //update database with the To_Do's "isDone" checkbox value
        holder.checkBoxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    //get the (database) id of the item from its position on the recycler view
                    id = todosList.get(position).getId();

                    //update database
                    databaseManager.updateToDo(id, isChecked);

                    //update list
                    todosList.get(position).setDone(isChecked);
                }
                //database deletion delay and element is nonexistent
                catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //get number of todos
        return todosList.size();
    }

    /**
     * Describes the view (and its widgets) inside the RecyclerView.
     */
    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewToDo,
                textViewByTime,
                textViewOnDate;
        CheckBox checkBoxDone;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);

            //get view widgets
            textViewToDo = itemView.findViewById(R.id.textViewTodo);
            textViewByTime = itemView.findViewById(R.id.textViewByTime);
            textViewOnDate = itemView.findViewById(R.id.textViewOnDate);
            checkBoxDone = itemView.findViewById(R.id.checkBoxDone);
        }
    }
}
