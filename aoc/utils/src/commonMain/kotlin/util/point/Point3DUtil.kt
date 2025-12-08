package util.point

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Calculates the straight-line distance between this and another [point]
 * in 3-dimensional space, see [euclideanDistance] for 2D points
 *
 * https://en.wikipedia.org/wiki/Euclidean_distance
 */
fun Point3D.distanceTo(point: Point3D): Double {
    return sqrt(
        /* x */ (point.x.toDouble() - x.toDouble()).pow(2) +
        /* y */ (point.y.toDouble() - y.toDouble()).pow(2) +
        /* z */ (point.z.toDouble() - z.toDouble()).pow(2)
    )
}