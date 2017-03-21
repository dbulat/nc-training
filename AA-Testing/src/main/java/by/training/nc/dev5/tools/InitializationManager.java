package by.training.nc.dev5.tools;

import by.training.nc.dev5.beans.test.Question;
import by.training.nc.dev5.beans.test.Test;
import by.training.nc.dev5.beans.test.TestContainer;
import by.training.nc.dev5.beans.users.Student;
import by.training.nc.dev5.beans.users.Tutor;
import by.training.nc.dev5.services.StudentService;
import by.training.nc.dev5.services.TutorService;

import java.util.*;

public class InitializationManager {
    private static TestContainer tests = new TestContainer();

    public static void testsDefaultInitialization()//without text file
    {
        List<Question> questions = new ArrayList<>();
        List<Integer> rightAnswers = new ArrayList<>();
        rightAnswers.add(2);
        Map<Integer, String> variants = new HashMap<>();
        variants.put(1, "6");
        variants.put(2, "9");
        questions.add(new Question("Вычислите сумму 6+3=...", 10, variants, rightAnswers));
        tests.addTest(new Test("Иван", "Иванов", "Математика", "Сложение", questions));
    }

    public static void menu() {
        while (true) {
            System.out.println("===========================================================");
            System.out.println("Выберите пользователя:");
            System.out.println("1. Tutor");
            System.out.println("2. Student");
            System.out.println("0. Выход");
            System.out.println("===========================================================");


            switch (ConsoleOperations.inputNumber()) {
                case 1:
                    Tutor tutor = ConsoleOperations.inputTutor();
                    TutorService tutorService = TutorService.getInstance();
                    out:
                    while (true) {
                        System.out.println("===========================================================");
                        System.out.println("1. Показать список тестов");
                        System.out.println("2. Добавить тест в систему");

                        System.out.println("0. Назад");
                        System.out.println("===========================================================");

                        switch (ConsoleOperations.inputNumber()) {
                            case 1:
                                System.out.println("===========================================================");
                                System.out.println(tests.toString());
                                break;

                            case 2:
                                System.out.println("Введите название теста (одно слово без пробелов)");
                                String testName = ConsoleOperations.inputString();
                                System.out.println("Введите количество вопросов");
                                int questionAmount=0;
                                while(true) {
                                    try {
                                        questionAmount = ConsoleOperations.inputNumber();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Неверный ввод! Повторите...");
                                        continue;
                                    }
                                    break;
                                }
                                tests.addTest(tutorService.creatingTest(tutor, testName, questionAmount));
                                System.out.println(tests.toString());
                                break;

                            case 0:
                                System.out.println("===========================================================");
                                break out;

                            default:
                                System.out.println("===========================================================");
                                System.out.println("Неверный выбор либо формат. Повторите...");
                        }
                    }
                    break;
                case 2:
                    Student student = ConsoleOperations.inputStudent();
                    StudentService studentService = StudentService.getInstance();
                    studentMenu:
                    while (true) {
                        System.out.println("===========================================================");
                        System.out.println("1. Показать список тестов");
                        System.out.println("2. Пройти тест");

                        System.out.println("0. Назад");
                        System.out.println("===========================================================");

                        switch (ConsoleOperations.inputNumber()) {
                            case 1:
                                System.out.println("===========================================================");
                                System.out.println(tests.toString());
                                break;

                            case 2:
                                List<List<Integer>> questionAnswers = new ArrayList<>();
                                System.out.println("Введите название теста (одно слово без пробела)");
                                String testName = ConsoleOperations.inputString();
                                Test test = tests.getTest(testName);
                                if (test != null) {
                                    for (Question question : test.getQuestions()) {
                                        List<Integer> answer=new ArrayList<>();
                                        System.out.println(question);
                                        System.out.println("Введите Ваши вариант(ы) ответа (разделитель-пробел)");
                                        while(true) {
                                            try {
                                                answer = ConsoleOperations.inputArray();
                                            } catch (NumberFormatException e) {
                                                System.out.println("Неверный ввод! Повторите...");
                                                continue;
                                            }
                                            break;
                                        }
                                        questionAnswers.add(answer);
                                    }
                                    System.out.println("Результат: " + studentService.passingTest(student, test, questionAnswers));
                                } else {
                                    System.out.println("Тест с таким названием отсутствует!");
                                }
                                break;

                            case 0:
                                System.out.println("===========================================================");
                                break studentMenu;

                            default:
                                System.out.println("===========================================================");
                                System.out.println("Неверный выбор либо формат. Повторите...");
                        }
                    }
                    break;
                case 0:
                    System.out.println("===========================================================");
                    System.out.println("Работа завершена...");
                    System.out.println("===========================================================");
                    System.exit(0);

                default:
                    System.out.println("===========================================================");
                    System.out.println("Неверный выбор либо формат. Повторите...");
            }
        }
    }
}