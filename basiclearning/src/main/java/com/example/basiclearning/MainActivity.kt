package com.example.basiclearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
//        name.inc() //inc是一个Int运算符函数，因此无法对String利用它

        //Null安全
        //在某些语言中，可以声明引用类型变量而不明确提供初始值，在这类情况下，变量通常包含null值。
        //默认情况下，kotlin变量不能持有null值，这意味着以下代码无效
//        val languageN: String = null

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

        //条件语句  对于表示有状态的逻辑很有用，但编写这些语句时会出现重复，在上面的示例中，每个分支都是输出一个String,
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

        //when表达式   每个分支都由一个条件、一个箭头和一个结果表示
        val answerStr: String = when {
            count == 42 -> "I have the answer"
            count > 35 -> "The answer is close"
            else -> "The answer ecludes me"
         }
        println(answerStr)

        //kotlin的条件语句彰显了它的一项强大的功能，即智能类型转换。你不必使用安全调用运算符或非空null断言运算符来处理可为null的值，而可以使用条件语句
        //来检查变量是否包含对null的引用，如下示例

        val languageNa: String? = null
        if (languageNa != null) {
            //no need to write languageNa?.toUpperCase()
            println(languageNa.toUpperCase())
        }




    }
}