/*
 * Copyright 2026 gparap
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
package gparap.apps.classifieds.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.models.ClassifiedModel;

public class ClassifiedsAdapter extends RecyclerView.Adapter<ClassifiedsAdapter.ClassifiedsViewHolder> {
    private Context context;
    private ArrayList<ClassifiedModel> classifieds = new ArrayList<>();

    public void setClassifieds(ArrayList<ClassifiedModel> classifieds) {
        this.classifieds = classifieds;
    }

    @NonNull
    @Override
    public ClassifiedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ClassifiedsViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_classifieds, parent, false));
    }

    @Override
    public int getItemCount() {
        return classifieds.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifiedsViewHolder holder, int position) {
        Glide.with(holder.image.getContext())
                .load(R.drawable.ic_home_black_24dp)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);

        holder.shortDesc.setText(classifieds.get(position).getShortDescription());
    }

    public static class ClassifiedsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView shortDesc;

        public ClassifiedsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView_classified);
            shortDesc = itemView.findViewById(R.id.textView_classified_short_desc);
            System.out.println(shortDesc.getText().toString());
        }
    }
}
