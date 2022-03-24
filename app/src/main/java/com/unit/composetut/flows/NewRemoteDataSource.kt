package com.unit.composetut.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewRemoteDataSource(private val newsApi: NewsApi) {

    val latestNews: Flow<List<ArticleHeadline>> = flow {
        while (true) {
            val latestNews = newsApi.fetchLatestNews()
            emit(latestNews)
            delay(5000)
        }
    }

}

interface NewsApi {
    suspend fun fetchLatestNews(): List<ArticleHeadline>
}

data class ArticleHeadline(val articleId: String, val articleName: String, val isFavorite: Boolean)