package medi.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import medi.voll.api.endereco.DadosEnderecoMedico;

public record DadosAtualizacaoCadastroMedico(
        @NotBlank
        Long id,
        String nome,
        String telefone,
        DadosEnderecoMedico endereco) {
}
