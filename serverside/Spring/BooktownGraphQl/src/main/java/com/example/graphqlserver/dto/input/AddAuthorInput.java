package com.example.graphqlserver.dto.input;

import com.example.graphqlserver.model.Book;

import java.util.List;

public record AddAuthorInput(String firstName, String lastName) {
}
