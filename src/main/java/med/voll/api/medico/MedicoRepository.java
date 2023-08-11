package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); // o primeiro objeto é qual o tipo da entidade que este Repository vai trabalhar(Medico), e o segundo é qual o tipo de atributo da chave primária dessa entidade(Long)

}
