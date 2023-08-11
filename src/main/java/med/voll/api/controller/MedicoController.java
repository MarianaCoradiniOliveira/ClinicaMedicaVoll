package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//REPOSITORY ACESSA O BANCO DE DADOS
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //é o Spring que instacia e passa o paramentro dentro da classe controller
    private MedicoRepository repository; // injetando o reposity como sendo um atributo

    @PostMapping //"postando" uma informação
    @Transactional //transação ativa com o banco de dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){ //json usa o @RequestBody pra mostrar na tela oq é enviado do postman
        repository.save(new Medico(dados));
    }

    @GetMapping //"devolvendo" uma informação
    //nao é necessario o @Transactional pois é apenas um método de leitura
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); //conversção: converter de médico para DadosListagemMedico e converter para uma lista,
        // chama o contrtutor da classe DadosListagemMedico
    }

    @PutMapping //editando uma informação
    @Transactional //transação ativa com o banco de dados
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){ //quando o método de atualização for chamado, vai ser convertido para o DTO
        var medico = repository.getReferenceById(dados.id()); // precisa carregar as informaçõea atuais
        medico.atualizarInformacoes(dados); //sobrescreve de acordo com as atualizações
    }

    @Transactional //transação ativa com o banco de dados
    @DeleteMapping("/{id}") //parametro dinâmico
    public void excluir(@PathVariable Long id){ //@PathVariable entende que é o mesmo ID do @DeleteMapping("/{id}")
       //carregar a entidade do banco de dados, inativa-la, e disparar o update no banco de dados
        var medico = repository.getReferenceById(id); // precisa carregar as informaçõea atuais
        medico.excluir();
    }
}
