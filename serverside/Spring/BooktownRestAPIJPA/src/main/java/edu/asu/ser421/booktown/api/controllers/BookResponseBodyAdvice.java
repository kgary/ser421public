package edu.asu.ser421.booktown.api.controllers;


import org.springframework.core.MethodParameter;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import edu.asu.ser421.booktown.api.modelhelpers.BookResponse;

@RestControllerAdvice
public class BookResponseBodyAdvice implements ResponseBodyAdvice<BookResponse> {
	@Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String controllerClassName = returnType.getContainingClass().toString();
		String returnTypeName = returnType.getMethod().getGenericReturnType().getTypeName();
		System.out.println("Controller Class: " + controllerClassName + ", Return Type Class: " + returnTypeName);
        return returnTypeName.contains("ResponseEntity<edu.asu.ser421.booktown.api.modelhelpers.BookResponse>");
    }

	@Override
	public BookResponse beforeBodyWrite(BookResponse body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// this gets us the link to the Author
		WebMvcLinkBuilder authorLink = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(body.getAuthorId());
		// this gets us the link to the Book
		WebMvcLinkBuilder bookLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(body.getIsbn());
		System.out.println("Creating Book Link: " + bookLink.toString());
		body.setLink(bookLink.toString());
		body.setAuthorLink(authorLink.toString());
		
		return body;
	}
}
