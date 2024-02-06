package persistencia;

import aplicacao.Contato;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContatoDAO {
    private static Conexao con;
    

    public static void inserirContato(Contato c1){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO Contato (nome, telefone, email) VALUES (?, ?, ?)");
            instrucao.setString(1, c1.getNome());
            instrucao.setString(2, c1.getTelefone());
            instrucao.setString(3, c1.getEmail());

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso");
            } else{
                System.out.println("Nenhuma linha afetada durante a inserção");
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally{
            con.desconectar();
        }
    }

    public static void setCon(Conexao con) {
        ContatoDAO.con = con;
    }

    public static void Alterar(int id, Contato c1){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("UPDATE Contato SET nome = ?, telefone = ?, email = ? WHERE pk_id = ?");
            instrucao.setString(1, c1.getNome());
            instrucao.setString(2, c1.getTelefone());
            instrucao.setString(3, c1.getEmail());
            instrucao.setInt (4, id);

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso");
            } else{
                System.out.println("Nenhuma linha afetada durante a inserção");
            }

        } catch (SQLException e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }finally{
            con.desconectar();
        }
    }

    public static void Excluir (int id){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("DELETE FROM Contato WHERE pk_id = ?");
            instrucao.setInt (1, id);
            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso");
            } else{
                System.out.println("Nenhuma linha afetada durante a inserção");
            }
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }finally{
            con.desconectar();
        }
        
    }

    public static ArrayList<Contato> Relatorio(){
        ArrayList<Contato> relatorio = new ArrayList<Contato>();

        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Contato");
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("pk_id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                Contato c1 = new Contato(id, nome, telefone, email);
                relatorio.add(c1);
            }
       

        }catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }finally{
            con.desconectar();
        }

        return relatorio;
    } 

    public static Contato BuscaID(int id){
        Contato c1 = null;
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Contato WHERE pk_id = ?"); 
            instrucao.setInt (1, id);
            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                c1 = new Contato(id, nome, telefone, email);
            }
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally{
            con.desconectar();
        }
        return c1;
    }

    public static Contato BuscaNome(String nome){
        Contato c1 = null;
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Contato WHERE nome = ?"); 
            instrucao.setString (1, nome);
            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("pk_id");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                c1 = new Contato(id, nome, telefone, email);
            }
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally{
            con.desconectar();
        }
        return c1;
    }


}


