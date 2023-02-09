package com.example.fineco.ui.topics

import androidx.lifecycle.ViewModel
import com.example.fineco.data.repository.SectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val repo: SectionsRepository
): ViewModel() {

    fun getTopics(section: String): List<String> {
        return repo.getSectionTopics(section)
    }
}