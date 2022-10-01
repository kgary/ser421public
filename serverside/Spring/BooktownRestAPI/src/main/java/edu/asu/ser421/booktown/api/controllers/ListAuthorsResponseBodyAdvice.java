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
public class ListAuthorsResponseBodyAdvice implements ResponseBodyAdvice<List<AuthorResponse>> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String controllerClassName = returnType.getContainingClass().toString();
		String returnTypeName = returnType.getMethod().getGenericReturnType().getTypeName();
		System.out.println("Controller Class: " + controllerClassName + ", Return Type Class: " + returnTypeName);
        return returnTypeName.contains("java.util.List") && returnTypeName.contains("AuthorResponse");
	}

	@Override
	public List<AuthorResponse> beforeBodyWrite(List<AuthorResponse> body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		WebMvcLinkBuilder authorLink;
		for (AuthorResponse a : body) {
			 authorLink = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(a.getAuthorID());
			 a.setLink(authorLink.toString());
			 
			 // this section gets us links to the Books, which go in Authors
			 List<BookLink> bookLinks = a.getBookLinks();
			 for (BookLink bl : bookLinks) {
				 // the difference here is nobody has set the URL links yet, we need to do that now
				 String s = WebMvcLinkBuilder.linkTo(BookController.class).slash(bl.getIsbn()).toString();
				 System.out.println("BL ISBN: " + bl.getIsbn() + " Creating Book Link " + s + " for Author: " + a.getAuthorID());
				 bl.setLink(s);
			 }
			 a.setBookLinks(bookLinks);
		}
		return body;
	}


}
