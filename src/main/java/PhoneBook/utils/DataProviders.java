package PhoneBook.utils;

import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {UserData.VALID_EMAIL, UserData.VALID_PASSWORD},
                {"portishead2025@gmail.com", "Password@1"},
                {"portishead2026@gmail.com", "Password@1"}
        };
    }

    @DataProvider
    public Object[][] addContactDataProvider() {
        return new Object[][]{
                {"Jack", "Jackson", "4915212345678", "contact1@gmail.com", "Germany, Berlin", "friend 1"},
                {"Lukas", "Schneider", "49309876543", "contact2@mail.de", "Germany, Cologne", "friend 2"},
                {"Hannah", "Fischer", "49896543210", "contact2@gmail.com", "Germany, Hanover", "friend 3"}
        };
    }

    @DataProvider
    public Iterator<Object[]> iteratorDataProvider() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Jack", "Jackson", "4915212345678", "contact1@gmail.com", "Germany, Berlin", "friend 1"});
        list.add(new Object[]{"Lukas", "Schneider", "49309876543", "contact2@mail.de", "Germany, Cologne", "friend 2"});
        list.add(new Object[]{"Hannah", "Fischer", "49896543210", "contact2@gmail.com", "Germany, Hanover", "friend 3"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> objectDataProvider() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new Contact().setName("Jack").setLastName("Jackson").setPhone("4915212345678").setEmail("contact1@gmail.com").setAddress("Germany, Berlin").setDescription("friend 1")});
        list.add(new Object[]{new Contact().setName("Lukas").setLastName("Schneider").setPhone("49309876543").setEmail("contact2@mail.de").setAddress("Germany, Cologne").setDescription("friend 2")});
        list.add(new Object[]{new Contact().setName("Hannah").setLastName("Fischer").setPhone("49896543210").setEmail("contact2@gmail.com").setAddress("Germany, Hanover").setDescription("friend 3")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        // Создаем список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();
        // Открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
        // Читаем первую строку из файла
        String line = reader.readLine();
        // Обрабатываем каждую строку файла до конца
        while (line != null) {
            // Разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // Создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])
            });
            // Читаем следующую строку из файла
            line = reader.readLine();
        }
        // Закрываем файл после чтения всех данных
        reader.close();
        // Возвращаем итератор для списка объектов
        return list.iterator();
    }


}
