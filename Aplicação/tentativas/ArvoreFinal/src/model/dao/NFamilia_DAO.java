/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Familia;

/**
 *
 * @author Kcer
 */
public class NFamilia_DAO {
    
    Connection con;
    
    public NFamilia_DAO(){
    
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO 404 NOT FOUND");
        }
        
    }
    public void create(Familia p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO n_familia (familia,senha)VALUES(?,?)");
            stmt.setString(1, p.getFamilia());
            stmt.setString(2, p.getSenha());
            
            stmt.executeUpdate();
            

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Familia> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Familia> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM teste1");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Familia produto = new Familia();

                produto.setFamilia(rs.getString("nome"));
                //produto.setSenha(rs.getInt("id"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NFamilia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    public List<Familia> readForReturn(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Familia> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Familia produto = new Familia();

                produto.setCod_Fam(rs.getInt("cod_fam"));
                produto.setFamilia(rs.getString("familia"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NFamilia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }

    public void delete(Familia p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE cod_fam = ?");
            stmt.setInt(1, p.getCod_Fam());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
