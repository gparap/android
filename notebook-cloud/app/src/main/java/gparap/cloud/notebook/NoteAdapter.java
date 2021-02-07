package gparap.cloud.notebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

@SuppressWarnings("Convert2Lambda")
public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    private final Context context;

    public NoteAdapter(FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(NoteAdapter.NoteViewHolder holder, int i, Note note) {
        //display item in RecyclerView
        holder.noteTitle.setText(note.getTitle());

        //goto note activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setup data to pass to note activity
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("note_id", note.getId());
                intent.putExtra("note_title", note.getTitle());
                intent.putExtra("note_details", note.getDetails());

                //finish activity
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        //delete note from database with long press
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //create popup menu with "delete" and "cancel" options
                PopupMenu popup = new PopupMenu(context, v);
                popup.setGravity(Gravity.CENTER);
                popup.getMenu().add("Delete Note").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //delete note from database
                        Task<Void> task = ((MainActivity) context).databaseManager.deleteNote(note.getId(), ((MainActivity) context).databaseManager.getFirebaseUser().getUid());
                        task.addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context, "Note deleted successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Sorry, cannot delete note.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        return false;
                    }
                });
                popup.getMenu().add("Cancel").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popup.show();
                return false;
            }
        });
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new NoteViewHolder(view);
    }

    /**
     * Describes the RecyclerView items.
     */
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            //get widgets from item
            noteTitle = itemView.findViewById(R.id.cardViewNoteTitle);
        }
    }
}






