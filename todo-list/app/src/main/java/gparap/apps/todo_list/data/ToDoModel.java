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
package gparap.apps.todo_list.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;

@Entity(tableName = "todos")
public class ToDoModel {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "done")
    private boolean isDone;

    private String todo;

    @ColumnInfo(name = "time_set")
    private String deadlineTime;

    @ColumnInfo(name = "date_set")
    private String deadlineDate;

    @ColumnInfo(name = "deadline")
    private String deadlineTimeStamp;

    @ColumnInfo(name = "creation")
    private Long creationTimeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDone(int done) {
        isDone = done == 1;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineTimeStamp() {
        return deadlineTimeStamp;
    }

    public void setDeadlineTimeStamp(String deadlineTimeStamp) {
        this.deadlineTimeStamp = deadlineTimeStamp;
    }

    public Long getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Long creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public String getCreationTimeStampFormatted() {
        //autoformat the to-do creation timestamp to the default locale
        return DateFormat.getDateTimeInstance().format(creationTimeStamp);
    }

    public ToDoModel() {
        isDone = false;
        deadlineTimeStamp = "";
        creationTimeStamp = System.currentTimeMillis();
    }
}
