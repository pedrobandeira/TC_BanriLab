package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Atms.class)
public abstract class Atms_ {

	public static volatile SingularAttribute<Atms, Boolean> talonadora;
	public static volatile SingularAttribute<Atms, Long> id;
	public static volatile SingularAttribute<Atms, Boolean> reservavel;
	public static volatile SingularAttribute<Atms, Integer> patrimonio;
	public static volatile SingularAttribute<Atms, Boolean> depositario;
	public static volatile SingularAttribute<Atms, String> nome;
	public static volatile SingularAttribute<Atms, Boolean> disponivel;
	public static volatile SingularAttribute<Atms, String> modelo;
	public static volatile SingularAttribute<Atms, ReservaAtms> reserva;
	public static volatile SingularAttribute<Atms, String> descricao;

}

