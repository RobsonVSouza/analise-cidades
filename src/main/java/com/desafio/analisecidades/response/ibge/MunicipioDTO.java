package com.desafio.analisecidades.response.ibge;


public class MunicipioDTO {

    private Long id;
    private String nome;
 //   private MicrorregiaoDTO microrregiao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

/*    public MicrorregiaoDTO getMicrorregiao() {
        return microrregiao;
    }

    public void setMicrorregiao(MicrorregiaoDTO microrregiao) {
        this.microrregiao = microrregiao;
    }*/

    @Override
    public String toString() {
        return "MunicipioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' ;//+
                //", microrregiao=" + microrregiao +
               // '}';
    }
}
