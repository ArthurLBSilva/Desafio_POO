package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String usuario, senha, caminho;
    private Connection con;

     public Conexao() {
        caminho = "jdbc:postgresql://localhost:5432/Agenda";
        usuario = "postgres";
        senha = "17042005";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void conectar(){
        try {    
          Class.forName("org.postgresql.Driver");
          con = DriverManager.getConnection(caminho, usuario, senha);
          System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na conexao: " + e.getMessage());
        }
    }

    public void desconectar(){
        try {
            con.close();
            System.out.println("Conexão encerrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar a conexão");
        }
    }
   


}
