package medi.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//COMO EU TENHO VARIOS DADOS DENTRO DE ENDEREÇO PARA NÃO DEIXAR ESSES DADOS JOGADOS FAZENDO UM RECORDO DENTRO DO RECORD PARA CONSEGUIR SE IDENTIFICAR
public record DadosEndereco(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String complemento,

        String numero) {


}
