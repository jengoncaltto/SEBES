package br.uniriotec.prae.sebes.Repositorio;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.Entity.RespostaFormulario;

@Repository
public interface RespostaFormularioRepository extends JpaRepository<RespostaFormulario, String>{
	
	List<RespostaFormulario> findAllByStatus(String status);
	
	List<RespostaFormulario> findAllByTipoForms(String tipoForms);
	
	List<RespostaFormulario> findAllByIdUsuario(String idUsuario);
	
	List<RespostaFormulario> findAllByIdProcessoSeletivo(String idProcessoSeletivo);
	
	List<RespostaFormulario> findAllByDataEnvio(LocalDateTime dataEnvio);
	
	List<RespostaFormulario> findAllByIdRespostaAssociada(String idRespostaAssociada);

}
