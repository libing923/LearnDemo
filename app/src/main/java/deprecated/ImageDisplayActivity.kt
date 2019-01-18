package deprecated

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.li.myapplication.R
import org.jetbrains.anko.find

class ImageDisplayActivity : AppCompatActivity() {

    companion object {
        @JvmField
        var ARG_PATH = "image_path"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)
        val path: String? = intent.getStringExtra(ARG_PATH)
        log("path : $path")

        val image: RegionImageView = find(R.id.image)

        path?.let {
            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_error)
                    .fitCenter()
            Glide.with(this).asBitmap().load(it).apply(requestOptions).into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    image.setImage(resource)
                }
            })
//            image.setImage(ImageSource.uri(Uri.fromFile(File(it))))
        }
    }

}