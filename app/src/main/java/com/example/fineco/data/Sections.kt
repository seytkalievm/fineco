package com.example.fineco.data



object Sections {
    private val topics = List(25) { i -> Topic("Тема $i", "video $i") }
    private val sections = List(5) { i -> Section("Раздел $i", topics.subList(i*5, i*5 + 5))}

    fun getSections(): List<String> {
        return mutableListOf<String>().apply {
            sections.forEach {
                this.add(it.title)
            }
        }
    }

    fun getSectionTopics(id: Int): List<Topic> {
        return sections[id].topics
    }

}


data class Section(
    val title: String,
    val topics: List<Topic>
)

data class Topic(
    val title: String,
    val video: String,
)