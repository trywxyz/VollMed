package medi.voll.api.paciente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @PostMapping
    public void CadastrarPaciente(@RequestBody DadosPaciente dados){

        System.out.println("Dados do paciente recebido: "+ dados);

    }
}
