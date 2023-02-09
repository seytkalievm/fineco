package com.example.fineco.data.datasource

import android.util.Log
import com.example.fineco.data.model.Section
import com.example.fineco.data.model.Topic

object SectionsDataSource{
    private val topics = List(25) { i -> Topic("Тема $i", "video $i") }
    private val sections = List(5) { i -> Section("Раздел $i", topics.subList(i*5, i*5 + 5)) }

    private val topicBySection = mapOf(
        "Раздел 0" to sections[0],
        "Раздел 1" to sections[1],
        "Раздел 2" to sections[2],
        "Раздел 3" to sections[3],
        "Раздел 4" to sections[4]
    )

    private val videosByTopic = mutableMapOf<String, String>().apply {
        for (i in 0..25) {
            this["Тема $i"] = "Видео $i"
        }
    }

    fun getSections(): List<String> {
        return sections.map { it.title }
    }

    fun getSectionTopics(section: String): List<String> {
        return topicBySection[section]?.topics?.map { it.title } ?: emptyList()
    }

    fun getVideoByTopic(topic: String): String? {
        Log.i("SectionsDataSource", "getVideoByTopic: ${videosByTopic.entries}")
        return videosByTopic[topic]
    }

}



