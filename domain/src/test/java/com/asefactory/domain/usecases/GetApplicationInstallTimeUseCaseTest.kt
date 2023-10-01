package com.asefactory.domain.usecases

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test

class TestRepository() : AppsRepository {
    override fun getApplicationsList(): List<ApplicationInfoWithInstallationDate> {
        return listOf(ApplicationInfoWithInstallationDate("", "", "", 12L, ""))
    }
}

class GetApplicationInstallTimeUseCaseTest {


    @Test
    fun shouldReturnCorrectData() {
        val usecase = GetApplicationInstallTimeUseCase(appsRepository = TestRepository())
        val actual = usecase.execute()
        val expectation = listOf(ApplicationInfoWithInstallationDate("", "", "", 12L, ""))
        assertEquals(expectation, actual)
        MatcherAssert.assertThat(
            usecase.execute().first(),
            isA(ApplicationInfoWithInstallationDate::class.java)
        )
    }
}