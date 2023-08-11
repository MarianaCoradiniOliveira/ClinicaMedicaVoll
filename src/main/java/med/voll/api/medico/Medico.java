package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor //gerar automaticamente um construtor sem argumentos para uma classe. oferece recursos para reduzir a verbosidade do código, evitando a repetição de código comum.
@AllArgsConstructor //gerar automaticamente um construtor com todos os argumentos para uma classe.
@EqualsAndHashCode(of = "id") // permite gerar automaticamente os métodos equals() e hashCode() para uma classe. Esses métodos são usados para comparar objetos quanto à igualdade e para calcular o código hash dos objetos, respectivamente.
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

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

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco()); //atualiza cada um dos campos que podem ser atualizados
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
