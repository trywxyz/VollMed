package medi.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // CRIA A CONEXÃO DE RELACIONAMENTO COM A CLASSE MEDICO QUE TEM O @Embedded
@Getter //ISSO É ANOTAÇÃO LOMBOK PARA GERAR AUTOMATICAMENTE OS GETTERS E SETTER
@NoArgsConstructor //GERAR O CONSTRUTOR DEFAULT DO JAVA SEM ARGUMENTO
@AllArgsConstructor //TER UM CONSTRUTOR QUE RECEBE TODOS OS CAMPOS
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEnderecoMedico dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();

    }

    public void atualizarInformacoes(DadosEnderecoMedico dados) {
        if (dados.logradouro() != null) {
            this.logradouro = logradouro;

        }
        if (dados.bairro() != null) {
            this.bairro = bairro;
        }
        if (dados.cep() != null) {
            this.cep = cep;
        }
        if (dados.uf() != null) {
            this.uf = uf;
        }
        if (dados.cidade() != null) {
            this.cidade = cidade;
        }
        if (dados.numero() != null) {
            this.numero = numero;
        }
        if (dados.complemento() != null) {
            this.complemento = complemento;
        }
    }
}
