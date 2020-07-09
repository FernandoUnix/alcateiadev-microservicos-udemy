package com.br.marcelo.pessoas.app.json.pessoafisica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoJson {

	@NotNull(message = "Código é obrigatório")
	private Long id;
	private String codigo;
	private String nome;


}
