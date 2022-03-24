package nithya;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type name of the person");
            Info info = new Info();
            String name = scanner.nextLine();
            info.connect(name);
            boolean responseCode = info.checkResponseCode();
            scanner.close();
            if (!responseCode) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                System.out.println("The age of the person is: " + info.getAge());
                System.out.println("The gender of the person is: " + info.getGender());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
