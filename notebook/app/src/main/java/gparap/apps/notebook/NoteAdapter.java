package gparap.apps.notebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

/**
 * Created by gparap on 2020-10-21.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    protected Context context;
    private List<Note> notes;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create an item view
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.cardview_note, parent, false);

        //return item view holder
        return new NoteViewHolder(itemView);
    }

    @SuppressWarnings("Convert2Lambda")
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        //display note titles on RecyclerView
        holder.textViewNoteTitle.setText(notes.get(position).getTitle());

        //if item is clicked open node activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add note data to intent
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("id", notes.get(position).getId());
                intent.putExtra("title", notes.get(position).getTitle());
                intent.putExtra("details", notes.get(position).getDetails());
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);   //leave no history trace
                context.startActivity(intent);

                //finish activity from context
                MainActivity activity = (MainActivity) context;
                activity.finish();
            }
        });

        //if item is long clicked delete node
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //display delete confirmation dialog
                AlertDialog.Builder builder = new MaterialAlertDialogBuilder(context)
                        .setTitle("Delete Note")
                        .setMessage("The selected note will be deleted. Are you sure?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //delete note from database
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                if (databaseManager.delete(notes.get(position).getId())) {
                                    Toast.makeText(context, "Note deleted successfully.", Toast.LENGTH_SHORT).show();

                                    //delete note from adapter
                                    notes.remove(position);
                                    notifyItemRemoved(position);
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        //return notes count
        return notes.size();
    }

    /**
     * Describes RecyclerView item.
     */
    static class NoteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewNote;
        TextView textViewNoteTitle;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            //get widgets
            imageViewNote = itemView.findViewById(R.id.imageViewNote);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
        }
    }
}
