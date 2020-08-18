package com.nikhil.hiltwithdatabinding.responses

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class HeroResponse(
    val name: String,
    val realname: String,
    val team: String,
    val firstappearance: String,
    val createdby: String,
    val publisher: String,
    val imageurl: String,
    val bio: String
) {

    companion object {
        @BindingAdapter("android:imageUrl")
        @JvmStatic
        fun loadHeroImage(view: View, imagePath: String) {
            val heroImageView = view as ImageView
            Glide.with(view.context).load(imagePath).into(heroImageView)
        }
    }

    override fun toString(): String {
        return "HeroResponse(name='$name', realname='$realname', team='$team', firstappearance='$firstappearance', createdby='$createdby', publisher='$publisher', imageurl='$imageurl', bio='$bio')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeroResponse

        if (name != other.name) return false
        if (realname != other.realname) return false
        if (team != other.team) return false
        if (firstappearance != other.firstappearance) return false
        if (createdby != other.createdby) return false
        if (publisher != other.publisher) return false
        if (imageurl != other.imageurl) return false
        if (bio != other.bio) return false

        return true
    }

}