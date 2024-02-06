package aplicacao;

public class Dependente {
    private int id, idade;
    private String nome_d;

    public Dependente (int id, int idade, String nome_d){
        this.id = id;
        this.idade = idade;
        this.nome_d = nome_d;
    }

    public Dependente (int idade, String nome_d){
        this.idade = idade;
        this.nome_d = nome_d;
    }

    public Dependente(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome_d() {
        return nome_d;
    }

    public void setNome_d(String nome_d) {
        this.nome_d = nome_d;
    }

}
