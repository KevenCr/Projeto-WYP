package Classes.dao;

import Classes.bean.ArvoreForm;
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

/**
 *
 * @author Kcer
 */
public class ArvoreDAO {
//////////////////////////////////////////////////////////////////////////////// CONEXÃO COM BANCO    

    Connection con;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public ArvoreDAO() {
        con = ConnectionFactory.getConnection();
    }
//////////////////////////////////////////////////////////////////////////////// CHAVE DE CONEXÃO
    private static int ID;

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        ArvoreDAO.ID = ID;
    }

//////////////////////////////////////////////////////////////////////////////// ADICIONAR MEMBRO
    public void member(ArvoreForm a) {
        System.out.println(" NOME  " + a.getNome1());
        System.out.println(" SEXO  " + a.getSex1());
        String NOME1 = ("INSERT INTO nome (nome, sexo) VALUE (?,?)");
        String NOME2 = ("INSERT INTO nome (nome, sexo) VALUE (?,?)");
        try {
            stmt = con.prepareStatement(NOME1, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getNome1());
            stmt.setString(2, a.getSex1());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            a.setId_nome1(id);
            System.out.println("ID GERADO DO NOME1  " + id);

            stmt = con.prepareStatement(NOME2, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getNome2());
            stmt.setString(2, a.getSex2());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            a.setId_nome2(id);
            System.out.println("ID GERADO DO NOME2  " + id);
            ////////////////////////////////////////////////////////////////////
            String select = a.getGrau();
            System.out.println("O Grau de Parentesco é  " + select);
            switch (select) {
                case "PAI":
                    a.setIdgrau(1);
                    break;
                case "MÃE":
                    a.setIdgrau(2);
                    break;
                case "FILHO":
                    a.setIdgrau(3);
                    break;
                case "FILHA":
                    a.setIdgrau(4);
                    break;
                case "AVÔ":
                    a.setIdgrau(5);
                    break;
                case "AVÓ":
                    a.setIdgrau(6);
                    break;
                case "TIO":
                    a.setIdgrau(7);
                    break;
                case "TIA":
                    a.setIdgrau(8);
                    break;
                case "SOBRINHO":
                    a.setIdgrau(9);
                    break;
                case "SOBRINHA":
                    a.setIdgrau(10);
                    break;
                case "GENRO":
                    a.setIdgrau(11);
                    break;
                case "NORA":
                    a.setIdgrau(12);
                    break;
                case "CUNHADO":
                    a.setIdgrau(13);
                    break;
                case "CUNHADA":
                    a.setIdgrau(14);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Sorry, Error");
            }
            inserir(a);
            ////////////////////////////////////////////////////////////////////
            JOptionPane.showMessageDialog(null, "Salvo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
/////////////////////////////////////////////////////////////////// ADICIONAR ID: FAMILIA, GRAU, NOME NA ARVORE

    public void inserir(ArvoreForm a) {

        try {
            stmt = con.prepareStatement("INSERT INTO Arvore (id_familia, id_grau, id_nome, id_nome1)VALUES(?,?,?,?)");
            stmt.setInt(1, getID());
            stmt.setInt(2, a.getIdgrau());
            stmt.setInt(3, a.getId_nome1());
            stmt.setInt(4, a.getId_nome2());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
/////////////////////////////////////////////////////////////////  SELECIONA ARVORE

    public List<ArvoreForm> read() {

        List<ArvoreForm> trees = new ArrayList<>();

        try {
            stmt = con.prepareStatement(""
                    + "SELECT n1.nome AS nome1, n1.sexo AS sexo1,\n"
                    + "g.grau AS grau, n.nome AS nome2, n.sexo AS sexo2 \n"
                    + "FROM arvore a \n"
                    + "JOIN nome n ON n.id_nome = a.id_nome1\n"
                    + "JOIN grau g ON g.id_grau = a.id_grau\n"
                    + "JOIN nome n1 ON n1.id_nome = a.id_nome\n"
                    + "WHERE (a.id_familia = " + getID() + ") \n"
                    + "ORDER BY a.id_arvore ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ArvoreForm arvore = new ArvoreForm();

                arvore.setNome1(rs.getString("nome1"));
                arvore.setSex1(rs.getString("sexo1"));
                arvore.setGrau(rs.getString("grau"));
                arvore.setNome2(rs.getString("nome2"));
                arvore.setSex2(rs.getString("sexo2"));

                trees.add(arvore);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArvoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return trees;

    }

}
