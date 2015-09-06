package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaCartoesContas.class)
public abstract class ReservaCartoesContas_ {

	public static volatile SingularAttribute<ReservaCartoesContas, Long> id;
	public static volatile SingularAttribute<ReservaCartoesContas, Date> dataFim;
	public static volatile SingularAttribute<ReservaCartoesContas, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaCartoesContas, String> finalidade;
	public static volatile SingularAttribute<ReservaCartoesContas, Date> dataInicio;
	public static volatile SingularAttribute<ReservaCartoesContas, ReservaUsuarios> testador;
	public static volatile SingularAttribute<ReservaCartoesContas, CartoesContas> cartao;
	public static volatile SingularAttribute<ReservaCartoesContas, Usuarios> dono;

}

