import kotlin.random.Random

enum class pState
{
    THINKING,
    EATING
}

data class Phil(
    val id: Int,
    var state: pState = pState.THINKING
)

class Fork

fun main()
{
    println("Введите кол-во философов")
    val n = readln().toInt()

    var p = List(n)
    {
        Phil(it + 1)
    }

    val forks = Array(n)
    {
        Fork()
    }

    val pRand = p.shuffled()

    for (p in pRand)
    {
        when (p.state)
        {
            pState.EATING -> println("Философ ${p.id} обедает")
            pState.THINKING -> println("Философ ${p.id} думает")
        }
    }
}

fun pEat (p: Phil, ps:List<Phil>, forks:Array<Fork>)
{
    val index = p.id - 1
    val lFork = forks[index]
    val rFork = forks[(index + 1) % forks.size]
    val firstFork =
        if (Random.nextBoolean())
        {
            rFork
        }

        else
        {
            lFork
        }

    val secondFork =
        if (firstFork == rFork)
        {
            lFork
        }

        else
        {
            rFork
        }

    synchronized(firstFork)
    {
        synchronized(secondFork)
        {
            p.state = pState.EATING
        }
    }
}


