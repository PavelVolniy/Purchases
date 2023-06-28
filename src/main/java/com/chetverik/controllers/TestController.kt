package com.chetverik.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/")
    fun test(model: Model): String {
        val names: List<String> = arrayListOf(
            "№ п/п",
            "Наименование",
            "Тип",
            "Состоялось",
            "Дата размещения",
            "Дата окончания",
            "Дата рассмотрения",
            "№ Договора",
            "Начальная цена",
            "Заявок подано", "Заявок допущ",
            "Цена заявок 1",
            "Цена заявок 2",
            "Разница значений",
            "Цена договора", "Экономия",
            "№ процедуры на еис"
        )
        val values = names.map { "$it test value" }
        val list = values + names
        model.addAttribute("names", names)
        model.addAttribute("contractList", values)
        return "addContract"
    }
}