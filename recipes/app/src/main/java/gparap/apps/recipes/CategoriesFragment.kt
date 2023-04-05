package gparap.apps.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.recipes.adapters.RecipeCategoryAdapter
import gparap.apps.recipes.data.RecipeCategoryModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        //create a list of test categories and just give name and image url
        val categoriesList = createTestCategoriesList()

        //setup the categories RecyclerView with adapter
        val categoriesRecyclerView = view.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
        categoriesRecyclerView.layoutManager = GridLayoutManager(view.context, 2)
        val adapter = RecipeCategoryAdapter()
        adapter.recipeCategories = categoriesList
        categoriesRecyclerView.adapter = adapter

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun createTestCategoriesList() : ArrayList<RecipeCategoryModel> {
        val categoriesList = ArrayList<RecipeCategoryModel>()
        categoriesList.add(
            RecipeCategoryModel("1", "c1", "", "", "",
                "https://upload.wikimedia.org/wikipedia/commons/5/5f/Baba_Ganoush_05of05_%288735238183%29.jpg",
                "",""
            )
        )
        categoriesList.add(
            RecipeCategoryModel("2", "c2", "", "", "",
                "https://upload.wikimedia.org/wikipedia/commons/3/35/Tea_in_sahara%28algerian_desert%29.jpg",
                "",""
            )
        )
        categoriesList.add(
            RecipeCategoryModel("3", "c3", "", "",  "",
                "https://upload.wikimedia.org/wikipedia/commons/6/6f/Bread_in_Boudin.jpg","",""
            )
        )
        categoriesList.add(
            RecipeCategoryModel("4", "c4", "", "", "",
                "https://upload.wikimedia.org/wikipedia/commons/d/de/NCI_cream_cheese_bagel.jpg",
                "",""
            )
        )
        return categoriesList
    }
}