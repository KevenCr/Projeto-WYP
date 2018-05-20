package Classes.dao;

import Classes.bean.UsuarioForm;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Kcer
 */
public class UsuarioDAO {
//////////////////////////////////////////////////////////////////////////////// OBJETOS
    Connection con;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    FamiliaDAO dao = new FamiliaDAO();
//////////////////////////////////////////////////////////////////////////////// CONSTRUTOR
    public UsuarioDAO() {
        con = ConnectionFactory.getConnection();
    }

////////////////////////////////////////////////////////////////////////////////  CHECAR EXISTENCIA DE USUARIO
    public boolean checkLogin(String login, String senha) {

        Connection con1 = ConnectionFactory.getConnection();

        boolean check = false;
        try {
            stmt = con1.prepareStatement("SELECT id_login FROM Login WHERE (login = '" + login + "') AND (senha = '" + senha + "')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_login");
                System.out.println(id + "  ID CONSULTADO EM LOGIN ");
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

//////////////////////////////////////////////////////////////////////////////// CRIA NOVO USUARIO
    public void insere_usuario(UsuarioForm user) {
        String sql = ("INSERT INTO Login (login,senha)VALUES(?,?)");
        try {
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            dao.setID(id); // TRANFERE O ID DE LOGIN PARA FAMILIA
            System.out.println(id + "  ID GERADO EM LOGIN LOGIN  ");

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println("ERROR");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
