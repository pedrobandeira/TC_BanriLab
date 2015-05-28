package br.com.banrilab.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuarios.class)
public abstract class Usuarios_ {

	public static volatile SingularAttribute<Usuarios, Long> id;
	public static volatile SingularAttribute<Usuarios, String> email;
	public static volatile SingularAttribute<Usuarios, Integer> perfil;
	public static volatile SingularAttribute<Usuarios, String> nome;
	public static volatile SingularAttribute<Usuarios, String> senha;
	public static volatile SingularAttribute<Usuarios, String> matricula;

}

