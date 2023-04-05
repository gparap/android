package gparap.apps.recipes.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gparap.apps.recipes.R
import gparap.apps.recipes.data.RecipeCategoryModel

class RecipeCategoryAdapter :
    RecyclerView.Adapter<RecipeCategoryAdapter.RecipeCategoryViewHolder>() {
    var recipeCategories = ArrayList<RecipeCategoryModel>()
    private lateinit var context: Context

    class RecipeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.text_view_recipe_category)
        val categoryImage: ImageView = itemView.findViewById(R.id.image_view_recipe_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoryViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(
            context.resources.getLayout(R.layout.cardview_category), parent, false
        )
        return RecipeCategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeCategories.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {
        holder.categoryName.text = recipeCategories[position].name

        val categoryImageUri = recipeCategories[position].imageUri
        Picasso.get().load(categoryImageUri).into(holder.categoryImage)
    }
}