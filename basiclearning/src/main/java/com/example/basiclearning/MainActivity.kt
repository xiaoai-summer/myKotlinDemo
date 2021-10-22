package com.example.basiclearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//变量声明
        //val用于值从不更改的变量。使用val声明的变量无法重新赋值
        //var用于值可以更改的变量

        var count: Int = 10
        count = 15

        //使用val关键字定义，初始化后不可再修改
        val languageName: String = "Kotlin"


        //类型判断，利用Kotlin的类型推断，既能确保代码简洁，又能确保类型安全
        //"Kotlin"值为String类型，因此编译器推断name也是String,请注意 ，Kotlin是一种静态类型的语言，这意味着，类型将在编译时解析且从不改变
        val name = "Kotlin"
        val upperCaseName = name.toUpperCase()

        //Falis to Compile， name推断为String，因此无法对其调用任何不属于String类的函数
        //name.inc() //inc是一个Int运算符函数，因此无法对String利用它

//Null安全

        //在某些语言中，可以声明引用类型变量而不明确提供初始值，在这类情况下，变量通常包含null值。
        //默认情况下，kotlin变量不能持有null值，这意味着以下代码无效
        //val languageN: String = null

        //要使变量持有null值，它必须是可为null类型，可以在变量类型后面加?后缀，将变量指定为可为null
        //如下示例所示，指定String?类型后，可以为languageN赋予String值或null,必须小心处理可为null的变量，否则可能会出现NullPointerException,
        //例如在java中，如果对null值调用方法，程序会发生崩溃
        val languageN: String? = null

//条件语句
        if(count == 42) {
            println("I have the answer")
        } else if (count > 35) {
            println("The answer is close")
        } else{
            println("The answer eludes me")
        }

        //条件语句
        // 对于表示有状态的逻辑很有用，但编写这些语句时会出现重复，在上面的示例中，每个分支都是输出一个String,
        //为了避免这种重复，Kotlin提供了表达式，如下,每条分支都隐式返回最后一行的表达式的结果，因此无须使用return关键字

        val answerString: String = if (count == 42) {
            "I have the answer"
        } else if (count > 35) {
            "The answer is close"
        } else{
            "The answer eludes me"
        }

        println(answerString)

        //随着条件语句的复杂性不断增加，可以将if-else表达式替换为when表达式，如下示例

//when表达式

        //每个分支都由一个条件、一个箭头和一个结果表示
        val answerStr: String = when {
            count == 42 -> "I have the answer"
            count > 35 -> "The answer is close"
            else -> "The answer ecludes me"
         }
        println(answerStr)

//kotlin的条件语句
        // 彰显了它的一项强大的功能，即智能类型转换。你不必使用安全调用运算符或非空null断言运算符来处理可为null的值，而可以使用条件语句
        //来检查变量是否包含对null的引用，如下示例

        val languageNa: String? = null
        if (languageNa != null) {
            //no need to write languageNa?.toUpperCase()
            println(languageNa.toUpperCase())
        }

//函数
        //可以将一个或多个表达式归入一个函数，可以将相应的表达式封装在一个函数中并调用这个函数，而不必在每次需要某个结果时都重复同一系列的表达式
        //要声明函数，需使用fun关键字，后跟函数名称，接下来，定义函数接受的输入类型（如果有的话），并声明它返回的输出类型，函数的主体用于定义在调用函数时调用的表达式

        fun generateAnswerString(): String {
            val answerString = if (count == 42) {
                "I have the answer"
            } else{
                "The answer eludes me"
            }
            return answerString
        }

        //上面示例中的函数名为generateAnswerString.它不接受任何输入，它会输出String类型的结果。要调用函数，请使用函数名称，后跟调用运算符（）圆括号。
        //在下面的示例中，使用generateAnswerString()的结果对answerString变量进行了初始化

        val answer = generateAnswerString()

        //函数可以接受参数输入
        fun generateAnswerString1(countThreshold: Int): String {
            val answerString = if (count > countThreshold) {
                "I have the answer"
            } else {
                "The answer eludes me"
            }
            return answerString
        }

        //在生命函数时，可以指定任意数量的参数及其类型。在上面的示例中，generateAnswerString()接受一个名为countThreshold的Int类型参数，在函数中，可以使用参数的名称来引用参数
        //调用此函数时，必须在函数调用的圆括号内添加一个Int类型参数

        val answerS = generateAnswerString1(42)

