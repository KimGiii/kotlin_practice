// 반환 타입을 적지 않아도 컴파일러가 식의 결과 타입을 함수 반환 타입으로 정해줌

// 코틀린에서는 타입 생략하는 경우 흔함

// 초기화 식을 사용하지 않고 변수를 선언하려면 반드시 변수 타입을 명시해야함

// 기본적으로 모든 변수를 val 키워드를 사용해 불변 변수로 선언하고, 꼭 필요할 때만 var 키워드로 수정

// val 참조 자체는 불변이지만, 그 참조가 가리키는 객체의 내부 값은 변경할 수 있음

fun <T> Collection<T>.joinToString(
    separator: String = ";",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        set(length - 1, value)
    }


class User(id: Int, name: String, address: String) {
    val id: Int = id
    val name: String = name
    val address: String = address
}

// 중복되는 로직을 제거하고, 필요하면 다른 필드에 대한 검증도 쉽게 추가 가능
fun saveUser(user: User) {
    fun validate(user: User,
                 value: String,
                 fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}

fun main(args: Array<String>) {
    val language = arrayListOf("Java")
    language.add("Kotlin")
    println(language)

    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf("Java", "Kotlin")
    val map = hashMapOf(1 to "Kotlin", 7 to "Java")

    println(set)
    println(list)
    println(map)

    // 어떤 타입의 값을 원소로 하는 컬렉션이든 처리할 수 있음
    val list2 = listOf(1, 2, 3)
    println(list2.joinToString(" "))

    // 확장 프로퍼티는 뒷받침하는 필드가 없어서 기본 getter 구현을 제공할 수 없음
    // 따라서, 최소한의 Getter는 꼭 정의해야한다.
    // 마찬가지로 초기화 코드에서 게산한 값을 담을 장소가 없으므로 초기화 코드도 쓸 수 없다.
    println("KOTLIN".lastChar)
    val sb = StringBuilder("KOTLIN?")
    sb.lastChar = '!'
    println(sb)

    // 맵을 만들려면 mapOf 함수 사용
    val mapp = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println(mapp)

    // 정규식을 명시적으로 만듬
    println("12.345-6.A".split("\\\\.|-".toRegex()))

    // 여러 구분 문자열 지정도 가능
    println("12.345-5.A".split(".", "-"))

    val Kim = User(1, "Kim", "Seoul")
    saveUser(Kim)

}
