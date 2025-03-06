package com.example.taskapp.data.implementation

import com.example.taskapp.domain.TitleFormatter

class TitleFormatterImpl : TitleFormatter {
    override fun getCorrectTitle(title: String) = title.replace(Regex("\\s+"), " ").trim()
}