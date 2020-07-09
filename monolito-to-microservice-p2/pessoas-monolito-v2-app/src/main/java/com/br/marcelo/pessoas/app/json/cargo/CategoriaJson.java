package com.br.marcelo.pessoas.app.json.cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaJson {

	private Long id;

	@NotNull
	private String nome;

	
}
