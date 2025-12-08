package util.point

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int
) {

    companion object {

        fun fromLine(line: String): Point3D {
            val (x, y, z) = line.split("""\D""".toRegex()).map { it.toInt() }
            return Point3D(x, y, z)
        }

    }

}