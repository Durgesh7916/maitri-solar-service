package com.msedcl.main.dto;

public class ApiResponse {

    private String status;
    private String message;

    // getters setters
    
    public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", message=" + message + "]";
	}
    
}