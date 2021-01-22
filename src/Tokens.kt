import java.lang.StringBuilder

val exp_keyword = Regex("(?:some | random | words | exp | keyword | dont |know)")
val exp_sep = Regex("(; | : | \\. | , | [{}])")
val exp_oper = Regex("== | [<>]= | =\\+-\\*<>/[()]")
val exp_int = Regex("[0-9]+")
val exp_char = Regex("[A-Za-z_]")
val exp_string = Regex("\"[%a-zA-Z,.\\\\/_]*\"")
val exp_comment = Regex("(//[%a-zA-Z,.\\\\/ ]+|/\\*[%a-zA-Z,.\\\\/ \\n]+\\*/)")
val exp_space = Regex("[ ]")
var str = "some 213 a new13 <= exp 20341 _ \"new_new\" //commentati"

fun res(tmp_res:Sequence<MatchResult>,tk_type:String):MutableMap<MatchResult,String>{
    val res: MutableMap<MatchResult,String> = mutableMapOf()
    for (r in tmp_res) {
        res[r] = tk_type
        str = str.replace(r.value,"")
    }
    return res
}

fun main() {
    val res: MutableMap<MatchResult,String> = mutableMapOf()
    res.putAll(res(exp_comment.findAll(str),"comment"))
    res.putAll(res(exp_keyword.findAll(str),"keyword"))
    res.putAll(res(exp_sep.findAll(str),"sep"))
    res.putAll(res(exp_oper.findAll(str),"oper"))
    res.putAll(res(exp_int.findAll(str),"int"))
    res.putAll(res(exp_string.findAll(str),"string"))
    res.putAll(res(exp_char.findAll(str),"char"))
    res.putAll(res(exp_space.findAll(str),"space"))


    for ((key,value) in res)
        println ("${key.value} $value\n")
}
