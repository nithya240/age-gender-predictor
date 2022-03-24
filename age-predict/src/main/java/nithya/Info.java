package nithya;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Info {
    private String urlAge = "https://api.agify.io?";
    private String urlGender = "https://api.genderize.io?";
    private URL ageInfo, genderInfo;
    private HttpURLConnection connectAge, connectGender;

    public void connect(String name) throws IOException{
        ageInfo = new URL(urlAge + "name=" + name);
        genderInfo = new URL(urlGender + "name=" + name);
        connectAge = (HttpURLConnection) ageInfo.openConnection();
        connectAge.setRequestMethod("GET");
        connectGender = (HttpURLConnection) genderInfo.openConnection();
        connectGender.setRequestMethod("GET");
        connectGender.connect();
        connectAge.connect();
    }

    public boolean checkResponseCode() throws IOException {
        if(connectAge.getResponseCode() == 200 && connectGender.getResponseCode() == 200) return true;
        return false;
    }

    public String getAge() throws IOException {
        StringBuilder informationString = new StringBuilder();
        Scanner scanner = new Scanner(ageInfo.openStream());

        while (scanner.hasNext()) {
            informationString.append(scanner.nextLine());
        }
        scanner.close();

        String[] arr = informationString.toString().split(",");
        String[] age = arr[1].split(":");
        return age[1];
    }

    public String getGender() throws IOException {
        StringBuilder informationString = new StringBuilder();
        Scanner scanner = new Scanner(genderInfo.openStream());

        while (scanner.hasNext()) {
            informationString.append(scanner.nextLine());
        }
        scanner.close();

        String[] arr = informationString.toString().split(",");
        String[] gender = arr[1].split(":");
        return gender[1];
    }
}
