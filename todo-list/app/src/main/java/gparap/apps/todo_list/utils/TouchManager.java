package gparap.apps.todo_list.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import gparap.apps.todo_list.R;
import gparap.apps.todo_list.adapter.ToDoAdapter;

/**
 * Helper for swipe to the left and delete To_Do.
 * Created by gparap on 2020-10-17.
 */
public class TouchManager extends ItemTouchHelper.SimpleCallback {
    ToDoAdapter adapter;
    Context context;

    public TouchManager(int dragDirs, int swipeDirs, ToDoAdapter adapter, Context context) {
        super(dragDirs, swipeDirs);
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == ItemTouchHelper.LEFT){
            //create confirmation dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Delete TODO")
                    .setMessage("The requested TODO will be deleted. Are you sure?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //delete to_do from database
                            adapter.deleteTodo(viewHolder.getAdapterPosition());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.notifyItemChanged(viewHolder.getAdapterPosition());

                            //close dialog
                            dialog.dismiss();
                        }
                    });
            //display dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //control swipe horizontal displacement
        dX *= 0.25;
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        //draw red background
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.RED);
        colorDrawable.setBounds(viewHolder.itemView.getRight(), viewHolder.itemView.getTop(), (int) (viewHolder.itemView.getRight() + dX), viewHolder.itemView.getBottom());

        //image drawable
        View item = viewHolder.itemView;
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_24);
        assert drawable != null;
        int margin = (item.getHeight() - drawable.getIntrinsicHeight()) / 2;
        int top = item.getTop() + (item.getHeight()-  drawable.getIntrinsicHeight())/2;
        int bottom = top + drawable.getIntrinsicHeight();
        int left = item.getRight() - margin - drawable.getIntrinsicWidth();
        int right = item.getRight() - margin;
        drawable.setBounds(left,top ,right,bottom);

        //draw drawables on canvas
        colorDrawable.draw(c);
        if (Math.abs(dX) > getDisplayWidth()/8.0){
            drawable.draw(c);
        }
    }

    /**
     * Gets the absolute width of the display in pixels.
     * @return width
     */
    private int getDisplayWidth() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);

        //return display width
        return displayMetrics.widthPixels;
    }
}
