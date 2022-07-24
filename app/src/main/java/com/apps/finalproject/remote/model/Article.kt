package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.DataArticle
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
	val date: String,
	val photo: String,
	val title: String,
	val content: String
) : Parcelable

fun DataArticle.toArticle() : Article {
	return Article(
		date = this.date,
		photo = this.photo,
		title = this.title,
		content = this.content
	)
}

fun List<DataArticle>.toListArticle() : MutableList<Article>{
	val listArticle = mutableListOf<Article>()
	this.forEach { listArticle.add(it.toArticle()) }
	return listArticle
}



