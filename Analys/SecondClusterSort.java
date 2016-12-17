package Analys;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Артём on 19.10.2016.
 */
public class SecondClusterSort<T> {

/*
    private static Random random = new Random();



    private static List<Integer> firstCenters = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    @NotNull
    List<T> mean(List<List<Integer>> arrayElement, int qCenter) {                                                 //метод нахождения среднего значения
        int tempSum;
        List<Integer> meanValue = new ArrayList<>();
        for (int i = 0; i < qCenter; i++) {
            tempSum =0;
            for (int j = 0; j < arrayElement.get(i).size(); j++) {
                tempSum += arrayElement.get(i).get(j);
            }
            meanValue.add(Math.round(tempSum/arrayElement.get(i).size()));
        }
        Collections.sort(meanValue);
        return (List<T>) meanValue;
    }
/*

    static List<Integer> fillSourceArray() {                                                                            //наполняем исходный массив значениями
        List<Integer> sourceValues = new ArrayList<>();                                                                 //лист исходных значений
        IntStream.range(0, 300).forEach(i -> sourceValues.add(i, (random.nextInt(10000))));
        //List<Integer> sourceValues = Stream.generate(random::nextInt).limit(300).collect(Collectors.toList());
        /*
        boolean localValueCheck = true;
        while (localValueCheck) {
            localValueCheck = false;
            //IntStream.range(0, 300).forEach(i -> sourceValues.add(i, ((int) (Math.random() * 10000))));
            //sourceValues = Stream.generate(random::nextInt).limit(random.nextInt(300)).collect(Collectors.toList());
            Collections.sort(sourceValues);
            for (int i = 1; i < 300; i++) {
                int secondI = i - 1;
                if (sourceValues.get(i).equals(sourceValues.get(secondI)))
                    localValueCheck = true;
            }
        }*/
  //      return sourceValues;
    }
/*
    static List<List<Integer>> pick (List<Integer> sourceValues, int qCenter){                                          //метод заполнения кластеров
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

    @NotNull
    static Integer indexMinCompareCenter (int valueSourseValues){                                                       //возвращаем индекс минимального расстояния до центра кластера
        List<Integer> minValue = new ArrayList<>();
        for (int i = 0; i < firstCenters.size(); i++) {
            minValue.add((Math.abs(firstCenters.get(i) - valueSourseValues)));
        }
        return minValue.indexOf(Collections.min(minValue));
    }
/*
    void rePickArrays(List<List<Integer>> arrayElement, int qCenter, List<Integer> sourceValues){                //пересчет элементов кластеров
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

    @NotNull
    static Integer quantity (){
        int qCenter = 0;
        try {                                                                                                           //блок ввода количества кластеров
            System.out.print("Введите количество цетров: ");
            qCenter = in.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Get out!");
            System.exit(1);
        }
        return qCenter;
    }

    static List<Integer> generationFisrtCenter(int qCenter, List<Integer> sourceValues){
        IntStream.range(0, qCenter).forEach(i -> firstCenters.add(i, sourceValues.get(random.nextInt(299))));
        return firstCenters;
    }

/*
    public static void main(String[] args)  {
        List<List<Integer>> arrayElement;                                                                               //лист получаемых значений
        SecondClusterSort SSC = new SecondClusterSort();

        int qCenter = quantity();
        List<Integer> sourceValues = fillSourceArray();
        firstCenters = generationFisrtCenter(qCenter, sourceValues);

        Collections.sort(firstCenters);
        arrayElement = pick(sourceValues, qCenter);
        SSC.rePickArrays(arrayElement, qCenter, sourceValues);
    }
}*/
