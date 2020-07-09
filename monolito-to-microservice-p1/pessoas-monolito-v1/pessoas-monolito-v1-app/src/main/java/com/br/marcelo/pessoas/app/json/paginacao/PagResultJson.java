package com.br.marcelo.pessoas.app.json.paginacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagResultJson {

	public static final int NRO_TOTAL_REGISTROS = 50;
	
	private List registros;
	private int tamanho;
	private int totalPaginas;
	private int pos;
	private long totalRegistros;

	
}
