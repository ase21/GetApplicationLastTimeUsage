package com.asefactory.domain.usecases

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository
import com.asefactory.domain.usecases.getinstalltime.GetInstallTimeUseCase
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test

class TestRepository() : AppsRepository {
    override fun getApplicationsList(): List<ApplicationInfoWithInstallationDate> {
        return listOf(ApplicationInfoWithInstallationDate("", "", "", 12L, ""))
    }
}

class GetInstallTimeUseCaseTest {


    @Test
    fun shouldReturnCorrectData() {
//        val currentRepository = mock<AppsRepository>()

        val usecase = GetInstallTimeUseCase(appsRepository = TestRepository())
        val actual = usecase.execute()
        val expectation = listOf(ApplicationInfoWithInstallationDate("", "", "", 12L, ""))
        assertEquals(expectation, actual)
        MatcherAssert.assertThat(
            usecase.execute().first(),
            isA(ApplicationInfoWithInstallationDate::class.java)
        )
    }
}