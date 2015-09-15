package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoHomologacaoCiclos.class)
public abstract class HistoricoHomologacaoCiclos_ {

	public static volatile SingularAttribute<HistoricoHomologacaoCiclos, Long> id;
	public static volatile SingularAttribute<HistoricoHomologacaoCiclos, Homologacoes> homologacao;
	public static volatile SingularAttribute<HistoricoHomologacaoCiclos, Date> dataFimCiclo;
	public static volatile SingularAttribute<HistoricoHomologacaoCiclos, Integer> ciclo;

}

