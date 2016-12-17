package Analys;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Артём on 19.10.2016.
 */
public class ClusterSort<T> {

    private static Random random = new Random();
    private static List<Integer> firstCenters = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    private static Integer quantity(){
        int qCenter = 0;
        try {                                                                                                           //блок ввода количества кластеров
            System.out.print("Введите количество центров: ");
            qCenter = in.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Get out!");
            System.exit(1);
        }
        return qCenter;
    }

    private List<? extends T> fillSourceArray() {                                                                       //наполняем исходный массив значениями
        List<Integer> sourceValues = new ArrayList<>();                                                                 //лист исходных значений
        int n = 0;
        System.out.print("Введите количетсво элементов: ");
        try {
            n = in.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Get out");
            System.exit(1);
        }
        IntStream.range(0, n).forEach(i -> sourceValues.add(i, (random.nextInt(10000))));
        System.out.println(sourceValues);
        return (List<T>) sourceValues;
    }

    private List<? extends T> generationFirstCenter(int qCenter, List<Integer> sourceValues){                           //генерируем первые центры
        IntStream.range(0, qCenter).forEach(i -> firstCenters.add(i, sourceValues.get(random.nextInt(sourceValues.size()))));
        return (List<T>) firstCenters;
    }

    private static List<List<Integer>> pick(List<Integer> sourceValues, int qCenter){                                   //метод заполнения кластеров
        List<List<Integer>> arrayElement = new ArrayList<>();
        List<Integer> element;
        for (int i = 0; i < qCenter; i++) {
            element = new ArrayList<>();
            arrayElement.add(element);
        }
        for (int i = 0; i < sourceValues.size(); i++) {
            arrayElement.get(indexMinCompareCenter(sourceValues.get(i))).add(sourceValues.get(i));
        }
        return arrayElement;
    }

    private List<? extends T> mean(List<List<Integer>> arrayElement, int qCenter) {                                     //метод нахождения среднего значения
        List<Integer> meanValue = new ArrayList<>();

        for (int i = 0; i < qCenter; i++) {
            int tempSum = 0;
            for (int j = 0; j < arrayElement.get(i).size(); j++) {
                tempSum += arrayElement.get(i).get(j);
            }
            meanValue.add(Math.round(tempSum/arrayElement.get(i).size()));
        }

        Collections.sort(meanValue);
        return (List<T>) meanValue;
    }

    private static Integer indexMinCompareCenter(int valueSourceValues){                                                //возвращаем индекс минимального расстояния до центра кластера
        List<Integer> minValue = new ArrayList<>();
        IntStream.range(0, firstCenters.size()).forEach((i) -> minValue.add((Math.abs(firstCenters.get(i) - valueSourceValues))));
        return minValue.indexOf(Collections.min(minValue));
    }

    private void rePickArrays(List<List<Integer>> arrayElement, int qCenter, List<Integer> sourceValues){               //пересчет элементов кластеров
        while (true){
            List<List<Integer>> arrayElementOld = arrayElement;

            firstCenters = (List<Integer>) mean(arrayElement, qCenter);
            System.out.println();
            System.out.println("Центры: ");
            System.out.println(firstCenters);

            arrayElement = pick(sourceValues, qCenter);

            for (int i = 0; i < qCenter; i++) {
                System.out.println("Кластер №" + i);
                System.out.println(arrayElement.get(i));
            }
            if (arrayElementOld.equals(arrayElement)){
                break;
            }
        }
    }


    public static void main(String[] args)  {
        List<List<Integer>> arrayElement;                                                                               //лист получаемых значений
        ClusterSort<Integer> CS = new ClusterSort<>();

        int qCenter = quantity();
        List<Integer> sourceValues = (List<Integer>) CS.fillSourceArray();
        firstCenters = (List<Integer>) CS.generationFirstCenter(qCenter, sourceValues);

        Collections.sort(firstCenters);
        arrayElement = pick(sourceValues, qCenter);
        CS.rePickArrays(arrayElement, qCenter, sourceValues);
    }
}