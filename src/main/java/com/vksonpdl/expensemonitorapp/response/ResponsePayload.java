package com.vksonpdl.expensemonitorapp.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePayload implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	private Map<String, Long> tableSize;
	private Date responseDate;
	

}
