package Classes.dao;

import Classes.bean.FamiliaForm;
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
public class FamiliaDAO {

    ////////////////////////////////////////////////////////////////////////////  OBJETOS
    Connection con;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    ////////////////////////////////////////////////////////////////////////////  CONSTRUTOR
    public FamiliaDAO() {
        con = ConnectionFactory.getConnection();
        System.out.println(getID() + "  ID RETORNADO DE USUARIO  ");
    }
    ////////////////////////////////////////////////////////////////////////////  CHAVE ESTRANGEIRA
    private static int ID;

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        FamiliaDAO.ID = ID;
    }

    ////////////////////////////////////////////////////////////////////////////  INICIALIZA COM LISTA 
   public List<FamiliaForm> read() {

        List<FamiliaForm> familias = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Familia WHERE (id_login = "+getID()+") ORDER BY id_familia ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                FamiliaForm familia = new FamiliaForm();

                familia.setFamilia(rs.getString("familia"));
                int id_familia = (rs.getInt("id_familia"));
                familia.setId_familia(id_familia);
                familias.add(familia);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return familias;

    }

    /////////////////////////////////////////////////////////////////////////////  BUSCA VALOR ORDENADO 

    public List<FamiliaForm> readForDesc(String fam) {

        List<FamiliaForm> family = new ArrayList<>();
        FamiliaForm dado = new FamiliaForm();
        System.out.println("Nome buscado " + fam);
        System.out.println("ID BUSCADO " + getID());
        String sql = ("SELECT * FROM familia WHERE (id_login = "+getID()+") AND (familia = "+fam+") ORDER BY id_familia ASC");

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                dado.setFamilia(rs.getString("familia"));
                dado.setId_familia(rs.getInt("id_familia"));

                family.add(dado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return family;

    }
////////////////////////////////////////////////////////////////////////////////  INSERE NOME FAMILIA   

    public void criar(FamiliaForm f) {
        ArvoreDAO dao = new ArvoreDAO();
        String sql = "insert into Familia (familia, id_login) values (?,?)";

        try {
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, f.getFamilia());
            stmt.setInt(2, getID());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            dao.setID(id);

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

////////////////////////////////////////////////////////////////////////////////  DELETA NOME FAMILIA
    public void delete(FamiliaForm f) {

        Connection con1 = ConnectionFactory.getConnection();

        try {
            stmt = con1.prepareStatement("DELETE FROM Familia WHERE familia = ?");
            stmt.setString(1, f.getFamilia());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Constam depenpendências nesse nome\n Proibido a exclusão");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
////////////////////////////////////////////////////////////////////////////////   ABRIr A ARVORE

    public boolean abrir(String nome) {
        ArvoreDAO dao = new ArvoreDAO();

        System.out.println("Acessando metodo abrir");
        Connection con1 = ConnectionFactory.getConnection();
        int id = getID();
        boolean check = false;
        try {
            stmt = con1.prepareStatement("SELECT id_familia FROM Familia WHERE (familia = '" + nome + "') AND (id_login = '" + id + "')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_familia");
                System.out.println(id + "  ID CONSULTADO EM FAMILIA ");
                dao.setID(id);// TRANFERE O ID DE LOGIN PARA FAMILIA
                check = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel Acessar");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }
}
