package care.vive.android.covid

enum class CovidStatus(val statusCode: Int) {
    HAVE_OR_POSSIBLE_HAVE_COVID(1),
    HAD_COVID_DIAGNOSED_BY_PHYSICIAN(2),
    POSSIBLE_HAVE_HAD_COVID(3),
    TESTED_POSITIVE_ANTIBODIES(4),
    HAVE_NOT_HAD_COVID_OR_DO_NOT_KNOW_STATUS(5)
}