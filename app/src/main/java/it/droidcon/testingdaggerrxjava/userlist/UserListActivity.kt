package it.droidcon.testingdaggerrxjava.userlist

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.droidcon.testingdaggerrxjava.R
import it.droidcon.testingdaggerrxjava.component
import javax.inject.Inject

class UserListActivity : AppCompatActivity() {

    @Inject lateinit var presenter: UserListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.userListComponent(UserListModule(this)).inject(this)
        setContentView(R.layout.activity_main)
        presenter.reloadUserList()
    }

    fun updateText(s: String) {
        findViewById<TextView>(R.id.text).text = s
    }

    fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}
