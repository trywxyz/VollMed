package medi.voll.api.controller;


import jakarta.validation.Valid;
import medi.voll.api.infra.security.DadosToken;
import medi.voll.api.infra.security.TokenService;
import medi.voll.api.user.DadosAutenticao;
import medi.voll.api.user.User;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticao dados){
        var Authtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(Authtoken);
        var tokenJWT = tokenService.gerarToken((User)authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));


    }
}
