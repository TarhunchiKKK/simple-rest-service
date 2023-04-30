package com.example.restjava.validators;

import com.example.restjava.controllers.ResultPairController;
import com.example.restjava.entity.Numbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//класс валидатора входных данных
@Component
public class NumbersValidator {
    //логгирование
    private final Logger logger = LoggerFactory.getLogger(ResultPairController.class);
    public ErrorList Validate(Numbers numbers){                    //проверка входных данных на валидированность
        ErrorList errors = new ErrorList();                           //список сообщений об ошибке
        int[] nums = numbers.getNumbers();                      //приведение массива строк к массиву чисел

        for(int i = 0; i < nums.length; i++) {                  //проверка значений на абсолютную величину
            if(nums[i] > 20){                                       //слишком большое значение
                logger.error("Element " + nums[i] + " is more then 20");
                errors.add("Element " + nums[i] + " is more then 20");
            }
            if(nums[i] < -20){                                      //слишком маленькое значение
                logger.error("Element " + nums[i] + " is less then -20");
                errors.add("Element " + nums[i] + " is less then -20");
            }
        }

        int equalsNumbersCounter = 1;
        for(int i = 1; i < nums.length;i++){
            if(nums[0] == nums[i]) equalsNumbersCounter++;
        }
        if(equalsNumbersCounter == nums.length){                //все значения равны нулю
            logger.error("All numbers are equals");                 //логгиование
            errors.add("All numbers are equals");                   //+ 1 ошибка
        }
        return errors;                                          //возврат всех ошибок
    }
}
