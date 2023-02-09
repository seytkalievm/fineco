package com.example.fineco.ui.sections

import androidx.lifecycle.ViewModel
import com.example.fineco.data.repository.SectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val repo: SectionsRepository
): ViewModel() {
    fun getSections(): List<String> {
        return repo.getAllSections()
    }
}