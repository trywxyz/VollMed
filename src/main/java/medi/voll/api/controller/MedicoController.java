package medi.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);

        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //TRANSFORMA EM FORMATO JSON PARA RECEBER O ENDEREÇO CORRETAMENTE

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCadastroMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


}
