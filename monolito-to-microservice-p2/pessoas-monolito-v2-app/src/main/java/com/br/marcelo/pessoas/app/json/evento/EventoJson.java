package com.br.marcelo.pessoas.app.json.evento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoJson {

	private Long id;

	@NotNull(message = "Código do grupo é obrigatório")
	@NotEmpty(message = "Código do grupo é obrigatório")
	@Size(max = 5, message = "Código deve conter no máximo 5 caracteres.")
	private String codigo;

	@NotNull(message = "Nome do grupo é obrigatório")
	@NotEmpty(message = "Nome do grupo é obrigatório")
	@Size(max = 1000, message = "Código deve conter no máximo 1000 caracteres.")
	private String nome;
	private String comentario;


}
