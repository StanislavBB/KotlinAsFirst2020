@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import ru.spbstu.kotlin.generate.combinators.shrinkMany
import ru.spbstu.kotlin.typeclass.kind
import ru.spbstu.wheels.NullableMonad.filter
import ru.spbstu.wheels.defaultCompareTo
import ru.spbstu.wheels.getEntry

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    private val book = mutableMapOf<String, MutableSet<String>>()

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean =
        if (name !in book) {
            book[name] = mutableSetOf()
            true
        } else false

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean =
        if (name in book) {
            book.remove(name)
            true
        } else false


    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean =
        if (name in book && !book.values.any { it.contains(phone) }) {
            book[name]!!.add(phone)
            true
        } else false


    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean =
        if (name in book && book.values.any { it.contains(phone) }) {
            book[name]!!.remove(phone)
            true
        } else false


    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> = book.getValue(name).toSet()

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        for ((key, value) in book) {
            if (value.contains(phone)) return key
        }
        return null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhoneBook

        if (book != other.book) return false

        return true
    }

    override fun hashCode(): Int = book.hashCode()

}