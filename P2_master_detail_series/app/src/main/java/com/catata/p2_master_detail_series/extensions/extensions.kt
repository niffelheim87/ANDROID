import android.content.Context
import android.widget.ImageView

fun ImageView.setFromString(imageName: String?){
    val imageNameWithOutExtension = imageName?.split(".")?.get(0) ?: "no_image"
    val id = this.context.resources.getIdentifier(
        imageNameWithOutExtension,
        "drawable",
        context.packageName
    )
    this.setImageResource(id)

}

fun String.toDrawableIdentifier(context: Context):Int{
    val imageNameWithOutExtension = this.split(".")[0]
    return context.resources.getIdentifier(
        imageNameWithOutExtension,
        "drawable",
        context.packageName
    )
}