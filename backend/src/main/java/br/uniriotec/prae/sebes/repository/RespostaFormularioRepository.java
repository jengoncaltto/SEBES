package br.uniriotec.prae.sebes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.RespostaFormulario;

@Repository
public interface RespostaFormularioRepository extends JpaRepository<RespostaFormulario, String> {

    List<RespostaFormulario> findAllByStatus(String status);

    List<RespostaFormulario> findAllByTipoForms(String tipoForms);

    List<RespostaFormulario> findAllByIdUsuario(String idUsuario);

    List<RespostaFormulario> findAllByIdProcessoSeletivo(String idProcessoSeletivo);

    List<RespostaFormulario> findAllByDataEnvio(LocalDateTime dataEnvio);

    List<RespostaFormulario> findAllByIdRespostaAssociada(String idRespostaAssociada);

    List<RespostaFormulario> findAllByTipoFormsAndIdProcessoSeletivo(String tipoForms, String idProcessoSeletivo);

    List<RespostaFormulario> findAllByTipoFormsAndIdRespostaAssociada(String tipoForms, String idRespostaAssociada);

    List<RespostaFormulario> findAllByTipoFormsAndIdProcessoSeletivoAndStatus(String tipoForms, String idProcessoSeletivo, String status);

    List<RespostaFormulario> findAllByTipoFormsAndIdRespostaAssociadaAndStatus(String tipoForms, String idRespostaAssociada, String status);

    List<RespostaFormulario> findAllByTipoFormsAndIdUsuario(String tipoForms, String idUsuario);

    List<RespostaFormulario> findAllByTipoFormsAndIdUsuarioAndIdProcessoSeletivo(String tipoForms, String idUsuario, String idProcessoSeletivo);
}
