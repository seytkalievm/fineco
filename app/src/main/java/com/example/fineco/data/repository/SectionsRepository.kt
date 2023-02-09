package com.example.fineco.data.repository

import com.example.fineco.data.datasource.SectionsDataSource
import javax.inject.Inject

class SectionsRepository @Inject constructor() {

    private val dataSource = SectionsDataSource
    fun getAllSections(): List<String> {
        return dataSource.getSections()
    }

    fun getSectionTopics(section: String): List<String> {
        return dataSource.getSectionTopics(section)
    }

    fun getTopicVideo(topic: String): String? {
        return dataSource.getVideoByTopic(topic)
    }
}