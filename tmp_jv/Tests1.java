package ru.example;

import org.junit.jupiter.api.Test;

public class Tests1 {
    @Test
    public void amountInRange (){
        Account acc1= new Account();
        acc1.setAmtArr(ECurrency.RUB,100);
    }
    @Test
    public void amountNotInRange (){
       Account acc1 = new Account();
       try {    acc1.setAmtArr(ECurrency.RUB, -100);
        } catch (IllegalArgumentException ex) {return;}
       throw new RuntimeException("Ошибка теста");
    }
    @Test
    public void nameIsNotNull (){
        Account acc1= new Account();
        acc1.setOwner("Василий Иванов");
    }

    @Test
    public void nameIsNull (){
        Account acc1 = new Account();
        try {    acc1.setOwner(null);
        } catch (IllegalArgumentException ex) {return;}
        throw new RuntimeException("Ошибка теста");
    }

    @Test
    public void nameIsEmpty (){
        Account acc1 = new Account();
        try {    acc1.setOwner("");
        } catch (IllegalArgumentException ex) {return;}
        throw new RuntimeException("Ошибка теста");
    }

    @Test
    public void testUndo (){
        Account etalon = new Account();
        etalon.setAmtArr(ECurrency.RUB,100);
        Account acc = new Account();
        acc.setAmtArr(ECurrency.RUB,100);
        if (!acc.equals(etalon)) throw new RuntimeException("Ошибка теста"); // Проверяем что acc и etalon содержат одинаковую информацию
        acc.setOwner("Василий Иванов");
        if (acc.equals(etalon)) throw new RuntimeException("Ошибка теста"); // Проверим что измененный acc (+владелец), теперь не равен etalon
        acc.undo(); // теперь откатим последнее изменение, где указывали владельца
        if (!acc.equals(etalon)) throw new RuntimeException("Ошибка теста"); // acc и etalon теперь опять должны содержат одинаковую информацию
    }

    @Test
    public void testSave (){
        Account acc = new Account();
        acc.setAmtArr(ECurrency.RUB,100);
        acc.setOwner("Василий Иванов");
        if (acc.equalsSavedAcc(acc.savedAcc)) throw new RuntimeException("Ошибка теста"); // Проверяем что acc не соответствует savedAcc
        acc.save();     // сохраняем состояние
        if (!acc.equalsSavedAcc(acc.savedAcc)) throw new RuntimeException("Ошибка теста"); // Теперь acc соответствует savedAcc
        acc.undo(); // теперь откатим последнее изменение, где указывали владельца
        if (acc.equalsSavedAcc(acc.savedAcc)) throw new RuntimeException("Ошибка теста");   // Теперь они не равны так как,
                                                                                            // SavedAcc содержит сохраненное состояние,
                                                                                            // в acc отменено последнее изменение

    }
}
