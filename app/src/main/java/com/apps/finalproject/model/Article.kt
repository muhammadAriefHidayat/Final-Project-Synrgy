package com.apps.finalproject.model

import android.os.Parcelable
import com.apps.finalproject.model.response.ArticleResponse
import com.apps.finalproject.model.response.DataArticle
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
		content = this.title
	)
}

fun List<DataArticle>.toListArticle() : MutableList<Article>{
	val listArticle = mutableListOf<Article>()
	this.forEach { listArticle.add(it.toArticle()) }
	return listArticle
}



