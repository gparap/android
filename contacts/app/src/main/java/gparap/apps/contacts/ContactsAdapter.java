package gparap.apps.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Created by gparap on 2020-09-22.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    Context context;
    ArrayList<Contact> contacts;
    int itemCount;  //total number of items in data set

    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        init();
    }

    private void init() {
        itemCount = contacts.size();
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_contacts, parent, false);

        //create view holder
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        //display the data at the specified position
        holder.textViewContactName.setText(contacts.get(position).getName());
        holder.textViewPhoneNumberPrimary.setText(contacts.get(position).getPhoneNumberPrimary());
        holder.textViewPhoneNumberSecondary.setText(contacts.get(position).getPhoneNumberSecondary());

        //handle check box visibility
        if (!holder.textViewPhoneNumberSecondary.getText().toString().equals("")){
            holder.checkBoxCallSecondaryPhoneNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewContactName,
                textViewPhoneNumberPrimary,
                textViewPhoneNumberSecondary;
        ImageButton imageButtonCall;
        CheckBox checkBoxCallSecondaryPhoneNumber;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            //find views
            textViewContactName = itemView.findViewById(R.id.textViewContactName);
            textViewPhoneNumberPrimary = itemView.findViewById(R.id.textViewPhoneNumberPrimary);
            textViewPhoneNumberSecondary = itemView.findViewById(R.id.textViewPhoneNumberSecondary);
            imageButtonCall = itemView.findViewById(R.id.imageButtonCall);
            checkBoxCallSecondaryPhoneNumber = itemView.findViewById(R.id.checkBoxCallSecondaryPhoneNumber);

            //make a phone call
            imageButtonCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel: " + contacts.get(getAdapterPosition()).getPhoneNumberPrimary()));
                    //if there is a secondary phone number and check box is checked
                    if (!contacts.get(getAdapterPosition()).getPhoneNumberSecondary().equals("") &&
                            checkBoxCallSecondaryPhoneNumber.isChecked()){
                        intent.setData(Uri.parse("tel: " + contacts.get(getAdapterPosition()).getPhoneNumberSecondary()));
                    }
                    context.startActivity(intent);
                }
            });
        }
    }
}
