package com.br.marcelo.pessoas.app.json.cargo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoJson {

	private Long id;
	
	@NotEmpty(message="Código é obrigatório")
	@NotNull(message="Código é obrigatório")
	@Size(max = 5, message = "Código deve conter no máximo 5 caracteres.")
	private String codigo;
	
	@NotEmpty(message="Nome é obrigatório")
	@NotNull(message="Nome é obrigatório")
	@Size(max = 1000, message = "Nome deve conter no máximo 1000 caracteres.")
	private String nome;
	
	@NotNull(message="Categoria é obrigatório")
	private Long categoria;
	private String nomeCategoria;
	
	private Long cargoPai;
	private String cargoPaiCodigo;
	private String cargoPaiNome;


	
}
