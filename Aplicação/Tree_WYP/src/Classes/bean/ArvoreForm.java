package Classes.bean;

/**
 *
 * @author Kcer
 */
public class ArvoreForm {
    ////// ATENÇÃO, ANTES DE USAR, ESSA CLASSE CONTÉM OBJETOS QUE LIGAM TRÊS TABELAS SENDO ELAS 

    //>>ARVORE (TABELA DE RELACIONAMENTO)
    //>>NOME (TABELA QUE CONTEM OS NOMES DAS PESSOAS)
    //>>GRAU (TABELA QUE CONTEM O GRAU DE PARENTESCO)
    ///////////////////////////////////////////////////////// ARVORE
    private static int idfamilia;
    private static int idnome;
    private static int idgrau;

    public static int getIdfamilia() {
        return idfamilia;
    }

    public static void setIdfamilia(int idfamilia) {
        ArvoreForm.idfamilia = idfamilia;
    }

    public static int getIdnome() {
        return idnome;
    }

    public static void setIdnome(int idnome) {
        ArvoreForm.idnome = idnome;
    }

    public static int getIdgrau() {
        return idgrau;
    }

    public static void setIdgrau(int idgrau) {
        ArvoreForm.idgrau = idgrau;
    }

    ///////////////////////////////////////////////////////// NOME   AND GRAU AND SEXO
    private static int id_nome1;
    private static int id_nome2;
    private String nome1;
    private String sobre1;
    private String sex1;
    private String grau;
    private String nome2;
    private String sobre2;
    private String sex2;

    public static int getId_nome1() {
        return id_nome1;
    }

    public static void setId_nome1(int id_nome1) {
        ArvoreForm.id_nome1 = id_nome1;
    }

    public static int getId_nome2() {
        return id_nome2;
    }

    public static void setId_nome2(int id_nome2) {
        ArvoreForm.id_nome2 = id_nome2;
    }

    public String getNome1() {
        return nome1;
    }

    public void setNome1(String nome1) {
        this.nome1 = nome1;
    }

    public String getSobre1() {
        return sobre1;
    }

    public void setSobre1(String sobre1) {
        this.sobre1 = sobre1;
    }

    public String getSex1() {
        return sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getNome2() {
        return nome2;
    }

    public void setNome2(String nome2) {
        this.nome2 = nome2;
    }

    public String getSobre2() {
        return sobre2;
    }

    public void setSobre2(String sobre2) {
        this.sobre2 = sobre2;
    }

    public String getSex2() {
        return sex2;
    }

    public void setSex2(String sex2) {
        this.sex2 = sex2;
    }

}
