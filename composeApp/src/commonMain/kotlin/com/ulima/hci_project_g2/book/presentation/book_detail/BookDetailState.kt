package com.ulima.hci_project_g2.book.presentation.book_detail

import com.ulima.hci_project_g2.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
