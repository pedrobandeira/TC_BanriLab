package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servidores.class)
public abstract class Servidores_ {

	public static volatile SingularAttribute<Servidores, Long> id;
	public static volatile SingularAttribute<Servidores, Boolean> reservavel;
	public static volatile SingularAttribute<Servidores, Integer> patrimonio;
	public static volatile SingularAttribute<Servidores, String> nome;
	public static volatile SingularAttribute<Servidores, Boolean> disponivel;
	public static volatile SingularAttribute<Servidores, String> modelo;
	public static volatile SingularAttribute<Servidores, String> descricao;

}

