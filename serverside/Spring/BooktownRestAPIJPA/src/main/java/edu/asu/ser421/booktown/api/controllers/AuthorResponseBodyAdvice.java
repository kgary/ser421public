package edu.asu.ser421.booktown.api.controllers;


import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import edu.asu.ser421.booktown.api.modelhelpers.AuthorResponse;
import edu.asu.ser421.booktown.api.modelhelpers.BookLink;

@RestControllerAdvice
public class AuthorResponseBodyAdvice implements ResponseBodyAdvice<AuthorResponse> {
	@Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String controllerClassName = returnType.getContainingClass().toString();
		String returnTypeName = returnType.getMethod().getGenericReturnType().getTypeName();
		System.out.println("Controller Class: " + controllerClassName + ", Return Type Class: " + returnTypeName);
        return returnTypeName.contains("ResponseEntity<edu.asu.ser421.booktown.api.modelhelpers.AuthorResponse>");
    }

	@Override
	public AuthorResponse beforeBodyWrite(AuthorResponse body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// this gets us the link to the Author
		WebMvcLinkBuilder authorLink = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(body.getAuthorID());
		// this section gets us links to the Books, which go in Authors
		List<BookLink> bookLinks = body.getBookLinks();
		for (BookLink bl : bookLinks) {
			// the difference here is nobody has set the URL links yet, we need to do that now
			String s = WebMvcLinkBuilder.linkTo(BookController.class).slash(bl.getIsbn()).toString();
			System.out.println("Creating Book Link: " + s);
			bl.setLink(s);
		}
		body.setBookLinks(bookLinks);
		// We don't need to assign bl back in to AuthrResponse since it is an Object reference
		
		// Now the Author URL
		// We need to set the location header if we are handling a POST
		if (request.getMethod().matches("POST")) {
			response.getHeaders().add("Location", authorLink.toString());
		}
		body.setLink(authorLink.toString());
		
		return body;
	}
}
