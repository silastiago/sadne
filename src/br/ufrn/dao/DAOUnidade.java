package br.ufrn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import br.ufrn.conexao.ConnectionFactory;
import br.ufrn.model.Municipio;
import br.ufrn.model.Unidade;
import br.ufrn.util.SessionUtil;

public class DAOUnidade {
	private Connection conexao;

	public DAOUnidade(){
		this.conexao = new ConnectionFactory().getConnection();
	}

	public void adicionarUnidade(Unidade unidade){
		Session sessao = SessionUtil.getSession();
		Transaction tx = sessao.beginTransaction();
		adicionarUnidade(sessao, unidade);
		tx.commit();
		sessao.close();
	}
	
	public void adicionarUnidade(Session sessao, Unidade unidade){
		Unidade unidade2 = new Unidade();
		unidade2.setNome(unidade.getNome());
		unidade2.setMunicipio(unidade.getMunicipio());
		sessao.save(unidade2);
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> listarUnidades(){
		Session sessao = SessionUtil.getSession();
		//Query query = sessao.createQuery("from municipio");
		List<Unidade> unidades = sessao.createCriteria(Unidade.class).addOrder(Order.asc("nome")).list();
		return unidades;
	}

	public Unidade listarUnidade(String codigo){
		Session sessao = SessionUtil.getSession();
		Unidade unidade = (Unidade) sessao.get(Unidade.class, codigo);
		return unidade;
	}
	
	public void atualizarMunicipio(Unidade unidade){
		Session sessao = SessionUtil.getSession();
		sessao.update(unidade);
	}
	
	public void deletarMunicipio(Unidade unidade){
		Session sessao = SessionUtil.getSession();
		sessao.delete(unidade);
	}
	
}