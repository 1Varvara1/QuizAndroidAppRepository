package com.example.quizapplication.JsonModels;

import com.example.quizapplication.constrants.AppConstants;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestAdder_CS_Basics {

    Questinarie questinarie;
    ArrayList<Question> questions;
    final String numCategory ="150";
    final String nameCategory ="C# Basics";

    public void AddTest(File fileDir) throws FileNotFoundException {

        questions = new ArrayList<Question>();
        String[] a1 = {"функциональным ", "обьектно-ориентировпным", "упрощенным", "перегруженным "};
        questions.add(new Question("с# являются языком ", a1, 1, numCategory));

        String[] a2 = { "класс", "функцию",   "namespace",  "все перечисленные"};
        questions.add(new Question("что можно перегрузить", a2, 1, numCategory));

        String[] a3 = { "class",
                "sting",
                "int",
                "array"};
        questions.add(new Question("какой тип можно расширить", a3, 0, numCategory));

        String[] a4 = {
                "string, bool, array ",
                "class, enum, string",
                "class, string",
                "string, enum, int"};
        questions.add(new Question("выберите вариант где все типы ссылочные", a4, 2, numCategory));

        String[] a5 = {
                "из int в  short",
                "из shot в int",
                "все перечисленные ",
                "из byte в float "};
        questions.add(new Question("какие преобразования типов НЕ выполняются автоматически", a5, 0, numCategory));

        String[] a6 = {  "файла проецироваемого в память ",
                "исключительные ситуации",
                "дерева выражений",
                "коды на промежуточном языке MSIL"};
        questions.add(new Question("лямбда-выражения на платформе dotnet могут существовать в виде", a6, 3, numCategory));

        String[] a7 = { "private, protected",
                "public, private",
                "protected, private",
                "все перечисленные "};
        questions.add(new Question("какие идентификаторы доступа в классе существуют", a7, 3, numCategory));

        String[] a8 = {  "sealed single abstruct",
                "sealed abstruct",
                "sealed single abstruct multiple",
                "sealed abstruct static"};
        questions.add(new Question("какие бывают идентификаторы и класса", a8, 3, numCategory));

        String[] a9 = {  "1",
                "неограниченное количество",
                "указывается в скобках при создании класса",
                "зависит от среды разработки"};
        questions.add(new Question("сколько наследников может быть у класса", a9, 1, numCategory));


        String[] a10 = {    "нет",
                "да",
                "зависит от среды разработки",
                "зависит от компилятора"};
        questions.add(new Question("можно ли сделать перегрузку операторов true , false", a10, 1, numCategory));

        String[] a11 = {  "нельзя",
                "можно",
                "зависит от кода ",
                "зависит от среды разработки "};
        questions.add(new Question("можно ли перегрузить оператор присваивания", a11, 0, numCategory));

        String[] a12 = { "нельзя",
                "можно",
                "можно при условии наследование от интерфейса",
                "можно при условии наличия идентификатора abstract"};
        questions.add(new Question("наследоваться от нескольких классов", a12, 0, numCategory));

        String[] a13 = { "нельзя",
                "можно",
                "можно при условии наследование от класса",
                "можно при условии обозначения идентификатором seald"};
        questions.add(new Question("Наследоваться от нескольких интерфейсов", a13, 1, numCategory));

        String[] a14 = { "ссылочный тип",
                "класс",
                "рекурсивный тип",
                "значимый тип"};
        questions.add(new Question("Структура это", a14, 3, numCategory));

        String[] a15 = {   "позволяет разделять сущность на несколько частей",
                "запрещает наследование",
                "позволяет использовать неуправляемый код",
                "ограничивает видимость текущем классом"};
        questions.add(new Question("Что делает модификатор в patrial", a15, 0, numCategory));


        String[] a16 = {    "ссылочным",
                "перечисляемым",
                "определяемым",
                "значимым "};
        questions.add(new Question("Тип string является типом ", a16, 0, numCategory));


        String[] a17 = {    "FCL",
                "MASM",
                "CIL",
                "CLI"};
        questions.add(new Question("C# компилируется в код ", a17, 3, numCategory));

        String[] a18 = {    "да",
                "нет",
                "зависит от кода",
                "зависит от режима работы"};
        questions.add(new Question("Выполнится ли блок finally если исключения не было", a18, 0, numCategory));

        String[] a19 = {   "ограничивает видимость текущей сборкой",
                "запрещает наследование",
                "разрешает наследование только один",
                "добавляется просто для наглядности но ничего не изменяет "};
        questions.add(new Question("Что делает модификатор seald", a19, 1, numCategory));

        String[] a20 = {    "передача слишком больших данных в класс",
                "передача слишком больших данных в функцию",
                "передача слишком большого файла через return",
                "использование 1 имени для разных методов"};
        questions.add(new Question("Что такое перегрузка методов", a20, 3, numCategory));

        String[] a21 = {     "нет отличий",
                "break используются в switch case а continue в циклах",
                "continue пропускает итерацию, break выходит из цикла",
                "continue работает только в циклах a break дополнительно в методах"};
        questions.add(new Question("B чём отличие между break и continue", a21, 2, numCategory));

        String[] a22 = {     "int, char, bool, float, double",
                "int, char, bool, float, double, uint, short",
                "ни один из них",
                "все перечисленные"};
        questions.add(new Question("Какие типы переменных существуют", a22, 3, numCategory));


        String[] a23 = {     "для создания веб сайтов",
                "для создания программ под ПК",
                "для написания игр",
                "все перечисленное"};
        questions.add(new Question("для чего можно использовать язык C#", a23, 3, numCategory));

        String[] a24 = {  "работает с файлами",
                "работает с исключениями",
                "работает с классами",
                "работает с базой данных"};
        questions.add(new Question("Что делает try-catch", a24, 1, numCategory));

        String[] a25 = {  "for",
                "for, while",
                "for, while, do while",
                "for, while, do while, foreach"};
        questions.add(new Question("какие циклы существуют в языке с#", a25, 3, numCategory));



////////////////////////////////////////////

        File path = fileDir;
        File file = new File( path, AppConstants.JSON_Question_set_fileName);

        InputStream inputStream   = new FileInputStream(file.getPath());
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str_QuestionsFile = sb.toString();

        ////////////////////////////////////////////


        Gson gson = new Gson();
        Questinarie ConvertQuestionsResult = gson.fromJson(str_QuestionsFile, Questinarie.class);

        ///////////////////////////////////


            ConvertQuestionsResult.getQuestionnaires().addAll(questions);
        ////////////////////////////////////


        String strToWrite =  gson.toJson(ConvertQuestionsResult);

        File pathWrite = fileDir;
        File fileWrite = new File( pathWrite,AppConstants.JSON_Question_set_fileName);

        FileOutputStream stream = new FileOutputStream(fileWrite, false);
        try {
            stream.write(strToWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ///////////////////////////////
       InitCategory(fileDir);
    }

    private void InitCategory(File fileDir) {

        Category category = new Category(numCategory,nameCategory);
        //////////////////////////////////////////////////////////
        File path = fileDir;
        File file = new File( path,AppConstants.JSON_Quiz_category_fileName);

        InputStream inputStream   = null;
        try {
            inputStream = new FileInputStream(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream ));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str_CategoryFile = sb.toString();
        ////////////////////////////////////////////////////
        Gson gson = new Gson();
        CategoryItem ConvertCategoryResult = gson.fromJson(str_CategoryFile, CategoryItem.class);
        ///////////////////////////////////////////////////////////////////////

        ConvertCategoryResult.getItems().add(category);

        //////////////////////////////////////////////

        String strToWrite =  gson.toJson(ConvertCategoryResult);

        File pathWrite =fileDir;
        File fileWrite = new File( pathWrite,AppConstants.JSON_Quiz_category_fileName);

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(fileWrite, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write(strToWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
