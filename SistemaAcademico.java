import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Classe Aluno
class Aluno {
    private String nome;
    private String matricula;
    private String curso;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}

// Classe Disciplina
class Disciplina {
    private String nome;
    private String codigo;
    private Professor professor;
    private ArrayList<Aluno> alunosMatriculados;

    public Disciplina(String nome, String codigo, Professor professor) {
        this.nome = nome;
        this.codigo = codigo;
        this.professor = professor;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void matricularAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    public ArrayList<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void mostrarInformacoes() {
        System.out.println("Disciplina: " + nome + " (" + codigo + ")");
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Alunos matriculados: ");
        for (Aluno aluno : alunosMatriculados) {
            System.out.println(aluno.getNome());
        }
    }
}

// Classe Professor
class Professor {
    private String nome;
    private String matricula;

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }
}

// Classe principal
public class SistemaAcademico {
    private static HashMap<String, Aluno> alunos = new HashMap<>();
    private static HashMap<String, Disciplina> disciplinas = new HashMap<>();

    public static void main(String[] args) {
        // Adicionando algumas disciplinas e professores para teste
        Professor prof1 = new Professor("Prof. João", "P123");
        Professor prof2 = new Professor("Prof. Maria", "P456");

        disciplinas.put("D001", new Disciplina("Matemática", "D001", prof1));
        disciplinas.put("D002", new Disciplina("História", "D002", prof2));

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nSistema Academico:");
            System.out.println("1. Matricular Aluno");
            System.out.println("2. Listar alunos por disciplina");
            System.out.println("3. Mostrar informacoes da disciplina");
            System.out.println("4. Lista de disciplinas");
            System.out.println("5. Mostrar a informacao do aluno por matricula");
            System.out.println("6. Alterar informacoes do aluno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    matricularAluno(scanner);
                    break;
                case 2:
                    listarAlunosPorDisciplina(scanner);
                    break;
                case 3:
                    mostrarInformacoesDisciplina(scanner);
                    break;
                case 4:
                    listarDisciplinas();
                    break;
                case 5:
                    mostrarInformacaoAluno(scanner);
                    break;
                case 6:
                    alterarInformacoesAluno(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void matricularAluno(Scanner scanner) {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();
        System.out.print("Curso do aluno: ");
        String curso = scanner.nextLine();

        Aluno aluno = new Aluno(nome, matricula, curso);
        alunos.put(matricula, aluno);

        System.out.print("Codigo da disciplina para matricula: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = disciplinas.get(codigoDisciplina);

        if (disciplina != null) {
            disciplina.matricularAluno(aluno);
            System.out.println("Aluno matriculado com sucesso na disciplina " + disciplina.getNome());
        } else {
            System.out.println("Disciplina nao encontrada.");
        }
    }

    private static void listarAlunosPorDisciplina(Scanner scanner) {
        System.out.print("Codigo da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = disciplinas.get(codigoDisciplina);

        if (disciplina != null) {
            System.out.println("Alunos matriculados na disciplina " + disciplina.getNome() + ":");
            for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                System.out.println(aluno.getNome());
            }
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void mostrarInformacoesDisciplina(Scanner scanner) {
        System.out.print("Codigo da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = disciplinas.get(codigoDisciplina);

        if (disciplina != null) {
            disciplina.mostrarInformacoes();
        } else {
            System.out.println("Disciplina nao encontrada.");
        }
    }

    private static void listarDisciplinas() {
        System.out.println("Lista de disciplinas:");
        for (Disciplina disciplina : disciplinas.values()) {
            System.out.println(disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
        }
    }

    private static void mostrarInformacaoAluno(Scanner scanner) {
        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = alunos.get(matricula);

        if (aluno != null) {
            System.out.println(aluno);
        } else {
            System.out.println("Aluno nao encontrado.");
        }
    }

    private static void alterarInformacoesAluno(Scanner scanner) {
        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = alunos.get(matricula);

        if (aluno != null) {
            System.out.print("Novo nome do aluno: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo curso do aluno: ");
            String novoCurso = scanner.nextLine();

            aluno.setNome(novoNome);
            aluno.setCurso(novoCurso);

            System.out.println("Informacoes do aluno atualizadas com sucesso.");
        } else {
            System.out.println("Aluno nao encontrado.");
        }
    }
}
