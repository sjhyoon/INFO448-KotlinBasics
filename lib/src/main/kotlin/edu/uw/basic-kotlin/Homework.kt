package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person {
    var firstName: String = ""
    var lastName: String = ""
    var age: Int = 0
    val debugString: String

    constructor(firstName: String, lastName: String, age: Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        debugString = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
    }

}

// write a class "Money"
class Money {
    val currencyList: Array<String> = arrayOf("USD", "EUR", "CAN", "GBP")
    var amount: Int = 0
        set(value) {
            if(value < 0)
                throw IllegalArgumentException()
            else
                field = value    
        }
    var currency: String = ""
        set(value) {
            if(value in currencyList)
                field = value
            else
                throw IllegalArgumentException()
        }
    
    constructor(amount: Int, currency: String) {
        this.amount = amount
        this.currency = currency
    }
    
    fun convert(currencyType: String): Money {
        var newAmount = this.amount
        if(currencyType == "USD") {
            if(this.currency == "GBP")
                newAmount = this.amount * 2
            if(this.currency == "EUR")
                newAmount = this.amount * 2 / 3
            if(this.currency == "CAN")
                newAmount = this.amount * 4 / 5
        }
        if(currencyType == "GBP") {
            if(this.currency == "USD")
                newAmount = this.amount / 2
            if(this.currency == "EUR")
                newAmount = this.amount / 3
            if(this.currency == "CAN")
                newAmount = this.amount * 2 / 5
        }       
        if(currencyType == "EUR") {
            if(this.currency == "USD")
                newAmount = this.amount * 3 / 2
            if(this.currency == "GBP")
                newAmount = this.amount * 3
            if(this.currency == "CAN")
                newAmount = this.amount * 6 / 5
        }
        if(currencyType == "CAN") {
            if(this.currency == "USD")
                newAmount = this.amount * 5 / 4
            if(this.currency == "GBP")
                newAmount = this.amount * 5 / 2
            if(this.currency == "EUR")
                newAmount = this.amount * 5 / 6
        }
        return Money(newAmount, currencyType)
    }

    operator fun plus(other: Money): Money {
        if(this.currency != other.currency)
            return Money(this.amount + other.convert(this.currency).amount, this.currency)
        else
            return Money(this.amount + other.amount, this.currency)
    }
}
