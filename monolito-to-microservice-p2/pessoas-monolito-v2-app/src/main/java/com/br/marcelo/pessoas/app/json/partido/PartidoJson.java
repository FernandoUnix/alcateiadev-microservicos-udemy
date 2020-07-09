package com.br.marcelo.pessoas.app.json.partido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoJson {

	private Long id;
	
	@NotEmpty(message="Sigla é obrigatória")
	@NotNull(message="Sigla é obrigatória")
	@Size(max = 10, message = "Código deve conter no máximo 10 caracteres.")
	private String sigla;
	
	@NotEmpty(message="Descricao é obrigatória")
	@NotNull(message="Descricao é obrigatória")
	@Size(max = 1000, message = "Descrição deve conter no máximo 1000 caracteres.")
	private String descricao;

	private String codigo;
	private String nome;

}
