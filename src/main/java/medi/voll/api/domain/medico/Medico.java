package medi.voll.api.domain.medico;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.voll.api.domain.endereco.Endereco;

//DTO <Data Tranfer Object>
//JPA <Java Persistence API>

@Table(name = "medicos") //CRIAÇÃO DA TABELA 'medicos'
@Entity(name = "Medico") //MARCANDO ESSA CLASSE COMO A ENTIDADE DE 'Medico'
@Getter //ISSO É ANOTAÇÃO LOMBOK PARA GERAR AUTOMATICAMENTE OS GETTERS E SETTER
@NoArgsConstructor //GERAR O CONSTRUTOR DEFAULT DO JAVA SEM ARGUMENTO
@AllArgsConstructor //TER UM CONSTRUTOR QUE RECEBE TODOS OS CAMPOS
@EqualsAndHashCode(of = "id")
public class Medico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTIFICA QUE O 'Long id' É UMA PRIMARY KEY
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING) //É NECESSARIO DECLARAR @Enumated emcima do tipo ENUM IDENTIFICANDO QUE O ENUM VAI SER DO TIPO STRING
    private Especialidade especialidade;

    // É UM TIPO DE RELACIONAMENTO DE BANCO DE DADOS COM O @Embeddable
    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoCadastroMedico dados) {
        if(dados.nome()!= null){
            this.nome = dados.nome();
        }
        if(dados.telefone()!= null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
