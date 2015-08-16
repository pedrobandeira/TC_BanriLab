package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaTerminais.class)
public abstract class ReservaTerminais_ {

	public static volatile SingularAttribute<ReservaTerminais, Long> id;
	public static volatile SingularAttribute<ReservaTerminais, Date> dataFim;
	public static volatile SingularAttribute<ReservaTerminais, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaTerminais, Terminais> terminal;
	public static volatile SingularAttribute<ReservaTerminais, String> finalidade;
	public static volatile SingularAttribute<ReservaTerminais, Date> dataInicio;
	public static volatile SingularAttribute<ReservaTerminais, Usuarios> dono;

}

