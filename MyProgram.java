package Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyProgram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, "ibm866");

        System.out.println(
                "Введите данные (Фамилия Имя Отчество, дата рождения(dd.mm.yyyy), номер телефона, пол(используйте 'm' для мужского и 'f' для женского):");
        String input = scanner.nextLine();

        String[] data = input.split(",");
        if (data.length != 4) {
            System.out.println("Ошибка: введено неверное количество данных.");
            return; // завершаем метод main
        }

        // Извлекаем данные из массива и обрезаем лишние пробелы
        String[] nameParts = data[0].trim().split(" ");
        String surname = nameParts[0];
        String name = nameParts[1];
        String patronymic = nameParts[2];
        String birthDate = data[1].trim();
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: номер телефона должен быть целым числом.");
            return;
        }
        char gender;
        if (data[3].trim().equalsIgnoreCase("m") || data[3].trim().equalsIgnoreCase("f")) {
            gender = data[3].trim().charAt(0);
        } else {
            System.out.println("Ошибка: неверно указан пол. Используйте 'm' для мужского и 'f' для женского.");
            return;
        }

        // Формируем имя файла на основе фамилии пользователя
        String filename = surname + ".txt";

        // Формируем строку для записи в файл
        String record = String.format("%s %s %s %s %d %c\n", surname, name, patronymic, birthDate, phoneNumber,
                gender);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(record);
            // writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при записи в файл.");
        }

    }
}