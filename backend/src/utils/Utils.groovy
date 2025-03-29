package utils

class Utils {

    private static final Scanner sc = new Scanner(System.in)

    static int readInt(){
        def input = sc.nextInt()
        sc.nextLine()
        return input
    }

    static String readLine(){
        return sc.nextLine()
    }

}
