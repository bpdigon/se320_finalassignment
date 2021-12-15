package se320_finalassignment;
/*
Final assignment Part 3
Server Client
Made by: Benigno Digon
Credit to Dr. Akbas for code templates and examples


*/

public class Question3{
    public static<E extends Comparable<E>> int linearSearch(E[] list, E key){
        int count = 0;
        for(E el : list){

            if(el == key){
                return count;
            }
            else{
                count++;

            }

        }

        return -1;
    }

    public static void main(String[] args){
        Integer[] list = {3, 4, 5, 1, -3, -5, -1};
        System.out.println(linearSearch(list, 2));
        System.out.println(linearSearch(list, 5));
    }

}