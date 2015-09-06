package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaEquipamentosAdicionais.class)
public abstract class ReservaEquipamentosAdicionais_ {

	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, Long> id;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, Date> dataFim;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, String> finalidade;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, Date> dataInicio;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, ReservaUsuarios> testador;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, Usuarios> dono;
	public static volatile SingularAttribute<ReservaEquipamentosAdicionais, EquipamentosAdicionais> equipamento;

}

