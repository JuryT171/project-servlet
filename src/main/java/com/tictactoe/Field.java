package com.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Field {
    private final Map<Integer, Sign> field;

    public Field() {
        field = new HashMap<>();
        field.put(0, Sign.EMPTY);
        field.put(1, Sign.EMPTY);
        field.put(2, Sign.EMPTY);
        field.put(3, Sign.EMPTY);
        field.put(4, Sign.EMPTY);
        field.put(5, Sign.EMPTY);
        field.put(6, Sign.EMPTY);
        field.put(7, Sign.EMPTY);
        field.put(8, Sign.EMPTY);
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    // ищет свободную ячейку для компьютера
    public int getEmptyFieldIndex() {
        return field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY) // фильтруем до пустых
                .map(Map.Entry::getKey) // из мапы выделяем ключи
                .findFirst().orElse(-1); // берем первый самый маленький либо -1
    }
    // возвращает таблицу для отрисовки
    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // сортируем по ключу
                .map(Map.Entry::getValue) // вытаскиваем значение из мапы
                .collect(Collectors.toList()); // составляем список
    }

    public Sign checkWin() {
        List<List<Integer>> winPossibilities = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );
        // список выйгрышных комбинаций
        for (List<Integer> winPossibility : winPossibilities) { // цикл комбинаций
            if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(1))
                && field.get(winPossibility.get(0)) == field.get(winPossibility.get(2))) {
                return field.get(winPossibility.get(0));
            }
        }
        return Sign.EMPTY;
    }
}