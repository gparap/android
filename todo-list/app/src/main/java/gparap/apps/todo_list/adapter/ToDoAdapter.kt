/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.todo_list.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gparap.apps.todo_list.R;
import gparap.apps.todo_list.data.ToDoModel;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<ToDoModel> todosList;

    public void setTodosList(List<ToDoModel> todosList) {
        this.todosList = todosList;
        notifyDataSetChanged();
    }

    public ToDoAdapter() {
        this.todosList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ToDoAdapter.ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create new to-do list view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_view, parent, false);
        return new ToDoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ToDoAdapter.ToDoViewHolder holder, final int position) {
        //bind ViewHolder contents with list elements
        holder.textViewToDo.setText(todosList.get(position).getTodo());
        holder.textViewDeadline.setText(todosList.get(position).getDeadlineTimeStamp());
        holder.checkBoxDone.setChecked(todosList.get(position).isDone());
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
        TextView textViewToDo, textViewDeadline;
        CheckBox checkBoxDone;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);

            //get view widgets
            textViewToDo = itemView.findViewById(R.id.textViewTodo);
            textViewDeadline = itemView.findViewById(R.id.textViewDeadline);
            checkBoxDone = itemView.findViewById(R.id.checkBoxDone);
        }
    }
}
