package com.hdtec.ecommercejava11springboot.model.enums;

public enum ClientType {

	PHYSICALPERSON(1, "Physical Person"),
	LEGALPERSON(2, "Legal Person");

	private int cod;
	private String description;

	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (ClientType x : ClientType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}