package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Terminais.class)
public abstract class Terminais_ {

	public static volatile SingularAttribute<Terminais, Long> id;
	public static volatile SingularAttribute<Terminais, Boolean> reservavel;
	public static volatile SingularAttribute<Terminais, Integer> patrimonio;
	public static volatile SingularAttribute<Terminais, String> nome;
	public static volatile SingularAttribute<Terminais, Boolean> disponivel;
	public static volatile SingularAttribute<Terminais, ReservaTerminais> reserva;
	public static volatile SingularAttribute<Terminais, String> descricao;

}

