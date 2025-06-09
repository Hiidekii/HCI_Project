package com.ulima.hci_project_g2.book.data.network

import com.ulima.hci_project_g2.book.data.dto.BookWorkDto
import com.ulima.hci_project_g2.book.data.dto.SearchResponseDto
import com.ulima.hci_project_g2.core.domain.DataError
import com.ulima.hci_project_g2.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}