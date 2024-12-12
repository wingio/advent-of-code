package util.algorithm

import util.list.product

/**
 * Gets the greatest common divisor for a pair of numbers
 */
tailrec fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a.rem(b))
}

/**
 * Calculates the greatest common divisor and ax + by = gcd(a, b)
 *
 * @return Triple containing (in order): GCD, x coefficient, y coefficient
 */
fun extendedEuclidean(a: Long, b: Long): Triple<Long, Long, Long> {
    if (b == 0L) return Triple(a, 1L, 0L)
    val res = extendedEuclidean(b, a.rem(b))
    return Triple(res.first, res.third, res.second - (a / b) * res.third)
}

/**
 * Chinese remainder theorem
 *
 * Finds minimum positive number such that:
 * ```
 * x % nums[i] = rems[i]
 * ```
 *
 * @param nums List of coprime numbers
 * @param rems List of remainders
 */
fun cRem(
    nums: List<Long>,
    rems: List<Long>
): Long {
    val product = nums.product()
    var result = 0L

    for (i in rems.indices) {
        val (_, _, yCoef) = extendedEuclidean(nums[i], product / nums[i])
        val a = rems[i].toBigInteger()
        val b = yCoef.toBigInteger()
        val c = (product / nums[i]).toBigInteger()
        val rem = product.toBigInteger()

        result += (a * b * c).remainder(rem).toLong()
        result = (result.rem(product) + product).rem(product)
    }

    return result
}