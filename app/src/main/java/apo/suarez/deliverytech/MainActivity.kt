package apo.suarez.deliverytech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import apo.suarez.deliverytech.di.deliveryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(findViewById(R.id.nav_host_fragment))

        appBarConfiguration = AppBarConfiguration(navController.graph)

        startKoin {
            androidContext(this@MainActivity)
            modules(koinModules())
        }

    }

    private fun koinModules() = listOf(
        deliveryModule
    )

}