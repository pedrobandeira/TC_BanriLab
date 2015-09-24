package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoReservaTerminais.class)
public abstract class HistoricoReservaTerminais_ {

	public static volatile SingularAttribute<HistoricoReservaTerminais, Long> id;
	public static volatile SingularAttribute<HistoricoReservaTerminais, Homologacoes> homologacao;
	public static volatile SingularAttribute<HistoricoReservaTerminais, String> terminal;
	public static volatile SingularAttribute<HistoricoReservaTerminais, String> testador;

}

