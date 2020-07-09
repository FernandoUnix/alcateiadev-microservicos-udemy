package com.br.marcelo.pessoas.app.json.pessoafisica;

import com.br.marcelo.pessoas.app.json.grupo.GrupoJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaGrupoJson {

    private Long id;
    private Long idPessoaJuridica;
    private GrupoJson grupo;


}
