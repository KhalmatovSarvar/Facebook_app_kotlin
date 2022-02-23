package com.example.facebook_app_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.facebook_app_kotlin.model.Link
import com.example.facebook_app_kotlin.utils.Utils
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.nodes.Document


class CreatePostActivity : AppCompatActivity() {

    private lateinit var edt_text: EditText
    private lateinit var btn_post: Button
    private lateinit var ll_preview: LinearLayout
    private lateinit var iv_post: ImageView
    private lateinit var tv_title: TextView
    private lateinit var tv_description: TextView
    private lateinit var btn_close: ImageView
    private lateinit var btn_remove:ImageView
    private var isFound = false
    var link = Link()

    lateinit var imageView: ShapeableImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initViews()
    }

    private fun initViews() {
        edt_text = findViewById(R.id.edt_link)
        btn_post = findViewById(R.id.btn_post)
        ll_preview = findViewById(R.id.ll_preview)
        iv_post = findViewById(R.id.link_image_create_post)
        tv_title = findViewById(R.id.title_link_post)
        tv_description = findViewById(R.id.description_link)
        btn_close = findViewById(R.id.id_back)
        btn_remove = findViewById(R.id.btn_remove)

        btn_close.setOnClickListener {
            finish()
        }

        btn_remove.setOnClickListener {
            ll_preview.visibility = View.GONE
            btn_remove.visibility = View.GONE
        }

        btn_post.setOnClickListener {
           val intent = Intent()
            intent.putExtra("object",link)
            setResult(Activity.RESULT_OK,intent)
            Log.d("RRR", "Create -> ${link.toString()}")
            finish()
        }

        edt_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (containsLink(p0.toString())) ll_preview.visibility = View.VISIBLE
                else ll_preview.visibility = View.GONE

                if (p0.toString().equals(null)){ btn_post.setBackgroundResource(R.drawable.shape_border_rounded_button)
                btn_post.isEnabled= false
                }
                else btn_post.setBackgroundResource(R.drawable.shape_border_rounded_btn_enabled)
                btn_post.isEnabled = true
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
    private fun getElementsJsoup(url: String) {
        Utils.getJsoupData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: Document ->
                val metaTags = result.getElementsByTag("meta")
                for (element in metaTags) {
                    when {
                        element.attr("property").equals("og:image") -> {
                            link.image = element.attr("content")
                            Picasso.get().load(element.attr("content")).into(iv_post)
                        }
                        element.attr("property").equals("og:description") -> {
                            link.description = element.attr("content")
                            tv_description.text = element.attr("content")
                        }
                        element.attr("property").equals("og:title") -> {
                            link.title = element.attr("content")
                            tv_title.text = element.attr("content")
                        }
                    }
                }
            }
    }

    private fun containsLink(input: String): Boolean {
        val parts = input.split(" ")
        for (item in parts) {
            if (Patterns.WEB_URL.matcher(item).matches()) {
                getElementsJsoup(item)
                isFound = true
                return true
            }
        }
        return false
    }
}

