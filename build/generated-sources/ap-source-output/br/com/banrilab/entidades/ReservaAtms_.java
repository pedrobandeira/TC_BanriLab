package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservaAtms.class)
public abstract class ReservaAtms_ {

	public static volatile SingularAttribute<ReservaAtms, Atms> atm;
	public static volatile SingularAttribute<ReservaAtms, Long> id;
	public static volatile SingularAttribute<ReservaAtms, Date> dataFim;
	public static volatile SingularAttribute<ReservaAtms, Homologacoes> homologacao;
	public static volatile SingularAttribute<ReservaAtms, String> finalidade;
	public static volatile SingularAttribute<ReservaAtms, Date> dataInicio;
	public static volatile SingularAttribute<ReservaAtms, ReservaUsuarios> testador;
	public static volatile SingularAttribute<ReservaAtms, Usuarios> dono;

}

