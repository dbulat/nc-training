package by.training.nc.dev5.serialization;

import by.training.nc.dev5.beans.test.Question;

import java.io.*;

/**
 * Created by NotePad.by on 19.03.2017.
 */
public class QuestionSerializer {
    public boolean serialization(Question q, String filename) {
        boolean flag = false;
        File f = new File(filename);
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            if (fos != null) {
                ostream = new ObjectOutputStream(fos);
                ostream.writeObject(q);
                flag = true;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден! " + e);
        } catch (NotSerializableException e) {
            System.out.println("Класс не поддерживает сериализацию " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }

            } catch (IOException e) {
                System.err.println("ошибка закрытия потока");
            }
        }
        return flag;
    }

    public Question deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);
            Question question = (Question) istream.readObject();
            return question;
        } catch (FileNotFoundException e) {
            System.out.println("Файл для десериализации не существует" + e);
        } catch (IOException e) {
            System.out.println("I/O ошибка: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("ошибка закрытия потока ");
            }
        }
        throw new InvalidObjectException("объект не восстановлен!");
    }
}
