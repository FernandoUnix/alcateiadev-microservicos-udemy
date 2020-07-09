package com.br.marcelo.pessoas.json.pessoafisica;


import com.br.marcelo.pessoas.json.evento.EventoJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaEventoJson {

	private Long id;
	private Long idPessoaJuridica;
	private EventoJson evento;



}
