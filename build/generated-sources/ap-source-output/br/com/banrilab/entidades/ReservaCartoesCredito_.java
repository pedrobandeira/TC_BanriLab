package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaCartoesCredito.class)
public abstract class ReservaCartoesCredito_ {

	public static volatile SingularAttribute<ReservaCartoesCredito, Long> id;
	public static volatile SingularAttribute<ReservaCartoesCredito, Date> dataFim;
	public static volatile SingularAttribute<ReservaCartoesCredito, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaCartoesCredito, String> finalidade;
	public static volatile SingularAttribute<ReservaCartoesCredito, Date> dataInicio;
	public static volatile SingularAttribute<ReservaCartoesCredito, ReservaUsuarios> testador;
	public static volatile SingularAttribute<ReservaCartoesCredito, CartoesCredito> cartao;
	public static volatile SingularAttribute<ReservaCartoesCredito, Usuarios> dono;

}

