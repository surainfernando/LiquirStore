package hfad.com.liquirstore;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LiquirBatchAdapter extends
        RecyclerView.Adapter<LiquirBatchAdapter.ViewHolder> {
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // ... constructor and member variables

    // Usually involves inflating a layout from XML and returning the holder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView seller_name;
        public TextView batch_id;
        public TextView batch_date;
        public TextView volume_text;
        public TextView permit_number;
        private Context context;




        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            super(itemView);
            this.context = context;
            seller_name = (TextView) itemView.findViewById(R.id.seller_name);
            batch_id=(TextView)itemView.findViewById(R.id.batch_id);
            batch_date=(TextView)itemView.findViewById(R.id.batch_date);
          volume_text=(TextView)itemView.findViewById(R.id.volume_text);
          permit_number=(TextView)itemView.findViewById(R.id.permit_number);
            itemView.setOnClickListener(this);



        }
        @Override
        public void onClick(View view) {

            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position);
                }
            }
        }
    }
    private List<LiquirBatch> mContacts;

    // Pass in the contact array into the constructor
    public LiquirBatchAdapter(List<LiquirBatch> contacts) {
        mContacts = contacts;
    }
    @Override
    public LiquirBatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.liquir_batch_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LiquirBatchAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        LiquirBatch batch = mContacts.get(position);
        TextView seller_name=holder.seller_name;
        seller_name.setText(batch.getCreator_name());
        TextView batch_id=holder.batch_id;
        batch_id.setText(String.valueOf(batch.getProduct_id()));
        TextView batch_date=holder.batch_date;
        batch_date.setText(batch.getName());

        TextView volume_text=holder.volume_text;
        volume_text.setText(batch.getDescription());
        TextView permit_number=holder.permit_number;
        permit_number.setText(String.valueOf( batch.getBottle_batch_id()));






        // Set item views based on your views and data model
//        TextView textView = holder.nameTextView;
//        textView.setText(batch.getCreator_name());
//        Button button = holder.messageButton;
//        button.setText(batch.getCreator_permit());
//        TextView permit_number=holder.permit_number;
//        permit_number.setText(batch.getCreator_permit());
//        TextView date_created=holder.date_created;
//        date_created.setText(batch.date_created);
//        TextView volume=holder.volume;
//        volume.setText(String.valueOf(batch.volume));




    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}
