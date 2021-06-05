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
package gparap.apps.todo_list.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;

public class ToDoModel implements Parcelable {
    private long id;
    private boolean isDone;
    private String todo;
    private Long deadlineTimeStamp;
    private final Long creationTimeStamp;

    public long getId() {
        return id;
    }
    public void setId(int id) {
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

    public String getDeadlineTimeStampFormatted() {
        //autoformat the to-do dealine timestamp to the default locale
        return DateFormat.getDateTimeInstance().format(deadlineTimeStamp);
    }

    public void setDeadlineTimeStamp(Long deadlineTimeStamp) {
        this.deadlineTimeStamp = deadlineTimeStamp;
    }

    public String getCreationTimeStampFormatted() {
        //autoformat the to-do creation timestamp to the default locale
        return DateFormat.getDateTimeInstance().format(creationTimeStamp);
    }

    public ToDoModel() {
        deadlineTimeStamp = null;
        creationTimeStamp = System.currentTimeMillis();
    }

    protected ToDoModel(Parcel in) {
        id = in.readLong();
        isDone = in.readByte() != 0;
        todo = in.readString();
        if (in.readByte() == 0) {
            deadlineTimeStamp = null;
        } else {
            deadlineTimeStamp = in.readLong();
        }
        if (in.readByte() == 0) {
            creationTimeStamp = null;
        } else {
            creationTimeStamp = in.readLong();
        }
    }

    public static final Creator<ToDoModel> CREATOR = new Creator<ToDoModel>() {
        @Override
        public ToDoModel createFromParcel(Parcel in) {
            return new ToDoModel(in);
        }

        @Override
        public ToDoModel[] newArray(int size) {
            return new ToDoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeByte(isDone ? (byte) 1 : 0);
        dest.writeString(todo);
        dest.writeLong(deadlineTimeStamp);
        dest.writeLong(creationTimeStamp);
    }
}
