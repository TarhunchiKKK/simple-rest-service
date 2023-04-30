package com.example.restjava.services;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import com.example.restjava.exceptions.ServerException;
import org.springframework.stereotype.Service;

@Service
public class MathService {
    //получение выходных данных
    public ResultPair getResult(Numbers numbers) throws ServerException{
        int[] nums = numbers.getNumbers();                          //получаем численное представление строк
        if(nums.length == 0) throw new ServerException("Set is empty");//ничего непришло - отправляем ошибочные выходные данные

        int sum = 0;                                                //суммирование значений
        for(int i = 0 ; i < nums.length;i++){
            sum += nums[i];
        }
        double middleValue = sum/nums.length;                       //среднее значение

        for(int i = 0 ; i < nums.length - 1;i++){                   //сортировака выбором для нахождения медианы
            int min = i;
            for(int j = i + 1; j < nums.length;j++){
                if(nums[min] > nums[j])
                    min = j;
            }
            if(min != i){
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
        double mediana;
        if(nums.length % 2 != 0) mediana = nums[nums.length/2];             //медиана для нечетного кол-ва значений
        else mediana = (nums[nums.length/2] + nums[nums.length/2 - 1])/2;   //медиана для четного кол-ва значений

        return new ResultPair(middleValue, mediana);                        //возврат выходных данных
    }
}
