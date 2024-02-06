package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String usuario;
    private String senha;
    private String caminho;
    private Connection con;

    public Conexao() {
        caminho = "jdbc:postgresql://localhost:5432/BDSocio ";
        usuario = "postgres";
        senha = ""; // por motivos de segurança omiti a senha.
    }

    public void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(caminho, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

    public void desconectar() {
            try {
                con.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao encerrar a conexão: " + e.getMessage());
            }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
