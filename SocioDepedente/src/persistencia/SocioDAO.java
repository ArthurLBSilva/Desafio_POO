package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Socio;

public class SocioDAO {
    private static Conexao con;
    
    public static void inserirSocio(Socio c1){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO Socio (pk_cpf_s, nome_s, data_adm) VALUES (?, ?, ?)");
            instrucao.setInt(1, c1.getCpf_s());
            instrucao.setString(2, c1.getNome_s());
            instrucao.setString(3, c1.getData_adm());

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
        SocioDAO.con = con;
    }

    public static void alterarSocio(int cpf, Socio c1){
        try {
            con.conectar();
        PreparedStatement instrucao = con.getCon().prepareStatement("UPDATE Socio SET pk_cpf_s = ?, nome_s = ?, data_adm = ? WHERE pk_cpf_s = ?");
            instrucao.setInt(1, c1.getCpf_s());
            instrucao.setString(2, c1.getNome_s());
            instrucao.setString(3, c1.getData_adm());
            instrucao.setInt(4, cpf);

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

    public static void Excluir (int cpf){
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("DELETE FROM Socio WHERE pk_cpf_s = ?");
            instrucao.setInt (1, cpf);
            
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

    public static ArrayList<Socio> Relatorio(){
        ArrayList<Socio> relatorio = new ArrayList<Socio>();

        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Socio");
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                int cpf = rs.getInt("pk_cpf_s");
                String nome = rs.getString("nome_s");
                String data = rs.getString("data_adm");

                Socio s1 = new Socio(cpf, nome, data);
                relatorio.add(s1);
            }
       

        }catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }finally{
            con.desconectar();
        }

        return relatorio;
    } 

    public static Socio BuscaCPF(int cpf){
        Socio c1 = null;
        try {
            con.conectar();
            PreparedStatement instrucao = con.getCon().prepareStatement("SELECT * FROM Socio WHERE pk_cpf_s = ?"); 
            instrucao.setInt (1, cpf);
            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int cpf2 = rs.getInt("pk_cpf_s");
                String nome = rs.getString("nome_s");
                String data = rs.getString("data_adm");
                c1 = new Socio(cpf2, nome, data);
            }
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        } finally{
            con.desconectar();
        }
        return c1;
    }

}
