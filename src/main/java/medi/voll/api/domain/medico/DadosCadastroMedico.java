package medi.voll.api.domain.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.voll.api.domain.endereco.DadosEndereco;

//NOSSO RECORD SEMPRE APENAS PARA MAPEAR CADA ELEMENTO JSON OU SEJA 'nome, cep, endereco, idade' DE MEDICO
public record DadosCadastroMedico(


        @NotBlank //VERIFICA SE NÃO É NULO OU VAZIO SERVE APENAS PARA STRINGS
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //VERIFICA SE O NÚMERO E DE 4 A 6 NUMEROS
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid //ELE VALIDA QUE O DTO EXISTE OUTRO DTO DENTRO
        DadosEndereco endereco) {
}
