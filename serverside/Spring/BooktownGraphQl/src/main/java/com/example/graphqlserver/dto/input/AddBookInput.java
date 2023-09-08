package com.example.graphqlserver.dto.input;

public record AddBookInput(String isbn, String title, int authorId) {
}
