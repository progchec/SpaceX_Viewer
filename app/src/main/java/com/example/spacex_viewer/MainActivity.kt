package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: android.view.View) {
        var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        var table: TableLayout = findViewById<TableLayout>(R.id.tableLayout) as TableLayout

        var launchManager = LaunchManager(this, txt, table)
        launchManager.requestLaunchesData("2019") // If scrolled down, year will be decreased

        // requestLaunchesData("2019") // when scrolls down, year must be changed
    }
}

/*
Написать Android-приложение на ЯП Kotlin, которое будет выводить все успешные космические миссии SpaceX за 2015-2019 года.
Также должна быть возможность отсортировать все подходящие миссии в порядке убывания/возрастания годов (первоначальное состояние -
в порядке убывания, т. е. сначала самые свежие данные).

Для получения данных следует использовать следующее REST API: github.com/r-spacex/SpaceX-API

Для каждого запуска выведите на экран:
название миссии
дату запуска
текстовую информацию о запуске
картинку ракеты, для которой производился запуск (для этого может понадобиться отдельный сетевой запрос)
Рекомендации по выполнению:
используйте принципы SOLID и Clean Architecture для организации кода
используйте паттерн представления MVP/MVVM
обрабатывайте ошибки в одном месте
Критерии оценки:
правильность выполнения задания
обработка типичных ситуаций (отсутствие сети, переворот экрана)
чистота кода и архитектура решения
наличие Unit-тестов
 */