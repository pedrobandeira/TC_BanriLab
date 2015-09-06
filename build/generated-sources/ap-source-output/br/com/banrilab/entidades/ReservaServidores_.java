package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaServidores.class)
public abstract class ReservaServidores_ {

	public static volatile SingularAttribute<ReservaServidores, Long> id;
	public static volatile SingularAttribute<ReservaServidores, Date> dataFim;
	public static volatile SingularAttribute<ReservaServidores, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaServidores, Servidores> servidor;
	public static volatile SingularAttribute<ReservaServidores, String> finalidade;
	public static volatile SingularAttribute<ReservaServidores, Date> dataInicio;
	public static volatile SingularAttribute<ReservaServidores, ReservaUsuarios> testador;
	public static volatile SingularAttribute<ReservaServidores, Usuarios> dono;

}

