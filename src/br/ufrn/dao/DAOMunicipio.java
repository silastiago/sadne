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
import br.ufrn.util.SessionUtil;

public class DAOMunicipio {
	private Connection conexao;

	public DAOMunicipio(){
		this.conexao = new ConnectionFactory().getConnection();
	}

	public void adicionarMunicipio(Municipio municipio){
		Session sessao = SessionUtil.getSession();
		Transaction tx = sessao.beginTransaction();
		adicionarMunicipio(sessao, municipio);
		tx.commit();
		sessao.close();
	}
	
	public void adicionarMunicipio(Session sessao, Municipio municipio){
		Municipio cidade = new Municipio();
		cidade.setNome(municipio.getNome());
		sessao.save(cidade);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Municipio> listarMuncipios(){
		Session sessao = SessionUtil.getSession();
		//Query query = sessao.createQuery("from municipio");
		List<Municipio> municipios = sessao.createCriteria(Municipio.class).addOrder(Order.asc("nome")).list();
		return municipios;
	}

	public Municipio listarMuncipio(String codigo){
		Session sessao = SessionUtil.getSession();
		Municipio cidade = (Municipio) sessao.get(Municipio.class, codigo);
		return cidade;
	}
	
	public void atualizarMunicipio(Municipio municipio){
		Session sessao = SessionUtil.getSession();
		sessao.update(municipio);
	}
	
	public void deletarMunicipio(Municipio municipio){
		Session sessao = SessionUtil.getSession();
		sessao.delete(municipio);
	}
	
}