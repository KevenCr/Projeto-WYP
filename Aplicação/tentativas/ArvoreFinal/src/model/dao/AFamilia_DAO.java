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
import model.bean.arvore_genealogica;


public class AFamilia_DAO {
    
    Connection con;
   
    public AFamilia_DAO() throws SQLException {
        
       con = ConnectionFactory.getConnection();
    }
    
    public void create(arvore_genealogica p) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO arvore_genealogica (cod_fam,nome,grau)VALUES(?,?,?)");
            stmt.setInt(1, p.getCod_Fam());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getGrau());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
   public List<arvore_genealogica> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<arvore_genealogica> membros = new ArrayList<>();

        try {
            Connection con = null;
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                arvore_genealogica entrada = new arvore_genealogica();

                entrada.setCod_Fam(rs.getInt("cod_fam"));
                entrada.setNome(rs.getString("nome"));
                entrada.setGrau(rs.getString("grau"));
                membros.add(entrada);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AFamilia_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return membros;

    }
    
}

