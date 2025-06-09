package com.ulima.hci_project_g2.book.presentation.book_detail

import com.ulima.hci_project_g2.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book): BookDetailAction
}