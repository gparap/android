package gparap.apps.recipes.data

data class RecipeCategoryModel(
    val id: String, //id
    val name: String,   //name
    val description: String,    //desc
    val descriptionAttribution: String, //descAttribText
    val descriptionAttributionLink: String, //descAttribLink
    val imageUri: String,   //img
    val imageAttributionLink: String,   //imgAttrib
    val keywords: String,   //keywords
)