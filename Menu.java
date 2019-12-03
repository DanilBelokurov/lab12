import java.io.BufferedReader;
import java.io.InputStreamReader;

class Menu {
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private String host;
    private int port;

    String getName(){return name;}
    String getHost(){return host;}
    int getPort(){return port;}

    void menu() {
        try {
            System.out.println("Welcome to server");
            System.out.println("1 - Изменить имя пользователя (@name Vasya)");
            System.out.println("2 - Отправить сообщение конкретному пользователю (@senduser Vasya)");
            System.out.println("3 - Выход (@quit)");

            System.out.print("Введите хост: ");
            host = "localhost"; //input.readLine();
            System.out.print("Введите порт: ");
            port = 255;//Integer.parseInt(input.readLine());
            System.out.print("Введите имя пользователя: ");
            name = input.readLine();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