//简化函数声明
        //generateAnswerString()函数时一个相对简单的函数，该函数声明一个变量，然后立即返回一个结果。函数返回单个表达式的结果时，可以通过直接返回函数中包含的if-else表达式的结果
        //来跳过声明局部变量

        fun generateAnswerString2(countThreshold: Int): String {
            return if (count > countThreshold) {
                "I have the answer"
            } else {
                "The answer eludes me"
            }
        }

        //还可以将return关键字替换为赋值运算符

        fun generateAnswerString(countThreshold: Int): String = if (count > countThreshold) {
            "I have the answer"
        } else {
            "The answer eludes me"
        }

//匿名函数
        //并非每个函数都需要一个名称。某些函数通过输入和输出更直接地进行标识，这些函数称为"匿名函数"，可以保留对某个匿名函数的引用，以便日后使用此引用来调用该匿名函数，与其他引用类型一样，
        //可以在应用中传递引用

        //源代码
        fun stringLengthFunc(input: String): Int{
            return input.length;
        }

        //lamba表达式
        val stringLengthFunc:(String) -> Int = {
            input -> input.length
        }

        //在上面的示例中，stringLengthFunc包含对一个匿名函数的引用，该函数将String当做输入，并将输入String的长度作为Int类型的输出返回。因此，该函数的类型表示为（String）->Int.不过，
        //此代码不会调用该函数。要检索该函数的结果，我们必须像调用函数一样调用该函数。调用stringLengthFunc时，必须提供String

        val stringLength: Int = stringLengthFunc("Android")

        Log.i("wxy", stringLength.toString())

//高阶函数
         //一个函数可以将另一个函数当做参数，将其他函数用作参数的函数称为"高阶函数"。此模式对组件之间的通信（其方式与在java中使用回调接口相同）很有用

        fun stringMapper(str: String, mapper: (String) -> Int): Int {
            return mapper(str)
        }

        //stringMapper()函数接受一个String以及一个函数，该函数根据传递给它的String来推导Int值
        //要调用StringMapper函数，可以传递一个String和一个满足其他输入参数的函数（即一个将String当做输入并输出Int的函数）

        stringMapper("Android", {input -> input.length})

//类
        class Wheel
//属性
        //类使用属性来表示状态，属性是类级变量，可以包含getter、setter和后备字段.由于车子需要轮子来驱动，因此可以添加Wheel对象的列表作为Car的属性

        class Car {
            val wheels = listOf<Wheel>()
        }

        //请注意，wheels是一个public val,这意味着，可以从Car类外部访问wheels，并且不能为其重新赋值，如果要获取Car的实例，必须先调用其构造函数，
        //这样，便可以访问它的任何可以访问的可访问属性

        val car = Car()
        val wheels = car.wheels

        //如果需要自定义轮子，可以定义一个自定义构造函数，用来指定如何初始化类属性
        class Car2(val wheels: List<Wheel>){

        }

//类函数和封装
        //类使用函数行为进行建模，函数可以修改状态，从而帮助我们只公开希望公开的数据，这种访问控制机制属于一个面向对象的更大概念，称为"封装"
        //在以下示例中，doorLock属性对Car类外部的一切都不公开，要解锁汽车🚗，必须使用unlockDoor()函数并传入有效的钥匙

        /**
        class Car3(val wheels: List<Wheel>) {
            private val doorLock: DoorLock = ...

            fun unlockDoor(key: Key): Boolean {
            //Return true if key is valid for door lock, false otherwise
            }
        }
       */

        //如果希望自定义属性的引用方式，则可以提供自定义的getter和setter。例如，如果希望公开属性的getter而限制访问其setter，则可以将该setter指定为private

        /**
        class Car3(val wheels: List<Wheel>) {
            private val doorLock: DoorLock = ...

            fun unlockDoor(key: Key): Boolean {
            //Return true if key is valid for door lock, false otherwise
            }
        }
        */
    }
}