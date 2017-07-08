package it.droidcon.testingdaggerrxjava.test1

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import it.droidcon.testingdaggerrxjava.TrampolineSchedulerRule
import it.droidcon.testingdaggerrxjava.core.UserInteractor
import it.droidcon.testingdaggerrxjava.core.UserStats
import it.droidcon.testingdaggerrxjava.userlist.UserListActivity
import it.droidcon.testingdaggerrxjava.userlist.UserListPresenter
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

class UserListPresenterTest {

    @get:Rule var mockitoRule = MockitoJUnit.rule()

    @get:Rule var schedulerRule = TrampolineSchedulerRule()

    val userInteractor: UserInteractor = mock()

    val activity: UserListActivity = mock()

    @InjectMocks lateinit var presenter: UserListPresenter

    @Test
    fun shouldLoadUsers() {
        Mockito.`when`(userInteractor.loadUsers()).thenReturn(
                Observable.fromArray(
                        UserStats.create(1, 50, "user1", "badge1"),
                        UserStats.create(2, 30, "user2", "badge2", "badge3")
                ).toList())

        presenter.reloadUserList()

        verify(activity, never()).showError(any())

        verify(activity).updateText(
                "50 user1 - badge1\n\n30 user2 - badge2, badge3")
    }
}