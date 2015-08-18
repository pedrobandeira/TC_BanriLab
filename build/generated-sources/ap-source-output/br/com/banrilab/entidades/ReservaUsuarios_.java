package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaUsuarios.class)
public abstract class ReservaUsuarios_ {

	public static volatile SingularAttribute<ReservaUsuarios, Long> id;
	public static volatile SingularAttribute<ReservaUsuarios, Date> dataFim;
	public static volatile SingularAttribute<ReservaUsuarios, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaUsuarios, Usuarios> usuario;
	public static volatile SingularAttribute<ReservaUsuarios, Date> dataInicio;

}

