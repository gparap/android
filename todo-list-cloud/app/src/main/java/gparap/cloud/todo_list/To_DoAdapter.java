package gparap.cloud.todo_list;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class To_DoAdapter extends FirestoreRecyclerAdapter<To_Do, To_DoAdapter.To_DoViewHolder> {
    private final To_DoListener todoListener;

    public To_DoAdapter(@NonNull FirestoreRecyclerOptions<To_Do> options, To_DoListener todoListener) {
        super(options);
        this.todoListener = todoListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull To_DoAdapter.To_DoViewHolder holder, int position, @NonNull To_Do model) {
        //fill in recycler view items
        holder.textViewTimestamp.setText(DateFormat.format("dd/MM/yyyy 'at' hh:mm a", model.getTimestamp().toDate()));
        holder.textViewTODO.setText(model.getTodo());
        holder.checkBoxIsDone.setChecked(model.getIsDone());
    }

    @NonNull
    @Override
    public To_DoAdapter.To_DoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.to_do_view, parent, false);
        return new To_DoViewHolder(view);
    }

    /**
     * Describes recycler view items.
     */
    @SuppressWarnings({"Convert2Lambda", "ConstantConditions"})
    public class To_DoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTimestamp, textViewTODO;
        CheckBox checkBoxIsDone;

        public To_DoViewHolder(@NonNull View itemView) {
            super(itemView);

            //get widgets
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
            textViewTODO = itemView.findViewById(R.id.textViewTODO);
            checkBoxIsDone = itemView.findViewById(R.id.checkBoxIsDone);

            //delegate checkbox state
            checkBoxIsDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isDone) {
                    //check if checkbox is pressed
                    if (isDone != (Boolean) getSnapshots().getSnapshot(getAdapterPosition()).get("isDone")) {
                        todoListener.setCheckedChanged(isDone, getSnapshots().getSnapshot(getAdapterPosition()));
                    }
                }
            });

            //delegate deletion of todos
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    todoListener.deleteTODO(getSnapshots().getSnapshot(getAdapterPosition()));
                    return false;
                }
            });

            //delegate update of todos
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoListener.updateTODO(getSnapshots().getSnapshot(getAdapterPosition()));
                }
            });
        }
    }
}
