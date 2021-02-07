package gparap.apps.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by gparap on 2020-10-24.
 */
@SuppressWarnings("Convert2Lambda")
public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {
    private final Context context;
    private List<Wallpaper> wallpapers;

    public WallpaperAdapter(Context context, List<Wallpaper> wallpapers) {
        this.context = context;
        this.wallpapers = wallpapers;
    }

    public void setWallpapers(List<Wallpaper> wallpapers) {
        this.wallpapers = wallpapers;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WallpaperAdapter.WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.WallpaperViewHolder holder, int position) {
        //future task to get items from provider asynchronously
        FutureTask<Void> future = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                try {
                    //create connection
                    URL url = new URL(wallpapers.get(position).getURL());
                    HttpURLConnection connection;
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    //DEBUG
                    String test = connection.getResponseMessage();
                    System.out.println(test);

                    //get items
                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);

                    //update recycler view item and close connection
                    holder.recycleViewItem.setImageBitmap(bitmap);
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, null);

        //fetch items asynchronously
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            executorService.submit(future).get(15, TimeUnit.SECONDS);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        //toast the photographer
        holder.recycleViewItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Photo by " + wallpapers.get(position).getPhotographer(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //goto photo url
        holder.recycleViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = wallpapers.get(position).getURL();
                //noinspection ConstantConditions
                if (url != null || !url.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    public static class WallpaperViewHolder extends RecyclerView.ViewHolder {
        ImageView recycleViewItem;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            recycleViewItem = itemView.findViewById(R.id.recyclerViewItem);
        }
    }
}
