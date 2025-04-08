package utils

class Utils {

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
}
