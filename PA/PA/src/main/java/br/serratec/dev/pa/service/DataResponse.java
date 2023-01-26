package br.serratec.dev.pa.service;

public class DataResponse<E> {
	private E data;
	private Boolean success;

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	

}
