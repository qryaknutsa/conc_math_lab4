import equation.EquationGraphic;
import equation.linearSquareMethod.*;

import java.io.*;
import java.util.*;

public class IO {
    int chosenFunction;

    double[][] readFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            try {
                int n = Integer.parseInt(line);
                double[] x = new double[n];
                line = reader.readLine();
                String[] numbers_x = line.split("\\s+");
                if (n != numbers_x.length) {
                    System.out.println("Введено неправильное количество точек x. Ошибка...");
                    System.exit(-1);
                } else {
                    try {
                        for (int i = 0; i < n; i++) {
                            x[i] = Double.parseDouble(numbers_x[i].replace(",", "."));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод координат. Ошибка...");
                        System.exit(-1);
                    }
                }
                double[] y = new double[n];
                line = reader.readLine();
                String[] numbers_y = line.split("\\s+");
                if (n != numbers_y.length) {
                    System.out.println("Введено неправильное количество точек x. Ошибка...");
                    System.exit(-1);
                } else {
                    try {
                        for (int i = 0; i < n; i++) {
                            y[i] = Double.parseDouble(numbers_y[i].replace(",", "."));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод координат. Ошибка...");
                        System.exit(-1);
                    }
                }
                return new double[][]{x, y};
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверный формат ввода. Ошибка...");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла не существует.");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    double[][] readFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Выберите выбор ввода: 1 - консоль, 2 - файл: ");
        try {
            int res = in.nextInt();
            if (res == 1) {
                in = new Scanner(System.in);
                System.out.println("Введите количество точек:");
                try {
                    int n = in.nextInt();
                    double[] x = new double[n];
                    System.out.println("Введите координаты x через пробел:");
                    in = new Scanner(System.in);
                    String str_x = in.nextLine();
                    String[] numbers_x = str_x.split("\\s+");
                    if (n != numbers_x.length) {
                        System.out.println("Вы ввели неправильное количество точек x. Ошибка...");
                        System.exit(-1);
                    } else {
                        try {
                            for (int i = 0; i < n; i++) {
                                x[i] = Double.parseDouble(numbers_x[i].replace(",", "."));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод координат. Ошибка...");
                            System.exit(-1);
                        }
                    }

                    double[] y = new double[n];
                    System.out.println("Введите координаты y через пробел:");
                    in = new Scanner(System.in);
                    String str_y = in.nextLine();
                    String[] numbers_y = str_y.split("\\s+");
                    if (n != numbers_y.length) {
                        System.out.println("Вы ввели неправильное количество точек y. Ошибка...");
                        System.exit(-1);
                    } else {
                        try {
                            for (int i = 0; i < n; i++) {
                                y[i] = Double.parseDouble(numbers_y[i].replace(",", "."));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод координат. Ошибка...");
                            System.exit(-1);
                        }
                    }
                    return new double[][]{x, y};
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("\nТакой опции нет. Ошибка...");
                    System.exit(-1);
                }
            } else if (res == 2) {
                in = new Scanner(System.in);
                System.out.print("Введите имя файла: ");
                String filename = in.nextLine();
                return readFromFile(filename);
            } else {
                System.out.println("\nТакой опции нет. Ошибка...");
                System.exit(-1);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Такой опции нет. Ошибка...");
            System.exit(-1);
        }
        return null;
    }


    public void solve() {
        double[][] coors = readFromConsole();
        double[] x = coors[0];
        double[] y = coors[1];

        double min_x = Arrays.stream(x).min().getAsDouble();
        double min_y = Arrays.stream(y).min().getAsDouble();

        ArrayList<Double> deviations = new ArrayList<>();
        LinearFunc linearFunc = new LinearFunc();
        deviations.add(linearFunc.getDeviation(x, y));
        SquareFunc squareFunc = new SquareFunc();
        deviations.add(squareFunc.getDeviation(x, y));
        CubeFunc cubeFunc = new CubeFunc();
        deviations.add(cubeFunc.getDeviation(x, y));
        PowFunc powFunc = new PowFunc();
        if (min_x >= 0 && min_y >= 0) deviations.add(powFunc.getDeviation(x, y));
        ExpFunc expFunc = new ExpFunc();
        if (min_y >= 0) deviations.add(expFunc.getDeviation(x, y));
        LnFunc lnFunc = new LnFunc();
        if (min_x >= 0) deviations.add(lnFunc.getDeviation(x, y));

        double min = deviations.stream().min(Comparator.comparing(Double::valueOf)).get();

        int id = deviations.indexOf(min);
        EquationGraphic equationGraphic = new EquationGraphic();

        System.out.print("x = {");
        for (int i = 0; i < x.length - 1; i++) {
            System.out.print(x[i] + ", ");
        }
        System.out.println(x[x.length - 1] + "}");


        System.out.print("y = {");
        for (int i = 0; i < y.length - 1; i++) {
            System.out.print(y[i] + ", ");
        }
        System.out.println(y[y.length - 1] + "}");

        switch (id) {
            case 0 -> System.out.println("Линейная функция: " + linearFunc.func_name);
            case 1 -> System.out.println("Квадратичная функция: " + squareFunc.func_name);
            case 2 -> System.out.println("Кубическая функция: " + cubeFunc.func_name);
            case 3 -> System.out.println("Степенная функция: " + powFunc.func_name);
            case 4 -> System.out.println("Экспоненциальная функция: " + expFunc.func_name);
            case 5 -> System.out.println("Логарифмическая функция: " + lnFunc.func_name);
            default -> System.out.println("");
        }
        System.out.println("Среднеквадратичное отклонение - " + deviations.get(id));
        if (id == 0) {
            System.out.println("Коэффициент корреляции - " + linearFunc.getCorrelation(x, y));
        }
        linearFunc.getDeviation(x, y);

        equationGraphic.drawGraphic(++id, x, y);
    }

}
