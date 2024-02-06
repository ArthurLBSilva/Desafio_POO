package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Dependente;
import aplicacao.Socio;

public class DependenteDAO {
     private static Conexao con;
    
    public static void inserirDependente(Dependente d1, int cpf){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO Dependente (nome_d, idade, fk_cpf_s) VALUES (?, ?, ?)");
            instrucao.setString(1, d1.getNome_d());
            instrucao.setInt(2, d1.getIdade()); 
            instrucao.setInt(3, cpf);
            

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
        DependenteDAO.con = con;
    }

    public static boolean BuscaCPF(int cpf) {
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Socio WHERE pk_cpf_s = ?");
            instrucao.setInt(1, cpf);
            ResultSet rs = instrucao.executeQuery();
    
            return rs.next();
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    
        return false; 
    }

    public static void alterarDependente(int cpf, Dependente c1, int id){
        try {
            con.conectar();
        PreparedStatement instrucao = con.getCon().prepareStatement("UPDATE Dependente SET nome_d = ?, idade = ?, fk_cpf_s = ? WHERE pk_id = ?");
            instrucao.setString(1, c1.getNome_d());
            instrucao.setInt(2, c1.getIdade());
            instrucao.setInt(3, cpf);
            instrucao.setInt(4, id);

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
            PreparedStatement instrucao = con.getCon().prepareStatement("DELETE FROM Dependente WHERE pk_id = ?");
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


    public static ArrayList<Dependente> Relatorio(int cpf){
        ArrayList<Dependente> relatorio = new ArrayList<Dependente>();

        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Dependente WHERE fk_cpf_s = ?");
            instrucao.setInt (1, cpf);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("pk_id");
                String nome = rs.getString("nome_d");
                int idade = rs.getInt("idade");


                Dependente s1 = new Dependente(id, idade, nome);
                relatorio.add(s1);
            }
       

        }catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }finally{
            con.desconectar();
        }

        return relatorio;
    } 

    public static Dependente BuscaID(int id){
        Dependente c1 = null;
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Dependente WHERE pk_id = ?"); 
            instrucao.setInt (1, id);
            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id2 = rs.getInt("pk_id");
                String nome = rs.getString("nome_d");
                int idade = rs.getInt("idade");
                c1 = new Dependente(id2, idade, nome);
            }
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally{
            con.desconectar();
        }
        return c1;
    }

    
}
