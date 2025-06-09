package com.ulima.hci_project_g2.book.presentation.book_list

import com.ulima.hci_project_g2.book.domain.Book
import com.ulima.hci_project_g2.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)