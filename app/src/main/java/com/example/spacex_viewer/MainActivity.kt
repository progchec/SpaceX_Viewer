package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var launches: ArrayList<Launch> = ArrayList<Launch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        var rcV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        for (i in 1..50) {
            launches.add(Launch("a", "b", "c", "d"))
        }

        var launchAdapter: LaunchAdapter = LaunchAdapter(this, launches)
        rcV.adapter = launchAdapter
    }

    fun btnClick(view: android.view.View) {


        // var launchManager = LaunchManager(this, txt, rcV)
        // launchManager.requestLaunchesData("2019") // If scrolled down, year will be decreased
        // 2015-2019

        // var lnc: Launch = Launch("a", "b", "c", "d")
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