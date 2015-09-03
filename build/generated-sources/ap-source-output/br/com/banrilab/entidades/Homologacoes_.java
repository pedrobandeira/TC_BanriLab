package br.com.banrilab.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Homologacoes.class)
public abstract class Homologacoes_ {

	public static volatile SingularAttribute<Homologacoes, String> versaoSistema;
	public static volatile ListAttribute<Homologacoes, ReservaTerminais> reservasTerminais;
	public static volatile SingularAttribute<Homologacoes, Integer> status;
	public static volatile ListAttribute<Homologacoes, ReservaUsuarios> reservasTestadores;
	public static volatile SingularAttribute<Homologacoes, Usuarios> solicitante;
	public static volatile SingularAttribute<Homologacoes, Date> dataAutorizacao;
	public static volatile SingularAttribute<Homologacoes, Usuarios> autorizador;
	public static volatile SingularAttribute<Homologacoes, Date> dataAbertura;
	public static volatile SingularAttribute<Homologacoes, String> requisitos;
	public static volatile ListAttribute<Homologacoes, ReservaCartoesContas> reservasCartoesContas;
	public static volatile SingularAttribute<Homologacoes, String> observacoes;
	public static volatile SingularAttribute<Homologacoes, Date> dataSolicitacao;
	public static volatile SingularAttribute<Homologacoes, Long> id;
	public static volatile SingularAttribute<Homologacoes, Sistemas> sistema;
	public static volatile SingularAttribute<Homologacoes, Date> dataFim;
	public static volatile ListAttribute<Homologacoes, ReservaCartoesCredito> reservasCartoesCreditos;
	public static volatile ListAttribute<Homologacoes, ReservaAtms> reservasAtms;
	public static volatile SingularAttribute<Homologacoes, Integer> ciclo;
	public static volatile SingularAttribute<Homologacoes, Date> dataInicioExecucao;
	public static volatile ListAttribute<Homologacoes, ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais;
	public static volatile SingularAttribute<Homologacoes, Usuarios> analista;
	public static volatile ListAttribute<Homologacoes, ReservaServidores> reservasServidores;

}

