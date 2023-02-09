package com.example.fineco.ui.topic

import androidx.lifecycle.ViewModel
import com.example.fineco.data.repository.SectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicVideoViewModel @Inject constructor(
    private val repo: SectionsRepository
) : ViewModel() {

    fun getVideoByTopic(topic: String): String? {
        return repo.getTopicVideo(topic)
    }

}