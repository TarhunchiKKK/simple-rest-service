CREATE TABLE IF NOT EXISTS results              -- создание таблицы
(
    id            INTEGER PRIMARY KEY ,           -- ключ БД (интовое значение)
    numbers       VARCHAR(15) NOT NULL ,          -- строка с введенными числами
    middle_value  DOUBLE PRECISION NOT NULL ,     -- среднее значение чисел (значение с плавающей точкой)
    mediana       DOUBLE PRECISION NOT NULL       -- медианное значение чисел (значение с плавающей точкой)
);

-- генерация ID
CREATE SEQUENCE IF NOT EXISTS results_id_seq START WITH 1 INCREMENT BY 1;
--DROP TABLE IF EXISTS results;                 -- удаление таблицы
--DROP SEQUENCE IF EXISTS results_id_seq;       -- удаление генерации ID