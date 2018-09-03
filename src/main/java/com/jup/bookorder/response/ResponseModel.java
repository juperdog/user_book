package com.jup.bookorder.response;

import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseModel {
	protected Response message;
	protected Object data = "";

	public ResponseModel(Response response, Object data) {
		this.message = response;
		this.data = data;
	}
	
	public HttpEntity<ResponseModel> build(HttpStatus status) {
		return new ResponseEntity<>(this, status);
	}


}
