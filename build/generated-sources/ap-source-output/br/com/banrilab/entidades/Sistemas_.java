package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sistemas.class)
public abstract class Sistemas_ {

	public static volatile SingularAttribute<Sistemas, Long> id;
	public static volatile SingularAttribute<Sistemas, Usuarios> responsavel;
	public static volatile SingularAttribute<Sistemas, String> nome;
	public static volatile SingularAttribute<Sistemas, String> versao;
	public static volatile SingularAttribute<Sistemas, String> descricao;

}

