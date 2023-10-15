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

import edu.asu.ser421.booktown.api.modelhelpers.BookResponse;

@RestControllerAdvice
public class ListBooksResponseBodyAdvice implements ResponseBodyAdvice<List<BookResponse>> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String controllerClassName = returnType.getContainingClass().toString();
		String returnTypeName = returnType.getMethod().getGenericReturnType().getTypeName();
		System.out.println("Controller Class: " + controllerClassName + ", Return Type Class: " + returnTypeName);
        return returnTypeName.contains("java.util.List") && returnTypeName.contains("BookResponse");
	}

	@Override
	public List<BookResponse> beforeBodyWrite(List<BookResponse> body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		WebMvcLinkBuilder bookLink;
		for (BookResponse b : body) {
			 bookLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(b.getIsbn());
			 b.setLink(bookLink.toString());
			// this gets us the link to the Author
			WebMvcLinkBuilder authorLink = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(b.getAuthorId());
			b.setAuthorLink(authorLink.toString());
		}
		return body;
	}


}
