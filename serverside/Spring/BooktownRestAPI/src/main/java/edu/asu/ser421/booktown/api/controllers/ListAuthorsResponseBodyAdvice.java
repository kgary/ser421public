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

import edu.asu.ser421.booktown.api.modelhelpers.AuthorLink;

@RestControllerAdvice
public class ListAuthorsResponseBodyAdvice implements ResponseBodyAdvice<List<AuthorLink>> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String controllerClassName = returnType.getContainingClass().toString();
		String returnTypeName = returnType.getMethod().getGenericReturnType().getTypeName();
		System.out.println("Controller Class: " + controllerClassName + ", Return Type Class: " + returnTypeName);
        return returnTypeName.contains("java.util.List") && returnTypeName.contains("AuthorLink");
	}

	@Override
	public List<AuthorLink> beforeBodyWrite(List<AuthorLink> body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		WebMvcLinkBuilder authorLink;
		for (AuthorLink a : body) {
			 authorLink = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(a.getAuthorID());
			 a.setLink(authorLink.toString());
		}
		return body;
	}


}
