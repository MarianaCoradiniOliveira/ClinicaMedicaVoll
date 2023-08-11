package med.voll.api.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade()); //tem que chamar o construtor principal que esta declarado na assinatura do record, passando
        //cada um dos campos que foi definido
    }
}
