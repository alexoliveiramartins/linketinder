package utils


import java.sql.SQLException

class Utils {
    static final dbConnParameters = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]

    private static final Scanner sc = new Scanner(System.in)

    static int readInt() {
        def input = sc.nextInt()
        sc.nextLine()
        return input
    }

    static String readLine() {
        return sc.nextLine()
    }

    static String promptInput(String property) {
        def input
        print "$property: "
        input = readLine()
        return input
    }

    static int promptInputInt(String property) {
        int input
        print "$property: "
        input = readInt()
        return input
    }

    static void dbErrorHandling(String acao, Closure block) {
        try {
            block.call()
        } catch (SQLException e) {
            println "Erro no banco de dados ao $acao: $e.message"
        } catch (Exception e) {
            println "Erro inesperado ao $acao: $e.message"
        }
    }
}
