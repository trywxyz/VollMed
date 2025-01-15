package medi.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    //CRIA UM ATRIBUTO DE REPOSITORY
    @Autowired //SPRING IDENTIFICA QUE É ELE QUE VAI INSTANCIAR O REPOSITORY NA NOSSA CLASSE CONTROLLER
    private MedicoRepository repository;

    @PostMapping //@PostMapping VAI RECEBER UM POST DE ALGUM LUGAR
    //@ResquestBody DIZ PARA O 'String json' QUE O FORMATO QUE IRA RECEBER É FORMATO JSON
    //COMO NÃO É POSSIVEL ESCOLHER CADA DADO DO JSON TROCAMOS O JSON POR UMA CLASSE CHAMADA DadosCadastroMedico com a variavel 'dados'
    //ESSE PADRÃO DE MANIPULAÇÃO DE DADOS SE CHAMA DTO DATA TRANSFER OBJECT
    @Transactional //COMO ESSE METODO É DE ESCRITA ENTÃO PRECISA FAZER UMA TRANSAÇÃO ATIVA COM BANCO DE DADOS
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCadastroMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }


}
