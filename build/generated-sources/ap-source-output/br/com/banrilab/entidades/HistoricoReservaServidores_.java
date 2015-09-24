package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoReservaServidores.class)
public abstract class HistoricoReservaServidores_ {

	public static volatile SingularAttribute<HistoricoReservaServidores, Long> id;
	public static volatile SingularAttribute<HistoricoReservaServidores, String> servidor;
	public static volatile SingularAttribute<HistoricoReservaServidores, Homologacoes> homologacao;
	public static volatile SingularAttribute<HistoricoReservaServidores, String> testador;

}

