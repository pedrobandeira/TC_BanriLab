package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CartoesContas.class)
public abstract class CartoesContas_ {

	public static volatile SingularAttribute<CartoesContas, Long> id;
	public static volatile SingularAttribute<CartoesContas, String> agencia;
	public static volatile SingularAttribute<CartoesContas, Boolean> reservavel;
	public static volatile SingularAttribute<CartoesContas, String> conta;
	public static volatile SingularAttribute<CartoesContas, String> nome;
	public static volatile SingularAttribute<CartoesContas, Boolean> disponivel;

}

